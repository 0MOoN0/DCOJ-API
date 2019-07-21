package com.dcoj.service.impl;

import com.dcoj.entity.ExaminationSubmissionEntity;
import com.dcoj.entity.example.ExaminationSubmissionEntityExample;
import com.dcoj.exam.ExamJudgeStatus;
import com.dcoj.service.ExaminationSubmissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author Leon
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExaminationSubmissionServiceImplTest {

    @Autowired
    private ExaminationSubmissionService examinationSubmissionService;

    @Test
    public void testSave(){
        int save = examinationSubmissionService.save(ExamJudgeStatus.Judging, 1, 90, new Timestamp(System.currentTimeMillis()), 1);
        System.out.println("ID == "+save);
    }

    @Test
    public void testSelect(){
        List<ExaminationSubmissionEntity> examinationSubmissionEntities = examinationSubmissionService.listByID(null, null);
        Optional.ofNullable(examinationSubmissionEntities).ifPresent(System.out::println);
    }


}
