package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.service.ProblemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemServiceImplTest {

    @Resource
    private ProblemService problemService;

    @Test
    public void countProblems() {
        System.out.println(problemService.countProblems());
        System.out.println(problemService.countProblems(0));
        System.out.println(problemService.countProblems(1));
        System.out.println(problemService.countProblems(2));
        System.out.println(problemService.countProblems(3));
    }


    @Test
    public void removeProblem() {
        problemService.removeProblem(0L);
    }

    @Test
    public void updateProblem() {
        ProblemEntity problemEntity2 =  problemService.getById(2);
        problemEntity2.setMemory(1);
        JSONArray tags2 = new JSONArray();
        tags2.add(0,1);
        tags2.add(1,2);
        tags2.add(2,3);
        problemService.updateProblem(tags2,problemEntity2);
    }

    @Test
    public void listAll() {
        List<ProblemEntity> list = problemService.listAll();
        for (int i = 0; i < list.size(); i++) {
            ProblemEntity problemEntity =  list.get(i);
            System.out.println(problemEntity);
        }
    }

    @Test
    public void listByType() {
        List<ProblemEntity> list = problemService.listByType(0);
        for (int i = 0; i < list.size(); i++) {
            ProblemEntity problemEntity =  list.get(i);
            System.out.println(problemEntity);
        }
    }

    @Test
    public void getById() {
        ProblemEntity problemEntity1 =  problemService.getById(1);
        ProblemEntity problemEntity2 =  problemService.getById(2);
        ProblemEntity problemEntity3 =  problemService.getById(3);
        System.out.println(problemEntity1);
        System.out.println(problemEntity2);
        System.out.println(problemEntity3);
    }

    @Test
    public void save() {
        ProblemEntity problemEntity1 = new ProblemEntity();
        problemEntity1.setType(0);
        problemEntity1.setDifficult(0);
        JSONObject des = new JSONObject();
        des.put("des","这是一道选择题");
        problemEntity1.setDescription(des);
        problemEntity1.setSubmitTimes(0);
        problemEntity1.setAnswer("这是答案");

        ProblemEntity problemEntity2 = new ProblemEntity();
        problemEntity2.setType(3);
        problemEntity2.setDifficult(0);
        JSONObject des1 = new JSONObject();
        des1.put("des","这是一道编程题");
        problemEntity2.setDescription(des);
        problemEntity2.setSubmitTimes(0);
        problemEntity2.setMemory(0);
        JSONObject input = new JSONObject();
        input.put("input","输入示例");
        problemEntity2.setInputFormat(input);
        JSONObject output = new JSONObject();
        input.put("output","输出示例");

        ProblemEntity problemEntity3 = new ProblemEntity();
        problemEntity3.setType(0);
        problemEntity3.setDifficult(0);
        JSONObject des2 = new JSONObject();
        des2.put("des","这是一道选择题");
        problemEntity3.setDescription(des);
        problemEntity3.setSubmitTimes(0);
        problemEntity3.setAnswer("这是答案");

        JSONArray tags1 = new JSONArray();
        tags1.add(0,1);
        tags1.add(1,2);
        tags1.add(2,3);

        JSONArray tags2 = new JSONArray();
        tags2.add(0,4);
        tags2.add(1,5);
        tags2.add(2,6);

        JSONArray tags3 = new JSONArray();
        tags3.add(0,2);
        tags3.add(1,4);
        tags3.add(2,6);

        problemService.save(tags1,problemEntity1);
        problemService.save(tags2,problemEntity2);
        problemService.save(tags3,problemEntity3);
    }

    //TODO:未测试
    @Test
    public void updateProblemTimes() {
    }

}