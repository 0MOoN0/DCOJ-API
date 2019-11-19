package com.dcoj.service;


import com.dcoj.entity.TestCaseEntity;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * 测试用例服务
 *
 * @author Leon
 **/
public interface TestCasesService {
    int save(int pid, String stdin, String stdout);

    int countProblemTestCases(int pid);

    /**
     *  获取指定题目的测试用例
     * @param pid
     * @return
     */
    List<TestCaseEntity> listAll(int pid);

    /**
     * 获取所有TestCases
     *
     * @return 所有测试用例
     */
    List<TestCaseEntity> listAllByPage(Map<String, Object> paramMap);

    /**
     * 根据ID获取一个测试用例
     *
     * @param tId 测试用例ID
     * @return 测试用例对象
     */
    TestCaseEntity getById(int tId);

    /**
     * 根据PID题目ID获取一个测试用例
     *
     * @param pId 题目ID
     * @return 测试用例对象
     */
    TestCaseEntity getOneByPid(int pId);

    /**
     * 修改测试用例
     * @param testCaseEntity 测试用例信息
     */
    void updateTestCaseSelective(TestCaseEntity testCaseEntity);

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

    /**
     *  新增测试用例
     * @param testCaseEntity 测试用例信息
     */
    int save(TestCaseEntity testCaseEntity);

}
