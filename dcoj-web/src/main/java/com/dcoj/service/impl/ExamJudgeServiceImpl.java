package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.config.DcojConfig;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.entity.exam.AnswerEntity;
import com.dcoj.entity.exam.ExamProblemResultEntity;
import com.dcoj.exam.ExamAutoTaskExtends;
import com.dcoj.exam.ExamJudgeStatus;
import com.dcoj.judge.ResultEnum;
import com.dcoj.judge.entity.RequestEntity;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.judger.Judger;
import com.dcoj.service.*;
import com.dcoj.util.FileUploadUtils;
import com.dcoj.util.JudgerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Leon
 */
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


//    private ObjectProblemUserService objectProblemUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void examJudge(List<AnswerEntity> answerSheet, Judger judger, ExamAutoTaskExtends examAutoTaskExtends, JSONArray examProblem) {
        // 请求实体类
        RequestEntity requestEntity = new RequestEntity();
        List<ExamProblemResultEntity> resultSheet = new ArrayList<ExamProblemResultEntity>();
        ExamProblemResultEntity examProblemResultEntity = null;
        // 分数
        int resultScore = 0;
        int problemScore = 0;
        // 问题详情
        JSONObject problemDetail = null;
        // 判卷器
        for (AnswerEntity answerEntity : answerSheet){
            switch (answerEntity.getProblemType()){
                case 1:     // 编程题
                    // 单题判卷
                    // 获取测试用例
                    ProgramProblemEntity programProblemEntity = programProblemService.getByPrimaryKey(answerEntity.getProblemId());
                    // 封装测试用例
                    List<TestCaseEntity> testCaseEntities = testCasesService.listAll(answerEntity.getProblemId());
                    requestEntity.setTestCases(JudgerUtils.converToTestRequestList(testCaseEntities));  // 将TestCase转成TestCaseRequest
                    requestEntity.setMemoryLimit(programProblemEntity.getMemory());
                    requestEntity.setLang(answerEntity.getLang());
                    requestEntity.setSourceCode(answerEntity.getAnswer().toString());
                    // 进行判卷
                    ResponseEntity responseEntity = judger.judge();
                    // 处理SE结果
                    if (responseEntity.getResult() == ResultEnum.SE) {
                        // TODO: Leon 20190709 System Error结果，需要记录到判卷情况
                        // 上传源码
//                            return;
                    }
                    // 保存判卷结果
                    // 省去分数统计
                    /*int subId = programSubmissionService.save(examAutoTaskExtends.getUid(), answerEntity.getProblemId(), examAutoTaskExtends.getExamId(), 0, answerEntity.getLang(),
                            responseEntity.getTime(), responseEntity.getMemory(), responseEntity.getResult(), (byte) 95);*/
                    // 上传代码附件
/*                    String sourceFileName = null;
                    try {
                        sourceFileName = FileUploadUtils.uploadCode(DcojConfig.getProfile(), answerEntity.getAnswer().toString(), answerEntity.getLang());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int attachId = attachmentService.save(examAutoTaskExtends.getUid(), sourceFileName);
                    // 保存判卷详情
                    programSubmissionDetailService.save(responseEntity, subId, attachId);*/
                    problemDetail = examProblem.getJSONObject(answerEntity.getExamProblemLocate());
                    problemScore = problemDetail.getInteger("problem_score");
                    resultScore = resultScore + problemScore;
                    Integer submissionDetailID = saveExamProgramSubmission(examAutoTaskExtends, answerEntity, 0, responseEntity, problemScore);
                    // TODO: Leon 20190724 更新题目状态、更新用户做题状态
                    programProblemService.updateProblemTimes(answerEntity.getProblemId(), responseEntity.getResult());
                    programProblemUserService.updateByPidUid(answerEntity.getProblemId(), examAutoTaskExtends.getUid(), responseEntity.getResult());
                    // 封装resultSheet
                    examProblemResultEntity.setExamProblemLocate(answerEntity.getExamProblemLocate());
                    examProblemResultEntity.setScore(problemScore);
                    examProblemResultEntity.setSubmissionDetail(submissionDetailID);
                    resultSheet.add(examProblemResultEntity);
                    break;
                case 2:     // 客观题

                    //选择题判卷
                    int i = objectProblemService.judgeObjectProblem(answerEntity.getProblemId(), answerEntity.getAnswer().toString());
                    if(i==1){
                        // TODO: Leon 20190730 更新题目状态、更新用户做题状态
                        // 保存客观题
                        objectSubmissionService.save(examAutoTaskExtends.getUid(),answerEntity.getProblemId(),i,answerEntity.getAnswer().toString());
                        examProblemResultEntity = new ExamProblemResultEntity();
                        examProblemResultEntity.setExamProblemLocate(answerEntity.getExamProblemLocate());
                        problemDetail = examProblem.getJSONObject(answerEntity.getExamProblemLocate());
                        resultScore = i==1 ?  problemDetail.getInteger("problem_score") : 0;
                        resultSheet.add(examProblemResultEntity);
                    }
            }
        }
        // 保存examSubmission
        int examSubmissionId = examinationSubmissionService.save(ExamJudgeStatus.Saving, examAutoTaskExtends.getExamId(), resultScore, new Timestamp(System.currentTimeMillis()), examAutoTaskExtends.getUid());
        // 保存examSubmissionDetail
        JSONObject answerSheetJson = new JSONObject();
        answerSheetJson.put("answer_sheet", answerSheet);
        JSONObject resultSheetJson = new JSONObject();
        resultSheetJson.put("result_sheet", resultSheet);
        examinationSubmissionDetailService.save(examSubmissionId,answerSheetJson,resultSheetJson,new Timestamp(System.currentTimeMillis()));
    }
    // 保存编程题题目，返回submissionDetail的ID
    protected Integer saveExamProgramSubmission (ExamAutoTaskExtends examAutoTaskExtends, AnswerEntity answerEntity, Integer groupId, ResponseEntity responseEntity, Integer problemScore){
        // 统计分数
        // 测试用例正确个数
        long ACCount = responseEntity.getTestCases().stream().filter(Predicate.isEqual(ResultEnum.AC)).count();
        long resultProblemScore = problemScore * ACCount / responseEntity.getTestCases().size();
        // 保存提交
        int subId = programSubmissionService.save(examAutoTaskExtends.getUid(), answerEntity.getProblemId(), examAutoTaskExtends.getExamId(), groupId, answerEntity.getLang(),
                responseEntity.getTime(), responseEntity.getMemory(), responseEntity.getResult(), (byte) resultProblemScore);
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


}
