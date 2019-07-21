package com.dcoj.dao;

import com.dcoj.entity.ExaminationSubmissionEntity;
import com.dcoj.entity.example.ExaminationSubmissionEntityExample;
import com.dcoj.exam.ExamJudgeStatus;
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
public class ExaminationSubmissionMapperTest {

    @Autowired
    private ExaminationSubmissionMapper examinationSubmissionMapper;

    @Test
    public void testSave(){
        ExaminationSubmissionEntity entity = new ExaminationSubmissionEntity();
        entity.setExamStatus(ExamJudgeStatus.Finished);
        entity.setExamId(1);
        entity.setQueryableTime(new Timestamp(System.currentTimeMillis()));
        entity.setScore(95);
        examinationSubmissionMapper.insertSelective(entity);
    }

    @Test
    public void testUpdate(){
        ExaminationSubmissionEntity entity = new ExaminationSubmissionEntity();
        entity.setExamSubmissionId(1);
        entity.setExamStatus(ExamJudgeStatus.Finished);
        entity.setExamId(1);
        entity.setQueryableTime(new Timestamp(System.currentTimeMillis()));
        entity.setScore(90);
        examinationSubmissionMapper.updateByPrimaryKeySelective(entity);
    }

    // 存入null值的score
    @Test
    public void testUpdateNullScore(){
        ExaminationSubmissionEntity entity = new ExaminationSubmissionEntity();
        entity.setExamSubmissionId(1);
        entity.setExamStatus(ExamJudgeStatus.Finished);
        entity.setExamId(1);
        entity.setQueryableTime(new Timestamp(System.currentTimeMillis()));
        entity.setScore(null);
        examinationSubmissionMapper.updateByPrimaryKey(entity);
    }

    @Test
    public void testSelect(){
        List<ExaminationSubmissionEntity> examinationSubmissionEntities = examinationSubmissionMapper.selectByExample(new ExaminationSubmissionEntityExample());
        Optional.ofNullable(examinationSubmissionEntities).ifPresent(System.out::println);
    }

    @Test
    public void testDelete(){
        examinationSubmissionMapper.deleteByPrimaryKey(1);
    }





}
