package com.dcoj.judge.task;

import com.dcoj.entity.TestCaseEntity;
import com.dcoj.judge.LanguageEnum;

import java.util.List;

/**
 * @author Smith
 **/
public interface JudgeTask {
    String getId();

    String getSourceCode();

    LanguageEnum getLang();

    List<TestCaseEntity> getTestCases();

    int getTime();

    int getMemory();

    int getPriority();

}
