package com.dcoj.judge.runner;

import com.dcoj.entity.TestCaseEntity;
import com.dcoj.judge.*;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.entity.TestCaseRequestEntity;
import com.dcoj.judge.judger.Judger;
import com.dcoj.judge.task.JudgeTask;
import com.dcoj.judge.task.ProblemJudgeTask;
import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Smith
 **/
@Component
public class JudgeRunner {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final int MAX_THREADS = 3;

    // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    private final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(MAX_THREADS);

//    private final Cache<String, JudgeResult> submissionCache = CacheController.getSubmissionCache();

    private JudgeService judgeService;

    private JudgerDispatcher judgerDispatcher;

    // 构造器方法在IoC容器启动时将会被调用
    public JudgeRunner(JudgeQueue queue,    // 判卷队列
                       JudgeService judgeService, //判卷服务
                       JudgerDispatcher judgerDispatcher) {
        this.judgeService = judgeService;
        this.judgerDispatcher = judgerDispatcher;
        new Thread(() -> {  //新建线程
            while (true) {
                // 此队列为阻塞队列，当队列没有元素时，此方法将会被阻塞
                JudgeTask judgeTask = queue.take();
                try {
                    // 执行判卷，此线程池的实现是一个定长线程池，execute接受一个Runnable
                    THREAD_POOL.execute(new Runner(judgeTask));
                } catch (Exception e) {
                    // 如果抛出异常则将缓存中的判卷状态标为异常
//                    submissionCache.get(judgeTask.getId()).setStatus(JudgeStatus.Error);
                    LOGGER.error(e.getMessage());
                }
            }
        }).start();
    }

    class Runner implements Runnable {

        private JudgeTask judgeTask;

        private JudgeResult judgeResult;

        Runner(JudgeTask task) {
            this.judgeTask = task;
            this.judgeResult = submissionCache.get(judgeTask.getId());
        }

        @Override
        public void run() {
            // 正在判卷
            judgeResult.setStatus(JudgeStatus.Judging);

            LanguageEnum lang = judgeTask.getLang();
            String sourceCode = judgeTask.getSourceCode();
            int time = judgeTask.getTime();
            int memory =judgeTask.getMemory();
            // 将测试用例封装为请求测试用例
            List<TestCaseRequestEntity> testCases = new ArrayList<>(judgeTask.getTestCases().size());
            for (TestCaseEntity entity: judgeTask.getTestCases()) {
                TestCaseRequestEntity requestEntity = new TestCaseRequestEntity(entity.getStdin(), entity.getStdout());
                testCases.add(requestEntity);
            }
            // 封装请求体
            RequestEntity requestEntity = new RequestEntity(lang, sourceCode, time, memory, testCases);
            String judgerUrl = judgerDispatcher.getJudgerUrl();
            // 如果判卷地址为空，则标志此次判卷失败
            if (judgerUrl == null) {
                setJudgeFailed();
                LOGGER.error("判卷地址不得为空");
                return;
            }
            // 使用判卷实现进行判卷
            Judger judger = new Judger(judgerUrl, requestEntity, new Eagle());
            // 判卷
            ResponseEntity responseEntity = judger.judge();
            // 处理SE结果
            if (responseEntity.getResult() == ResultEnum.SE) {
                setJudgeFailed();
                judgeResult.setResponse(responseEntity);
                return;
            }
            judgeResult.setResponse(responseEntity);
            save();
        }

        private void save() {
            // 正在保存
            judgeResult.setStatus(JudgeStatus.Saving);

           if (judgeTask instanceof ProblemJudgeTask) {
                LOGGER.info("Saving problem");
                judgeService.saveProblemCode((ProblemJudgeTask) judgeTask, judgeResult.getResponse());
            } else {
                // do nothing
                LOGGER.info("Saving test");
            }
            judgeResult.setStatus(JudgeStatus.Finished);
        }

        private void setJudgeFailed() {
            judgeResult.setStatus(JudgeStatus.Error);
        }
    }
}
