package com.dcoj.util;

import com.dcoj.dao.SubmissionMapper;
import com.dcoj.entity.SubmissionEntity;
import com.dcoj.entity.example.SubmissionEntityExample;
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
    private SubmissionMapper submissionMapper;

    @Test
    public void pageHeplerTest(){
        SubmissionEntityExample example = new SubmissionEntityExample();
        example.createCriteria().andPidEqualTo(1);
        example.setOrderByClause("'sub_id' DESC");
        for (int i=1; i<=5; i++){
            PageHelper.startPage(i, 2);
            List<SubmissionEntity> submissionEntities = submissionMapper.selectByExample(example);
            submissionEntities.parallelStream().forEach(submissionEntity -> System.out.println(submissionEntity));
            System.out.println("================");
        }
    }

}
