package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.config.DcojConfig;
import com.dcoj.dao.ExaminationProblemMapper;
import com.dcoj.entity.ExamJudgeEntity;
import com.dcoj.entity.ExaminationProblemEntity;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.entity.exam.AnswerEntity;
import com.dcoj.entity.exam.AnswerExamEntity;
import com.dcoj.entity.exam.ExamProblemResultEntity;
import com.dcoj.exam.ExamAutoTaskExtends;
import com.dcoj.exam.ExamJudgeStatus;
import com.dcoj.judge.JudgerDispatcher;
import com.dcoj.judge.ResultEnum;
import com.dcoj.judge.entity.RequestEntity;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.judger.Judger;
import com.dcoj.judge.judger.dcoj.DCOJJudger;
import com.dcoj.service.*;
import com.dcoj.util.FileUploadUtils;
import com.dcoj.util.JudgerUtils;
import com.dcoj.util.WebUtil;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dcoj.judge.LanguageEnum.JAVA8;

/**
 * @author Leon
 */
@Service
public class ExamJudgeServiceImpl implements ExamJudgeService {

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private TestCasesService testCasesService;

    @Autowired
    private ProgramSubmissionService programSubmissionService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private ProgramSubmissionDetailService programSubmissionDetailService;

    @Autowired
    private ObjectProblemService objectProblemService;

    @Autowired
    private ObjectSubmissionService objectSubmissionService;

    @Autowired
    private ExaminationSubmissionService examinationSubmissionService;

    @Autowired
    private ExaminationSubmissionDetailService examinationSubmissionDetailService;

    @Autowired
    private ProgramProblemUserService programProblemUserService;

    @Autowired
    private ObjectProblemUserService objectProblemUserService;

    @Autowired
    private JudgerDispatcher judgerDispatcher;

    @Autowired
    private ExaminationProblemMapper examinationProblemMapper;


    /**
     *  试卷判卷前的处理工作
     * @param examJudgeEntity
     */
    @Override
    public Integer handleExamJudge(ExamJudgeEntity examJudgeEntity) {
        AnswerExamEntity answerExamEntity = new AnswerExamEntity();
        List<AnswerEntity> answerSheet = new ArrayList<>();
        Map<Integer, ExaminationProblemEntity> examProblemSheet = new HashMap<>();
        ExamAutoTaskExtends examAutoTaskExtends = new ExamAutoTaskExtends();

        // 获取提交详情
        Integer examId = examJudgeEntity.getExamId();
        Integer userId = examJudgeEntity.getUserId();
        JSONArray problemArray = examJudgeEntity.getProblem_list();

        for(int i = 0 ; i < problemArray.size() ; i++){
            Integer id = problemArray.getJSONObject(i).getInteger("id");
            Integer type = problemArray.getJSONObject(i).getInteger("type");
            String answer = problemArray.getJSONObject(i).getString("answer");

            //根据题目id和试卷id获取到ExaminationProblemEntity实体，从而获取到题目在试卷的位置
            ExaminationProblemEntity examinationProblemEntity = examinationProblemMapper.listByExamIdAndProblemIdAndType(id,examId,type);

            StringBuffer stringBuffer = new StringBuffer(answer);
            AnswerEntity answerEntity = new AnswerEntity();
            answerEntity.setProblemType(type);
            answerEntity.setAnswer(stringBuffer);
            answerEntity.setProblemId(id);
            answerEntity.setLang(JAVA8);
            answerEntity.setExamProblemId(examinationProblemEntity.getExamProblemId()); //需要试卷在题目的位置
            answerSheet.add(answerEntity);

            examProblemSheet.put(examinationProblemEntity.getExamProblemId(),examinationProblemEntity);
        }

        answerExamEntity.setAnswerSheet(answerSheet);
        answerExamEntity.setExamProblemSheet(examProblemSheet);

        examAutoTaskExtends.setExamId(examId);
        examAutoTaskExtends.setUid(userId);

        //判卷
        return examJudge(answerExamEntity,examAutoTaskExtends);
    }

