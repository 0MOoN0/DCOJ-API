package com.dcoj.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ProblemEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * ProblemMapper测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemMapperTest {

    @Autowired
    private ProblemMapper problemMapper;

    @Test
    public void countProblems() {
        System.out.println(problemMapper.countProblems());
    }

    @Test
    public void countProblemsByType() {
        System.out.println(problemMapper.countProblemsByType(0));
        System.out.println(problemMapper.countProblemsByType(3));
    }

    @Test
    public void removeByPid() {
        System.out.println(problemMapper.removeByPid(1));
        System.out.println(problemMapper.removeByPid(2));
        System.out.println(problemMapper.removeByPid(3));
        System.out.println(problemMapper.removeByPid(4));

    }

    @Test
    public void updateProblem() {
        //选择题1
        ProblemEntity problemEntity1 = new ProblemEntity();
        problemEntity1.setPid(1);
        problemEntity1.setType(0);
        JSONObject des1 = new JSONObject();
        des1.put("des","这是一道选择题");
        problemEntity1.setDescription(des1);
        problemEntity1.setDifficult(2);

        problemEntity1.setAnswer("A");

        //编程题1
        ProblemEntity problemEntity3 = new ProblemEntity();
        problemEntity3.setPid(3);
        problemEntity3.setType(3);
        problemEntity3.setDescription(null);
        problemEntity3.setDifficult(2);
        problemEntity3.setTitle("编程题目标题");
        problemEntity3.setInputFormat(null);
        problemEntity3.setOutputFormat(null);
        problemEntity3.setSamples(null);
        problemEntity3.setRunTime(2);
        problemEntity3.setMemory(1);


        System.out.println(problemMapper.updateProblem(problemEntity1));
        System.out.println(problemMapper.updateProblem(problemEntity3));
    }

    @Test
    public void listAll() {
        List<ProblemEntity> list =  problemMapper.listAll();
        list.forEach(System.out::println);
    }

    @Test
    public void listByType() {
        List<ProblemEntity> list1 =  problemMapper.listByType(0);
        list1.forEach(System.out::println);
        System.out.println("========================");
        List<ProblemEntity> list2 =  problemMapper.listByType(3);
        list2.forEach(System.out::println);
    }

    @Test
    public void getById() {
        System.out.println(problemMapper.getById(1));
        System.out.println(problemMapper.getById(2));
        System.out.println(problemMapper.getById(3));
        System.out.println(problemMapper.getById(4));
        System.out.println(problemMapper.getById(5));
        System.out.println(problemMapper.getById(6));
    }

    @Test
    public void save() {
        //选择题1
        ProblemEntity problemEntity1 = new ProblemEntity();
        problemEntity1.setType(0);
        JSONObject des1 = new JSONObject();
        des1.put("des","这是一道选择题");
        problemEntity1.setDescription(des1);
        problemEntity1.setDifficult(1);

        problemEntity1.setAnswer("A");

        //选择题2
        ProblemEntity problemEntity2 = new ProblemEntity();
        problemEntity2.setType(0);
        JSONObject des2 = new JSONObject();
        des2.put("des","这是一道选择题2");
        problemEntity2.setDescription(des2);
        problemEntity2.setDifficult(2);

        problemEntity2.setAnswer("B");

        //编程题1
        ProblemEntity problemEntity3 = new ProblemEntity();
        problemEntity3.setType(3);
        problemEntity3.setDescription(null);
        problemEntity3.setDifficult(2);
        problemEntity3.setTitle("编程题目标题");
        problemEntity3.setInputFormat(null);
        problemEntity3.setOutputFormat(null);
        problemEntity3.setSamples(null);
        problemEntity3.setRunTime(1);
        problemEntity3.setMemory(1);

        problemMapper.save(problemEntity1);
        problemMapper.save(problemEntity2);
        problemMapper.save(problemEntity3);

    }
}