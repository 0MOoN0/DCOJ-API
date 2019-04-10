package com.dcoj.service.impl;

import com.dcoj.dao.TestCaseMapper;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.entity.TestCaseEntityExample;
import com.dcoj.service.TestCasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试用例Service
 * @author Leon
 */
@Service
public class TestCasesServiceImpl implements TestCasesService {

    @Autowired
    private TestCaseMapper testCaseMapper;


    @Override
    public int save(int pid, String stdin, String stdout, int strength) {
        return 0;
    }

    @Override
    public int countProblemTestCases(int pid) {
        return 0;
    }

    /**
     * 获取根据pid获取所有TestCases
     * @param pid   题目的业务id
     * @return      此题目的所有测试用例
     */
    @Override
    public List<TestCaseEntity> listAll(int pid) {
        TestCaseEntityExample testCaseEntityExample = new TestCaseEntityExample();
        testCaseEntityExample.createCriteria().andPidEqualTo(pid);
        List<TestCaseEntity> testCaseEntities = testCaseMapper.selectByExample(testCaseEntityExample);
        return testCaseEntities;
    }

    /**
     * 根据题目ID获取一个测试用例
     *
     * @param pid 题目ID
     * @return 测试用例对象
     */
    @Override
    public TestCaseEntity getOneByPid(int pid) {
        TestCaseEntityExample testCaseEntityExample = new TestCaseEntityExample();
        testCaseEntityExample.createCriteria().andPidEqualTo(pid);
        TestCaseEntity testCaseEntity = testCaseMapper.selectOneByExample(testCaseEntityExample);
        return testCaseEntity;
    }

    @Override
    public void updateTestCaseByTidPid(int tid, int pid, String stdin, String stdout, int strength) {

    }

    @Override
    public void deleteTestCase(int tid, int pid) {

    }

    @Override
    public void deleteProblemTestCases(int pid) {

    }
}
