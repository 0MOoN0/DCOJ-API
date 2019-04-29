package com.dcoj.service.impl;

import com.dcoj.entity.ProgramSubmissionEntity;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProgramSubmissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * SubmissionService测试类
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramSubmissionServiceImplTest {

    @Autowired
    private ProgramSubmissionService submissionService;

    @Test
    public void testSave() throws InterruptedException {
/*        for(int i=0;i<5;i++){
            submissionService.save(5, 1, 11,0,0, LanguageEnum.JAVA8, i, i, ResultEnum.AC);
            Thread.sleep(1000);
        }*/
        int key = submissionService.save(6, 1, 11, 0, 0, LanguageEnum.CPP, 1, 1, ResultEnum.AC);
        System.out.println(key);

    }

    @Test
    public void testCount(){
        int i = submissionService.countProblemSubmissions(0);
        System.out.println(i);
    }

    @Test
    public void testListUserProblemSubmissions(){
        List<ProgramSubmissionEntity> submissionEntities = submissionService.listUserProblemSubmissions(0, 1);
        submissionEntities.parallelStream().forEach(System.out::println);
    }

    @Test
    public void testListProblemLeaderboard(){
//        List<SubmissionEntity> submissionEntities = submissionService.listProblemLeaderboard(1, "submit_time", "PYTHON27");
//        List<SubmissionEntity> submissionEntities = submissionService.listProblemLeaderboard(1, "using_time", "PYTHON27");
        List<ProgramSubmissionEntity> submissionEntities = submissionService.listProblemLeaderboard(1, "submit_time", "JAVA8");
        for (ProgramSubmissionEntity se: submissionEntities
             ) {
            System.out.println(se);
        }
    }

    @Test
    public void testListUserSubmission(){
        List<ProgramSubmissionEntity> submissionEntities = submissionService.listUserSubmission(0);
        for (ProgramSubmissionEntity se: submissionEntities
             ) {
            System.out.println(se);
        }
    }

}
