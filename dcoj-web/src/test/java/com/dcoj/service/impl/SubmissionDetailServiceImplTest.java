package com.dcoj.service.impl;

import com.dcoj.dao.SubmissionDetailMapper;
import com.dcoj.entity.SubmissionDetailEntity;
import com.dcoj.entity.example.SubmissionDetailEntityExample;
import com.dcoj.service.SubmissionDetailService;
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
    private SubmissionDetailService submissionDetailService;

    @Test
    public void testHelloWorld(){
        System.out.println("helloworld!");
    }

    @Test
    public void testGet(){
        SubmissionDetailEntity submissionDetailBySubId = submissionDetailService.getSubmissionDetailBySubId(0);
        System.out.println("==============="+submissionDetailBySubId);
    }

}
