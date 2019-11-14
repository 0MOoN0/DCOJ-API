package com.dcoj.service;


import com.dcoj.entity.TestCaseEntity;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 测试用例服务
 *
 * @author Leon
 **/
public interface TestCasesService {
    int save(int pid, String stdin, String stdout, int strength);

    int countProblemTestCases(int pid);

    /**
     *  获取指定题目的测试用例
     * @param pid
     * @return
     */
    List<TestCaseEntity> listAll(int pid);

    /**
     * 根据题目ID获取一个测试用例
     *
     * @param pid 题目ID
     * @return 测试用例对象
     */
    TestCaseEntity getOneByPid(int pid);

    void updateTestCaseByTidPid(int tid, int pid, String stdin, String stdout, int strength);

    /**
     *  删除一条测试用例
     * @param tid
     */
    int deleteTestCase(Integer tid);

    /**
     *  清空题目的测试用例
     * @param pid
     */
    int deleteProblemTestCases(int pid);

    /**
     *  批量保存测试用例
     * @param testCaseEntityList
     * @return
     */
    int saveAll(List<TestCaseEntity> testCaseEntityList);

}
