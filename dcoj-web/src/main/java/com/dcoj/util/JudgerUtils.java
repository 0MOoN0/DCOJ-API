package com.dcoj.util;

import com.dcoj.entity.TestCaseEntity;
import com.dcoj.judge.entity.TestCaseRequestEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包含了判卷所需的工具类合集
 *
 * @author Leon
 */
public class JudgerUtils {

    /**
     * 将所给的TestCaseEntity 转成 TestCaseRequestEntity
     * @param testCaseEntities
     * @return List<TestCaseRequestEntity>
     */
    public static List<TestCaseRequestEntity> converToTestRequestList(List<TestCaseEntity> testCaseEntities){
        return testCaseEntities.stream().map(testCaseEntity -> {        // 将TestCase集合映射出来
            TestCaseRequestEntity testCaseRequestEntity = new TestCaseRequestEntity();
            testCaseRequestEntity.setStdin(testCaseEntity.getStdin());
            testCaseRequestEntity.setStdout(testCaseEntity.getStdout());
            return testCaseRequestEntity;       // 映射成TestCaseEntity
        }).collect(Collectors.toList());        // 收集所有TestCaseEntity

    }
}
