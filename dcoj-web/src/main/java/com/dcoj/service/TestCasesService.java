package com.dcoj.service;


import com.dcoj.entity.TestCaseEntity;

import java.util.List;

/**
 * 测试用例服务
 *
 * @author Leon
 **/
public interface TestCasesService {
    int save(int pid, String stdin, String stdout);

    int countProblemTestCases(int pid);

    List<TestCaseEntity> listAll(int pid);

    /**
     * 根据题目ID获取一个测试用例
     *
     * @param pid 题目ID
     * @return 测试用例对象
     */
    TestCaseEntity getOneByPid(int pid);

    void updateTestCaseByTidPid(int tid, int pid, String stdin, String stdout, int strength);

    void deleteTestCase(int tid, int pid);

    void deleteProblemTestCases(int pid);

}
