package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.service.ProblemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 题目业务层测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemServiceImplTest {

    @Autowired
    private ProblemService problemService;

    @Test
    public void countProblems() {
        System.out.println(problemService.countProblems());
    }

    @Test
    public void countProblemsByType() {
        System.out.println(problemService.countProblemsByType(0));
        System.out.println(problemService.countProblemsByType(1));
        System.out.println(problemService.countProblemsByType(2));
        System.out.println(problemService.countProblemsByType(3));
    }

    @Test
    public void removeByPid() {
        problemService.removeByPid(1);
        System.out.println(problemService.getById(1));
    }

    @Test
    public void updateProblem() {
        ProblemEntity problemEntity = problemService.getById(1);
        JSONArray tags = new JSONArray();
        problemEntity.setTitle("修改后的title");
        tags.add(1);
        tags.add(3);
        tags.add(6);
        tags.add(7);
        tags.add(8);
        problemService.updateProblem(1,tags, problemEntity);
    }

    @Test
    public void listAll() {
        problemService.listAll().forEach(System.out::println);
    }

    @Test
    public void listByType() {
        problemService.listByType(0).forEach(System.out::println);
    }

    @Test
    public void getById() {
        System.out.println(problemService.getById(1));
        System.out.println(problemService.getById(2));
        System.out.println(problemService.getById(3));
        System.out.println(problemService.getById(4));
    }

    @Test
    public void save() {
        ProblemEntity problemEntity3 = new ProblemEntity();
        problemEntity3.setType(3);
        problemEntity3.setDescription(null);
        problemEntity3.setDifficult(2);
        problemEntity3.setTitle("编程题目标题");
        problemEntity3.setInputFormat(null);
        problemEntity3.setOutputFormat(null);
        problemEntity3.setSamples(null);
        problemEntity3.setRunTime(2);
        problemEntity3.setMemory(1);
        JSONArray tags = new JSONArray();
        tags.add(1);
        tags.add(2);
        System.out.println(problemService.save(tags, problemEntity3));
    }

    @Test
    public void listProblemTagsByPid() {
        List<Map<String,Object>> list = problemService.listProblemTagsByPid(13);
        for (Map<String,Object>  map:list){
            map.forEach((k,v)-> System.out.println("k:"+k+" v:"+v));
        }
    }

    @Test
    public void testJSONArray(){
        JSONArray ja = new JSONArray();
        ja.add(1);
        ja.add("B");
        ja.add(3);
        System.out.println(ja.toString());
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println(ja.toJSONString());
    }
}