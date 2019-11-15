package com.dcoj.service.impl;

import com.dcoj.dao.ProgramProblemMapper;
import com.dcoj.dao.TestCaseMapper;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.entity.example.TestCaseEntityExample;
import com.dcoj.service.TestCasesService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试用例Service
 *
 * @author Leon
 */
@Service
public class TestCasesServiceImpl implements TestCasesService {

    @Autowired
    private TestCaseMapper testCaseMapper;

    @Autowired
    private ProgramProblemMapper programProblemMapper;

    /**
     *根据pid插入TestCase
     *
     * @param pid 题目的业务id
     *
     */
    @Override
    public int save(int pid, String stdin, String stdout) {
        TestCaseEntity testCaseEntity = new TestCaseEntity();
        testCaseEntity.setPid(pid);
        testCaseEntity.setStdin(stdin);
        testCaseEntity.setStdout(stdout);
        return testCaseMapper.insert(testCaseEntity);
    }

    @Override
    public int countProblemTestCases(int pid) {
        return 0;
    }

    /**
     * 获取根据pid获取所有TestCases
     *
     * @param pid 题目的业务id
     * @return 此题目的所有测试用例
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
    public int deleteTestCase(Integer tid) {
        TestCaseEntity testCaseEntity = testCaseMapper.selectByPrimaryKey(tid);
        WebUtil.assertNotNull(testCaseEntity,"删除失败，不存在此测试用例");
        return testCaseMapper.deleteByPrimaryKey(tid);
    }

    @Override
    public int deleteProblemTestCases(int pid) {
        TestCaseEntityExample testCaseEntityExample = new TestCaseEntityExample();
        testCaseEntityExample.createCriteria().andPidEqualTo(pid);
        return testCaseMapper.deleteByExample(testCaseEntityExample);
    }

    @Override
    public int saveAll(List<TestCaseEntity> testCaseEntityList) {
        ProgramProblemEntity programProblemEntity = programProblemMapper.getByPrimaryKey(testCaseEntityList.get(0).getPid());
        WebUtil.assertNotNull(programProblemEntity,"添加失败，题目异常");

        //TODO:如果已经存在了该测试用例则不重新插入

        return testCaseMapper.saveAll(testCaseEntityList);
    }
}
