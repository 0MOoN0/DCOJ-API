package com.dcoj.dao;

import com.dcoj.entity.TestCaseEntity;
import com.dcoj.entity.example.TestCaseEntityExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseMapperTest {

    @Autowired
    private TestCaseMapper testCaseMapper;

    @Test
    public void testSelectOne(){
        TestCaseEntityExample example = new TestCaseEntityExample();
        example.createCriteria().andPidEqualTo(10);
        TestCaseEntity testCaseEntity = testCaseMapper.selectOneByExample(example);
        System.out.println(testCaseEntity);
    }

    @Test
    public void testSelectAll(){
        TestCaseEntityExample example = new TestCaseEntityExample();
        example.createCriteria().andPidEqualTo(10);
        List<TestCaseEntity> testCaseEntities = testCaseMapper.selectByExample(example);
        testCaseEntities.parallelStream().forEach(testCaseEntity -> System.out.println(testCaseEntity));
    }

}
