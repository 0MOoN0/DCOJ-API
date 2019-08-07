package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProgramProblemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * 题目业务层测试类
 *
 * @author WANGQING, Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramProblemServiceImplTest {

    @Autowired
    private ProgramProblemService programProblemService;

    @Test
    public void countProblems() {
//        System.out.println(programProblemService.countProblems());
    }

    @Test
    public void countProblemsByType() {
//        System.out.println(programProblemService.countProblemsByType(0));
//        System.out.println(programProblemService.countProblemsByType(1));
//        System.out.println(programProblemService.countProblemsByType(2));
//        System.out.println(programProblemService.countProblemsByType(3));
    }

    @Test
    public void removeByPid() {
//        programProblemService.removeByPid(1);
//        System.out.println(programProblemService.getById(1));
    }

    @Test
    public void updateProblem() {
//        ProgramProblemEntity programProblemEntity = programProblemService.getById(1);
        JSONArray tags = new JSONArray();
//        programProblemEntity.setTitle("修改后的title");
        tags.add(1);
        tags.add(3);
        tags.add(6);
        tags.add(7);
        tags.add(8);
//        programProblemService.updateProblem(1,tags, programProblemEntity);
    }

    @Test
    public void listAll() {
//        programProblemService.listAll(0).forEach(System.out::println);
    }

//    @Test
//    public void listByType() {
//        programProblemService.listByType(0).forEach(System.out::println);
//    }

    @Test
    public void getById() {
//        System.out.println(programProblemService.getById(1));
//        System.out.println(programProblemService.getById(2));
//        System.out.println(programProblemService.getById(3));
//        System.out.println(programProblemService.getById(4));
    }

    @Test
    public void save() {
        ProgramProblemEntity programProblemEntity3 = new ProgramProblemEntity();
//        programProblemEntity3.setType(3);
        programProblemEntity3.setDescription(null);
        programProblemEntity3.setDifficult(2);
        programProblemEntity3.setTitle("编程题目标题");
        programProblemEntity3.setInputFormat(null);
        programProblemEntity3.setOutputFormat(null);
        programProblemEntity3.setSamples(null);
        programProblemEntity3.setRunTime(2);
        programProblemEntity3.setMemory(1);
        JSONArray tags = new JSONArray();
        tags.add(1);
        tags.add(2);
        System.out.println(programProblemService.save(tags, programProblemEntity3));
    }

    @Test
    public void listProblemTagsByPid() {
//        List<Map<String,Object>> list = programProblemService.listProblemTagsByPid(13);
//        for (Map<String,Object>  map:list){
//            map.forEach((k,v)-> System.out.println("k:"+k+" v:"+v));
//        }
    }

    @Test
    public void testJSONArray() {
        JSONArray ja = new JSONArray();
        ja.add(1);
        ja.add("B");
        ja.add(3);
        System.out.println(ja.toString());
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println(ja.toJSONString());
    }

    @Test
    public void testUpdateProblemTimes(){
        programProblemService.updateProblemTimes(21, ResultEnum.WA);
    }
}