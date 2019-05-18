package com.dcoj.service.impl;

import com.dcoj.entity.ProgramSubmissionDetailEntity;
import com.dcoj.service.ProgramSubmissionDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leon
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramSubmissionDetailServiceImplTest {

    @Autowired
    private ProgramSubmissionDetailService submissionDetailService;

    @Test
    public void testHelloWorld() {
        System.out.println("helloworld!");
    }

    @Test
    public void testGet() {
        ProgramSubmissionDetailEntity submissionDetailBySubId = submissionDetailService.getSubmissionDetailBySubId(14);
        System.out.println("===============" + submissionDetailBySubId);
    }

}