    // TODO 20190814 Leon 设计异常处理方式
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer examJudge(AnswerExamEntity answerExamEntity, ExamAutoTaskExtends examAutoTaskExtends) {
        List<AnswerEntity> answerSheet = answerExamEntity.getAnswerSheet();
        Map<Integer, ExaminationProblemEntity> problemSheet = answerExamEntity.getExamProblemSheet();
        // 请求实体类
        List<ExamProblemResultEntity> resultSheet = new ArrayList<ExamProblemResultEntity>();
        ExamProblemResultEntity examProblemResultEntity = null;
        // 分数
        int resultScore = 0;
        int problemScore = 0;
        // 问题详情
//        Map<Integer, ExaminationProblemEntity> problemEntityMap = examinationProblemService.listByExamId(examAutoTaskExtends.getExamId());
//        JSONObject problemDetail = null;
        // 判卷器
        for (AnswerEntity answerEntity : answerSheet){
            switch (answerEntity.getProblemType()){
                case 1:     // 编程题
                    // 获取题目详情
                    ExaminationProblemEntity examProgramProblemEntity = problemSheet.get(answerEntity.getExamProblemId());
                    // 单题判卷，调用本类的private方法  注：可能会抛出“不允许使用此语言的异常”
                    ResponseEntity responseEntity = programJudge(answerEntity, examProgramProblemEntity);
                    // 处理SE结果
                    if (responseEntity.getResult() == ResultEnum.SE) {
                        // TODO: Leon 20190709 System Error结果，需要记录到判卷情况
                        // 上传源码
//                            return;
                    }
                    // 获取题目分数
                    problemScore = examProgramProblemEntity.getScore();
                    // 分数计算
                    long ACCount = responseEntity.getTestCases().stream().filter(ts -> ts.getResult().equals(ResultEnum.AC)).count();
                    int resultProblemScore = (int) (problemScore * ACCount / responseEntity.getTestCases().size());
                    resultScore = resultScore + resultProblemScore;
                    // 计算分值，并保存编程题提交
                    Integer submissionDetailID = saveExamProgramSubmission(examAutoTaskExtends, answerEntity, 0, responseEntity, resultProblemScore);
                    programProblemService.updateProblemTimes(answerEntity.getProblemId(), responseEntity.getResult());
                    programProblemUserService.updateByPidUid(answerEntity.getProblemId(), examAutoTaskExtends.getUid(), responseEntity.getResult());
                    // 封装resultSheet，讲当前题目的判题结果封装到list中
                    handleExamProblemResult(answerEntity, resultProblemScore, submissionDetailID, resultSheet);
                    break;
                case 2:     // 客观题
                    //选择题判卷
                    Integer i = objectProblemService.judgeObjectProblem(answerEntity.getProblemId(), answerEntity.getAnswer().toString());
                    // 获取题目详情
                    ExaminationProblemEntity objectProblemEntity = problemSheet.get(answerEntity.getExamProblemId());
                    // 获取题目分数
                    int objectProblemScore = objectProblemEntity.getScore();
                    // 根据题目详情获取分数
                    problemScore = i==1 ?  objectProblemScore : 0;
                    // 计算判断题得分
                    resultScore = resultScore + problemScore;
                    Integer objectSubmissionDetailID = objectSubmissionService.save(examAutoTaskExtends.getUid(), answerEntity.getProblemId(), i, answerEntity.getAnswer().toString());
                    // 保存客观题答题详情
                    handleExamProblemResult(answerEntity, objectProblemScore, objectSubmissionDetailID, resultSheet);
                    // 更新用户客观题做题历史
                    objectProblemUserService.insertOrUpdate(answerEntity.getProblemId(), examAutoTaskExtends.getUid(), i.byteValue());
            }
        }
        // 保存examSubmission
        int examSubmissionId = examinationSubmissionService.save(ExamJudgeStatus.Finished, examAutoTaskExtends.getExamId(), resultScore, new Timestamp(System.currentTimeMillis()), examAutoTaskExtends.getUid());
        // 保存examSubmissionDetail
        JSONObject answerSheetJson = new JSONObject();
        answerSheetJson.put("answer_sheet", answerSheet);
        JSONObject resultSheetJson = new JSONObject();
        resultSheetJson.put("result_sheet", resultSheet);
        examinationSubmissionDetailService.save(examSubmissionId,answerSheetJson,resultSheetJson,new Timestamp(System.currentTimeMillis()));
        return examSubmissionId;
    }


    // 保存编程题题目，返回submissionDetail的ID
    private Integer saveExamProgramSubmission (ExamAutoTaskExtends examAutoTaskExtends, AnswerEntity answerEntity, Integer groupId, ResponseEntity responseEntity, Integer resultProblemScore){
        // 统计分数
        // 保存提交
        int subId = programSubmissionService.save(examAutoTaskExtends.getUid(), answerEntity.getProblemId(), examAutoTaskExtends.getExamId(), groupId, answerEntity.getLang(),
                responseEntity.getTime(), responseEntity.getMemory(), responseEntity.getResult(), resultProblemScore.byteValue());
        // 保存代码
        String sourceFileName = null;
        try {
            sourceFileName = FileUploadUtils.uploadCode(DcojConfig.getProfile(), answerEntity.getAnswer().toString(), answerEntity.getLang());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int attachId = attachmentService.save(examAutoTaskExtends.getUid(), sourceFileName);
        // 保存做题详情
        return programSubmissionDetailService.save(responseEntity, subId, attachId);
    }

    // 封装编程题判卷方法
    private ResponseEntity programJudge(AnswerEntity answerEntity, ExaminationProblemEntity examProgramProblemEntity){
        ProgramProblemEntity programProblemEntity = programProblemService.getByPrimaryKey(answerEntity.getProblemId());
        // 封装测试用例
        List<TestCaseEntity> testCaseEntities = testCasesService.listAll(answerEntity.getProblemId());
        JSONArray lang = examProgramProblemEntity.getLang().getJSONArray("lang");
        WebUtil.assertIsSuccess(lang.contains(answerEntity.getLang().getName())==true, "不允许使用此语言");
        // 封装请求用例
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setTestCases(JudgerUtils.converToTestRequestList(testCaseEntities));  // 将TestCase转成TestCaseRequest
        requestEntity.setMemoryLimit(programProblemEntity.getMemory());
        requestEntity.setLang(answerEntity.getLang());
        requestEntity.setSourceCode(answerEntity.getAnswer().toString());
        requestEntity.setTimeLimit(programProblemEntity.getRunTime());
        // 编程题判卷
        Judger judger = new Judger(judgerDispatcher.getJudgerUrl(),requestEntity,new DCOJJudger());
        ResponseEntity responseEntity = judger.judge();
        return responseEntity;
    }

    private List<ExamProblemResultEntity> handleExamProblemResult(AnswerEntity answerEntity, Integer score, Integer submissionDetailId,List<ExamProblemResultEntity> resultSheet){
        ExamProblemResultEntity problemResultEntity = new ExamProblemResultEntity(answerEntity.getExamProblemId(), submissionDetailId, score);
        resultSheet.add(problemResultEntity);
        return resultSheet;
    }


}
