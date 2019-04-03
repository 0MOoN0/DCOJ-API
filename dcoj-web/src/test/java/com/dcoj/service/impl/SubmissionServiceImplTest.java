package com.dcoj.service.impl;

import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.SubmissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SubmissionService测试类
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubmissionServiceImplTest {

    @Autowired
    private SubmissionService submissionService;

    @Test
    public void testSave(){
        submissionService.save(0, 1, 11, LanguageEnum.PYTHON27, 3, 123, ResultEnum.AC);
    }

    @Test
    public void testCount(){
        int i = submissionService.countProblemSubmissions(0);
        System.out.println(i);
    }

}
