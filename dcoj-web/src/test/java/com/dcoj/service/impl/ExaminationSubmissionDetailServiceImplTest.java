package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ExaminationSubmissionDetailEntity;
import com.dcoj.service.ExaminationSubmissionDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

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
        // 查询一条
        ExaminationSubmissionDetailEntity detail = examinationSubmissionDetailService.getExaminationSubmissionDetailByIdCheckWithTime(1);
        System.out.println(detail);
    }

    @Test
    public void testSave(){
        ExaminationSubmissionDetailEntity entity = new ExaminationSubmissionDetailEntity();
//        entity.setAnswerSheet(new JSONObject());
//        entity.setResultSheet(new JSONObject());
        entity.setExamSubmissionId(1);
        entity.setQueryableTime(new Timestamp(System.currentTimeMillis()));
        examinationSubmissionDetailService.save(1, new JSONObject(), null, new Timestamp(System.currentTimeMillis()));
    }


}
