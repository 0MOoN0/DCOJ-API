package com.dcoj.exam;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.config.DcojConfig;
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
import com.dcoj.service.*;
import com.dcoj.util.FileUploadUtils;
import com.dcoj.util.JudgerUtils;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/**
 * @author Leon
 */
public class ExaminationRunner {

    private final int MAX_THREADS = 3;
    private final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(MAX_THREADS);
    private final static DelayQueue<ExamAutoTask> delayQueue = new DelayQueue<ExamAutoTask>();

    @Autowired
    private ExamJudgeService examJudgeService;

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
                    THREAD_POOL.execute(() -> judgeExam(order));
                }
            } catch (InterruptedException e) {
                // 判卷出错
            }
        }).start();
    }



    // 进行判卷的方法
    @Transactional(rollbackFor = Exception.class)
    public void judgeExam(ExamAutoTask examAutoTask){
        examAutoTask.setExamJudgeStatus(ExamJudgeStatus.Judging);

        Cache<String, UserExamEntity> examCache = GlobalCacheManager.getExamCache();
        // 判断试卷类型
        if(examAutoTask instanceof ExamAutoTaskExtends){
            ExamAutoTaskExtends examAutoTaskExtends = (ExamAutoTaskExtends) examAutoTask;
            Integer examUserId = examAutoTaskExtends.getExamUserId();
            // 根据UserId 和 ExamId 获取缓存
            UserExamEntity userExamEntity = examCache.get(examUserId + "");
            // 用户答题卡
            List<AnswerEntity> answerSheet = userExamEntity.getAnswerSheet();
            examAutoTask.setExamJudgeStatus(ExamJudgeStatus.Saving);
            String judgerUrl = judgerDispatcher.getJudgerUrl();

            // TODO: Leon 20190710 保存试卷提交
//            examJudgeService.examJudge(answerSheet, examAutoTaskExtends,);
        }
    }
}
