package com.dcoj.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectProblemProgramTagMapperTest {

    @Autowired
    private ObjectProblemTagMapper objectProblemTagMapper;

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void save() {
        objectProblemTagMapper.save(1,1);
        objectProblemTagMapper.save(2,1);
        objectProblemTagMapper.save(3,1);
        objectProblemTagMapper.save(4,1);
        objectProblemTagMapper.save(5,1);
        objectProblemTagMapper.save(6,1);
        objectProblemTagMapper.save(2,7);
    }

    @Test
    public void countTagsByObjectProblemId() {
        System.out.println(objectProblemTagMapper.countTagsByObjectProblemId(2));
    }

    @Test
    public void getTagsByObjectProblemId() {
        List<Integer> list = objectProblemTagMapper.getTagsByObjectProblemId(2);
        list.forEach(System.out::println);
    }

    @Test
    public void removeProblemAllTags() {
        System.out.println(objectProblemTagMapper.removeProblemAllTags(2));
    }

    @Test
    public void removeProblemTag() {
        //System.out.println(objectProblemTagMapper.removeProblemTag(1, 1));
    }
}