package com.dcoj.dao;

import com.dcoj.entity.SubmissionEntity;
import com.dcoj.entity.SubmissionEntityExample;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Leon
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubmissionMapperTest {

    @Autowired
    SubmissionMapper submissionMapper;

    /**
     * 测试SubmissionMapper
     */
    @Test
    public void testSave(){
        SubmissionEntity submission = new SubmissionEntity();
        submission.setLang(LanguageEnum.JAVA8);
        submission.setMemory(123);
        submission.setPid(0);
        submission.setSourceCode(123);
        submission.setStatus(ResultEnum.SE);
        submission.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        submission.setUsingTime(new BigDecimal(3));
        submission.setUid(123);
        submissionMapper.insert(submission);
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate(){
        SubmissionEntity submission = new SubmissionEntity();
        submission.setSubId(3);
        submission.setLang(LanguageEnum.PYTHON35);
        submission.setMemory(123);
        submission.setPid(0);
        submission.setSourceCode(123);
        submission.setStatus(ResultEnum.AC);
        submission.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        submission.setUsingTime(new BigDecimal(3));
        submission.setUid(123);

        SubmissionEntityExample example = new SubmissionEntityExample();
        SubmissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andSubIdEqualTo(3);
        submissionMapper.updateByExample(submission, example);
    }

    @Test
    public void testSelect(){
        SubmissionEntityExample example = new SubmissionEntityExample();
        SubmissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andSubIdEqualTo(3);
        List<SubmissionEntity> submissionEntities = submissionMapper.selectByExample(example);
        submissionEntities.parallelStream().forEach((submissionEntity)-> System.out.println(submissionEntity));
    }

    @Test
    public void testDelete(){
        SubmissionEntityExample example = new SubmissionEntityExample();
        SubmissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andSubIdEqualTo(3);

        submissionMapper.deleteByExample(example);
    }



}
