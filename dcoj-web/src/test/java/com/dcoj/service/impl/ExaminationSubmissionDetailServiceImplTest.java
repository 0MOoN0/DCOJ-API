package com.dcoj.service.impl;

import com.dcoj.entity.ExaminationSubmissionDetailEntity;
import com.dcoj.service.ExaminationSubmissionDetailService;
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
public class ExaminationSubmissionDetailServiceImplTest {

    @Autowired
    private ExaminationSubmissionDetailService examinationSubmissionDetailService;

    @Test
    public void testSelect(){
        ExaminationSubmissionDetailEntity detail = examinationSubmissionDetailService.getExaminationSubmissionDetailByIdCheckWithTime(1);
        System.out.println(detail);
    }


}
