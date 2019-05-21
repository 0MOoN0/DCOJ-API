package com.dcoj.service.impl;

import com.dcoj.service.ProgramProblemTagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 题目标签关联业务层测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagProgramProblemServiceImplTest {

    @Autowired
    private ProgramProblemTagService programProblemTagService;


    @Test
    public void save() {

    }

    @Test
    public void countTagsByPid() {
    }

    @Test
    public void getTagsByPid() {
    }

    @Test
    public void getByPid() {
    }

    @Test
    public void removeProblemAllTags() {
        programProblemTagService.removeProblemAllTags(1);
    }

    @Test
    public void removeProblemTag() {
    }
}