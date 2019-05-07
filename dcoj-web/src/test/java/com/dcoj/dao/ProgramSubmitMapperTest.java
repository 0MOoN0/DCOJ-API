package com.dcoj.dao;

import com.dcoj.entity.ProgramSubmissionEntity;
import com.dcoj.entity.example.ProgramSubmissionEntityExample;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Leon
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramSubmitMapperTest {

    @Autowired
    ProgramSubmissionMapper submissionMapper;

    /**
     * 测试SubmissionMapper
     */
    @Test
    public void testSave(){
        ProgramSubmissionEntity submission = new ProgramSubmissionEntity();
        submission.setLang(LanguageEnum.JAVA8);
        submission.setMemory(123);
        submission.setPid(0);
        submission.setSourceCode(123);
        submission.setStatus(ResultEnum.SE);
        submission.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        submission.setUsingTime(new BigDecimal(3));
        submission.setUid(123);
        submissionMapper.insertSelective(submission);
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate(){
        ProgramSubmissionEntity submission = new ProgramSubmissionEntity();
        submission.setSubId(3);
        submission.setLang(LanguageEnum.PYTHON35);
        submission.setMemory(123);
        submission.setPid(0);
        submission.setSourceCode(123);
        submission.setStatus(ResultEnum.AC);
        submission.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        submission.setUsingTime(new BigDecimal(3));
        submission.setUid(123);

        ProgramSubmissionEntityExample example = new ProgramSubmissionEntityExample();
        ProgramSubmissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andSubIdEqualTo(3);
        submissionMapper.updateByExample(submission, example);
    }

    @Test
    public void testSelect(){
        ProgramSubmissionEntityExample example = new ProgramSubmissionEntityExample();
        ProgramSubmissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andSubIdEqualTo(3);
        List<ProgramSubmissionEntity> submissionEntities = submissionMapper.selectByExample(example);
        submissionEntities.parallelStream().forEach((submissionEntity)-> System.out.println(submissionEntity));
    }

    @Test
    public void testDelete(){
        ProgramSubmissionEntityExample example = new ProgramSubmissionEntityExample();
        ProgramSubmissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andSubIdEqualTo(3);

        submissionMapper.deleteByExample(example);
    }

    @Test
    public void tsetCompareWithTimestamp(){
        ProgramSubmissionEntityExample example = new ProgramSubmissionEntityExample();
//        example.createCriteria().andGmtCreateLessThan(new Timestamp(System.currentTimeMillis()));
/*        Calendar begin = Calendar.getInstance();
        begin.set(2019, 4, 16, 19, 33, 0);
        Calendar end = Calendar.getInstance();
        end.set(2019, 4, 16, 19, 34, 0);*/
        LocalDateTime begin = LocalDateTime.of(2019, 4, 16, 19, 33, 0);
        LocalDateTime end = LocalDateTime.of(2019, 4, 16, 19, 34, 0);

//        System.out.println();
//        System.out.println();


        example.createCriteria().andSubmitTimeBetween(Timestamp.valueOf(begin),Timestamp.valueOf(end));

        List<ProgramSubmissionEntity> submissionEntities = submissionMapper.selectByExample(example);
        submissionEntities.parallelStream().forEach(submissionEntity -> System.out.println(submissionEntity));
    }

    @Test
    public void testSort(){
        ProgramSubmissionEntityExample submissionEntityExample = new ProgramSubmissionEntityExample();
        submissionEntityExample.setOrderByClause("time");
        submissionEntityExample.setOrderByClause("memory");
        String orderByClause = submissionEntityExample.getOrderByClause();  // only "memory"
        System.out.println(orderByClause);
    }

}
