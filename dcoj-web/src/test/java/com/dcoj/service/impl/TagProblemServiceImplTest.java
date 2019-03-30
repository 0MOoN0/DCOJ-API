package com.dcoj.service.impl;

import com.dcoj.entity.TagProblemEntity;
import com.dcoj.service.TagProblemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * 题目标签关联业务层测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagProblemServiceImplTest {

    @Autowired
    private TagProblemService tagProblemService;


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
        tagProblemService.removeProblemAllTags(1);
    }

    @Test
    public void removeProblemTag() {
    }
}