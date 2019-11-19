package com.dcoj.service.impl;

import com.dcoj.dao.ProgramProblemMapper;
import com.dcoj.dao.TestCaseMapper;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.entity.example.TestCaseEntityExample;
import com.dcoj.service.TestCasesService;
import com.dcoj.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
     * 获取根据pid获取所有TestCases
     *
     * @return 此题目的所有测试用例
     */
    @Override
    public List<TestCaseEntity> listAllByPage(Map<String, Object> paramMap) {
        List<TestCaseEntity> testCaseEntities = testCaseMapper.selectAll(paramMap);
        return testCaseEntities;
    }

    /**
     * 根据题目ID获取一个测试用例
     *
     * @param tId 测试用例ID
     * @return 测试用例对象
     */
    @Override
    public TestCaseEntity getById(int tId) {
        return testCaseMapper.selectByPrimaryKey(tId);
    }

    /**
     * 根据PID题目ID获取一个测试用例
     *
     * @param pId 题目ID
     * @return 测试用例对象
     */
    @Override
    public TestCaseEntity getOneByPid(int pId) {
        TestCaseEntityExample testCaseEntityExample = new TestCaseEntityExample();
        testCaseEntityExample.createCriteria().andPidEqualTo(pId);
        List<TestCaseEntity> testCaseEntityList = testCaseMapper.selectByExample(testCaseEntityExample);
        WebUtil.assertNotNull(testCaseEntityList,"获取失败，不存在测试用例");
        return testCaseEntityList.get(0);
    }

    /**
     * 修改测试用例
     * @param testCaseEntity 测试用例信息
     */
    @Override
    public void updateTestCaseSelective(TestCaseEntity testCaseEntity) {
        WebUtil.assertNotNull(testCaseEntity.getTcId(),"更新失败，ID不能为空");
        TestCaseEntity isExist = testCaseMapper.selectByPrimaryKey(testCaseEntity.getTcId());
        WebUtil.assertNotNull(isExist,"更新失败，不存在此测试用例");
        testCaseMapper.updateByPrimaryKeySelective(testCaseEntity);
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

    /**
     *  新增测试用例
     * @param testCaseEntity 测试用例信息
     * @return
     */
    @Override
    public int save(TestCaseEntity testCaseEntity) {
        TestCaseEntityExample testCaseEntityExample = new TestCaseEntityExample();
        testCaseEntityExample.createCriteria()
                .andPidEqualTo(testCaseEntity.getPid())
                .andStdinEqualTo(testCaseEntity.getStdin())
                .andStdoutEqualTo(testCaseEntity.getStdout());
        List<TestCaseEntity> testCaseEntityList = testCaseMapper.selectByExample(testCaseEntityExample);
        WebUtil.assertNotNull(testCaseEntityList,"添加失败，测试用例已存在");
        return testCaseMapper.insertSelective(testCaseEntity);
    }
}
