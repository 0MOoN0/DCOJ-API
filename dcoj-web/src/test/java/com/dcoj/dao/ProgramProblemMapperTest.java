package com.dcoj.dao;

import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ProgramProblemEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ProblemMapper测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramProblemMapperTest {

    @Autowired
    private ProgramProblemMapper programProblemMapper;

//    @Test
//    public void countProblems() {
//        System.out.println(programProblemMapper.countProblems());
//    }
//
//    @Test
//    public void countProblemsByType() {
//        System.out.println(programProblemMapper.countProblemsByType(0));
//        System.out.println(programProblemMapper.countProblemsByType(3));
//    }
//
//    @Test
//    public void removeByPid() {
//        System.out.println(programProblemMapper.removeByPid(1));
//        System.out.println(programProblemMapper.removeByPid(2));
//        System.out.println(programProblemMapper.removeByPid(3));
//        System.out.println(programProblemMapper.removeByPid(4));
//
//    }
//
//    @Test
//    public void updateProblem() {
//        //选择题1
//        ProgramProblemEntity programProblemEntity1 = new ProgramProblemEntity();
//        programProblemEntity1.setPid(1);
//        programProblemEntity1.setType(0);
//        JSONObject des1 = new JSONObject();
//        des1.put("des","这是一道选择题");
//        programProblemEntity1.setDescription(des1);
//        programProblemEntity1.setDifficult(2);
//
//        programProblemEntity1.setAnswer("A");
//
//        //编程题1
//        ProgramProblemEntity programProblemEntity3 = new ProgramProblemEntity();
//        programProblemEntity3.setPid(3);
//        programProblemEntity3.setType(3);
//        programProblemEntity3.setDescription(null);
//        programProblemEntity3.setDifficult(2);
//        programProblemEntity3.setTitle("编程题目标题");
//        programProblemEntity3.setInputFormat(null);
//        programProblemEntity3.setOutputFormat(null);
//        programProblemEntity3.setSamples(null);
//        programProblemEntity3.setRunTime(2);
//        programProblemEntity3.setMemory(1);
//
//
//        System.out.println(programProblemMapper.updateProblem(programProblemEntity1));
//        System.out.println(programProblemMapper.updateProblem(programProblemEntity3));
//    }
//
    @Test
    public void listAll() {
//        List<ProgramProblemEntity> list =  programProblemMapper.listAll(0);
//        list.forEach(System.out::println);
    }
//
//    @Test
//    public void listByType() {
//        List<ProgramProblemEntity> list1 =  programProblemMapper.listByType(0);
//        list1.forEach(System.out::println);
//        System.out.println("========================");
//        List<ProgramProblemEntity> list2 =  programProblemMapper.listByType(3);
//        list2.forEach(System.out::println);
//    }
//
//    @Test
//    public void getById() {
//        System.out.println(programProblemMapper.getById(1));
//        System.out.println(programProblemMapper.getById(2));
//        System.out.println(programProblemMapper.getById(3));
//        System.out.println(programProblemMapper.getById(4));
//        System.out.println(programProblemMapper.getById(5));
//        System.out.println(programProblemMapper.getById(6));
//    }
//
//    @Test
//    public void save() {
//        //选择题1
//        ProgramProblemEntity programProblemEntity1 = new ProgramProblemEntity();
//        programProblemEntity1.setType(0);
//        JSONObject des1 = new JSONObject();
//        des1.put("des","这是一道选择题");
//        programProblemEntity1.setDescription(des1);
//        programProblemEntity1.setDifficult(1);
//
//        programProblemEntity1.setAnswer("A");
//
//        //选择题2
//        ProgramProblemEntity programProblemEntity2 = new ProgramProblemEntity();
//        programProblemEntity2.setType(0);
//        JSONObject des2 = new JSONObject();
//        des2.put("des","这是一道选择题2");
//        programProblemEntity2.setDescription(des2);
//        programProblemEntity2.setDifficult(2);
//
//        programProblemEntity2.setAnswer("B");
//
//        //编程题1
//        ProgramProblemEntity programProblemEntity3 = new ProgramProblemEntity();
//        programProblemEntity3.setType(3);
//        programProblemEntity3.setDescription(null);
//        programProblemEntity3.setDifficult(2);
//        programProblemEntity3.setTitle("编程题目标题");
//        programProblemEntity3.setInputFormat(null);
//        programProblemEntity3.setOutputFormat(null);
//        programProblemEntity3.setSamples(null);
//        programProblemEntity3.setRunTime(1);
//        programProblemEntity3.setMemory(1);
//
//        programProblemMapper.save(programProblemEntity1);
//        programProblemMapper.save(programProblemEntity2);
//        programProblemMapper.save(programProblemEntity3);
//    }
//
//    @Test
//    public void listProblemTagsByPid(){
//        List<Map<String, Object>> list =  programProblemMapper.listProblemTagsByPid(1);
//        for (Map<String,Object>  map:list){
//            map.forEach((k,v)-> System.out.println("k:"+k+" v:"+v));
//        }
//    }

    @Test
    public void listAllProblem(){
        List<Integer> list = Arrays.asList(1,3);
//        programProblemMapper.listAll(list,1,2,null).forEach(System.out::println);
        programProblemMapper.listAll(null,null,null,null).forEach(System.out::println);
    }



}