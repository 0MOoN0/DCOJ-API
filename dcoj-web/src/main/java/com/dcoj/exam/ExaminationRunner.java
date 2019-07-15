package com.dcoj.exam;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.entity.exam.AnswerEntity;
import com.dcoj.entity.exam.UserExamEntity;
import com.dcoj.judge.JudgerDispatcher;
import com.dcoj.judge.ResultEnum;
import com.dcoj.judge.entity.RequestEntity;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.judger.Judger;
import com.dcoj.judge.judger.dcoj.DCOJJudger;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.service.TestCasesService;
import com.dcoj.util.JudgerUtils;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Leon
 */
public class ExaminationRunner {

    private final int MAX_THREADS = 3;
    private final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(MAX_THREADS);
    private final static DelayQueue<ExamAutoTask> delayQueue = new DelayQueue<ExamAutoTask>();

    @Autowired
    private ObjectProblemService objectProblemService;

    @Autowired
    private TestCasesService testCasesService;

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private JudgerDispatcher judgerDispatcher;

    private boolean start;

    public void start(){
        if(start){
            return;
        }
        start = true;
        new Thread(()->{
            try {
                while(true){
                    ExamAutoTask order = delayQueue.take();
//                    THREAD_POOL.execute(()->{judgeExam(order);});
                }
            } catch (InterruptedException e) {
                // 判卷出错
            }
        }).start();
    }

    // 进行判卷的方法
    public void judgeExam(ExamAutoTask examAutoTask){
        examAutoTask.setExamJudgeStatus(ExamJudgeStatus.Judging);

        Cache<String, UserExamEntity> examCache = GlobalCacheManager.getExamCache();
        // 判断试卷类型
        if(examAutoTask instanceof ExamAutoTaskExtends){
            ExamAutoTaskExtends examAutoTaskExtends = (ExamAutoTaskExtends) examAutoTask;
            Integer examUserId = examAutoTaskExtends.getExamUserId();
            // 根据UserId 和 ExamId 获取缓存
            UserExamEntity userExamEntity = examCache.get(examUserId + "");
            List<AnswerEntity> answerSheet = userExamEntity.getAnswerSheet();
            RequestEntity requestEntity = new RequestEntity();
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
                        Judger judger = new Judger(judgerDispatcher.getJudgerUrl(),requestEntity,new DCOJJudger());
                        ResponseEntity responseEntity = judger.judge();
                        // 处理SE结果
                        if (responseEntity.getResult() == ResultEnum.SE) {
                            // TODO: Leon 20190709 System Error结果，需要记录到判卷情况

                            return;
                        }
                            // TODO: Leon 20190709 记录结果
                        break;
                    case 2:     // 客观题
                        //选择题判卷
                        int i = objectProblemService.judgeObjectProblem(answerEntity.getProblemId(), answerEntity.getAnswer().toString());
                        // TODO: Leon 20190709 记录判卷状态
                        break;
                }
            }
        }
        examAutoTask.setExamJudgeStatus(ExamJudgeStatus.Saving);
        // TODO: Leon 20190710 保存试卷提交
    }
}
