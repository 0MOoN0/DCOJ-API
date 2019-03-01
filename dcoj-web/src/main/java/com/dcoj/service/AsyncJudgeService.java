package com.dcoj.service;


import com.dcoj.entity.TestCaseEntity;
import com.dcoj.judge.LanguageEnum;

import java.util.List;

/**
 * @author Leon
 **/
public interface AsyncJudgeService {


    String addTestJudge(String sourceCode, LanguageEnum lang, int time, int memory,
                        List<TestCaseEntity> testCases);

    /**
     * 添加Problem判卷，根据所给参数生成Task，进行判卷
     * @param sourceCode        待测试的源码
     * @param lang              语言类型
     * @param owner             此测试的提交者
     * @param pid               题目的业务id
     * @return                  task的id
     */
    String addProblemJudge(String sourceCode, LanguageEnum lang,
                           String owner, int pid);
}
