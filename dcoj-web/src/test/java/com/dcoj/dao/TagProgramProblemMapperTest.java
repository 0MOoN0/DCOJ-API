package com.dcoj.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 标签题目关联测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagProgramProblemMapperTest {

    @Autowired
    private ProgramProblemTagMapper programProblemTagMapper;

    @Test
    public void save() {
        System.out.println(programProblemTagMapper.save(1, 1));
        System.out.println(programProblemTagMapper.save(1, 2));
        System.out.println(programProblemTagMapper.save(1, 3));
        System.out.println(programProblemTagMapper.save(1, 4));
        System.out.println(programProblemTagMapper.save(1, 5));
        System.out.println("==============================================================================================");
        System.out.println(programProblemTagMapper.save(2, 1));
        System.out.println(programProblemTagMapper.save(2, 2));
        System.out.println(programProblemTagMapper.save(2, 3));
        System.out.println(programProblemTagMapper.save(2, 4));
        System.out.println(programProblemTagMapper.save(2, 5));
    }

    @Test
    public void countTagsByPid() {
//        System.out.println(programProblemTagMapper.countTagsByPid(1));
//        System.out.println(programProblemTagMapper.countTagsByPid(2));
    }

    @Test
    public void getTagsByPid() {
//        List<Integer> list1 = programProblemTagMapper.getTagsByPid(1);
//        List<Integer> list2 = programProblemTagMapper.getTagsByPid(2);
//        list1.forEach(System.out::print);
//        System.out.println();
//        System.out.println("========================");
//        list2.forEach(System.out::print);
//        System.out.println();
    }

    @Test
    public void getByPid() {
//        System.out.println(programProblemTagMapper.getTagsByPid(1));
//        System.out.println(programProblemTagMapper.getTagsByPid(2));
    }

    @Test
    public void removeProblemTag() {
//        System.out.println(programProblemTagMapper.removeProblemTag(1,1));
//        System.out.println(programProblemTagMapper.removeProblemTag(1,1));
//        System.out.println(programProblemTagMapper.removeProblemTag(1,2));

    }

    @Test
    public void removeProblemAllTags() {
        System.out.println(programProblemTagMapper.removeProblemAllTags(1));

    }
}