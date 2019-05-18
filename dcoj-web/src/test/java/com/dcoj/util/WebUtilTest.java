package com.dcoj.util;

import com.dcoj.dao.ProgramSubmissionMapper;
import com.dcoj.entity.ProgramSubmissionEntity;
import com.dcoj.entity.example.ProgramSubmissionEntityExample;
import com.github.pagehelper.PageHelper;
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
public class WebUtilTest {

    @Autowired
    private ProgramSubmissionMapper submissionMapper;

    @Test
    public void pageHeplerTest() {
        ProgramSubmissionEntityExample example = new ProgramSubmissionEntityExample();
        example.createCriteria().andPidEqualTo(1);
        example.setOrderByClause("'sub_id' DESC");
        for (int i = 1; i <= 5; i++) {
            PageHelper.startPage(i, 2);
            List<ProgramSubmissionEntity> submissionEntities = submissionMapper.selectByExample(example);
            submissionEntities.parallelStream().forEach(submissionEntity -> System.out.println(submissionEntity));
            System.out.println("================");
        }
    }

}
