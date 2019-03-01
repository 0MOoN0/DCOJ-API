package com.dcoj.service.impl;

import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.judge.JudgeQueue;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.task.ProblemJudgeTask;
import com.dcoj.judge.task.TestJudgeTask;
import com.dcoj.service.AsyncJudgeService;
import com.dcoj.service.ProblemService;
import com.dcoj.service.TestCasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Smith
 **/
@Service
public class AsyncJudgeServiceImpl implements AsyncJudgeService {

    @Autowired
    private JudgeQueue judgerQueue;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private TestCasesService testCasesService;

    @Override
    public String addTestJudge(String sourceCode, LanguageEnum lang, int time, int memory, List<TestCaseEntity> testCases) {
        TestJudgeTask task = new TestJudgeTask();
        // 生成id
        task.setId(generateId());
        task.setSourceCode(sourceCode);
        task.setLang(lang);
        task.setTime(time);
        task.setMemory(memory);
        task.setTestCases(testCases);
        task.setPriority(0);
        judgerQueue.addTask(task);
        return task.getId();
    }

    @Override
    public String addProblemJudge(String sourceCode, LanguageEnum lang,
                                  String owner, int pid) {
        ProblemEntity problemEntity = problemService.getById(pid);
        // 判断是否支持此语言
//        containLang(lang, problemEntity);
        // 封装单次判卷Entity
        ProblemJudgeTask task = new ProblemJudgeTask();
        task.setId(generateId());
        task.setSourceCode(sourceCode);
        task.setLang(lang);
        task.setTime(problemEntity.getRunTime());
        task.setMemory(problemEntity.getMemory());
        task.setTestCases(getTestCases(pid));
        task.setOwner(owner);
        task.setPid(pid);
        task.setProblemEntity(problemEntity);
        task.setPriority(1);
        judgerQueue.addTask(task);
        return task.getId();
    }

    // 生成id
    private String generateId() {
        return UUID.randomUUID().toString();
    }


    /**
     * 根据problem的Id获取测试用例
     * @param pid
     * @return
     */
    private List<TestCaseEntity> getTestCases(int pid) {
        List<TestCaseEntity> testCases = testCasesService.listAll(pid);
        if (testCases.size() == 0) {
            throw new WebErrorException("该题没有测试用例，无法判卷");
        }
        return testCases;
    }

/*    private void containLang(LanguageEnum lang, ProblemEntity problemEntity) {
        for (Object obj: problemEntity.getLang()) {
            if (lang == LanguageEnum.valueOf(obj.toString())) {
                return;
            }
        }
        throw new WebErrorException("不能使用此语言");
    }*/
}
