package com.dcoj.service.impl;

import com.dcoj.dao.SubmissionDetailMapper;
import com.dcoj.entity.SubmissionDetailEntity;
import com.dcoj.entity.example.SubmissionDetailEntityExample;
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
public class SubmissionDetailServiceImplTest {

    @Autowired
    private SubmissionDetailMapper submissionDetailMapper;

    @Test
    public void testHelloWorld(){
        System.out.println("helloworld!");
    }

    @Test
    public void testGet(){
        //根据subId获取submissionDetail
        SubmissionDetailEntityExample submissionDetailEntityExample = new SubmissionDetailEntityExample();
        submissionDetailEntityExample.createCriteria()
                .andSubIdEqualTo(0);
        List<SubmissionDetailEntity> submissionDetailEntities = submissionDetailMapper.selectByExample(submissionDetailEntityExample);
    }

}
