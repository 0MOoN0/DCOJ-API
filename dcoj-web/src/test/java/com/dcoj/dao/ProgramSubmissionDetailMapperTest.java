package com.dcoj.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ProgramSubmissionDetailEntity;
import com.dcoj.entity.example.ProgramSubmissionDetailEntityExample;
import com.dcoj.judge.ResultEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramSubmissionDetailMapperTest {

    @Autowired
    private ProgramSubmissionDetailMapper programSubmissionDetailMapper;

    @Test
    public void testSave(){
        ProgramSubmissionDetailEntity programSubmissionDetailEntity = new ProgramSubmissionDetailEntity();
        JSONObject resultJson = new JSONObject();
        // memory
        resultJson.put("memory", 9);
        // testCases
        JSONArray testCases = new JSONArray();
        JSONObject testCase1 = new JSONObject();
        testCase1.put("memory", 8.552448f);
        testCase1.put("result", ResultEnum.AC);
        testCase1.put("time", 0.104);
        testCase1.put("error_message", null);
        JSONObject testCase2 = new JSONObject();
        testCase2.put("memory", 8.552448f);
        testCase2.put("result", ResultEnum.WA);
        testCase2.put("time", 0.104);
        testCase2.put("error_message", null);
        testCases.add(testCase1);
        testCases.add(testCase2);
        resultJson.put("test_cases", testCases);
        // result
        resultJson.put("result", ResultEnum.AC);
        // time
        resultJson.put("time", 0.12);
        programSubmissionDetailEntity.setJudgeDetail(resultJson);
        programSubmissionDetailEntity.setSourceCode(0);
        programSubmissionDetailEntity.setSubId(14);

        int i = programSubmissionDetailMapper.insertSelective(programSubmissionDetailEntity);
        System.out.println("============="+i+" sql was successfully executed");
        System.out.println("ProgramSubmissionDetail ID is "+programSubmissionDetailEntity.getSdId());
    }

    // 测试修改
    @Test
    public void testUpdate(){
        ProgramSubmissionDetailEntity programSubmissionDetailEntity = new ProgramSubmissionDetailEntity();
        programSubmissionDetailEntity.setSourceCode(123);
        programSubmissionDetailEntity.setSdId(2);
        int i = programSubmissionDetailMapper.updateByPrimaryKeySelective(programSubmissionDetailEntity);
        System.out.println("============="+i + " update SQL was successfully executed !");
    }

    @Test
    public void testSelect(){
        ProgramSubmissionDetailEntity programSubmissionDetailEntity = programSubmissionDetailMapper.selectByPrimaryKey(1);
        System.out.println(programSubmissionDetailEntity);
        ProgramSubmissionDetailEntityExample programSubmissionDetailEntityExample = new ProgramSubmissionDetailEntityExample();
        programSubmissionDetailEntityExample.createCriteria()
                .andSourceCodeEqualTo(123)
                .andSubIdEqualTo(14);
        List<ProgramSubmissionDetailEntity> programSubmissionDetailEntities = programSubmissionDetailMapper.selectByExample(programSubmissionDetailEntityExample);
        System.out.println(programSubmissionDetailEntities.get(0));
    }

    @Test
    public void testDelete(){
        programSubmissionDetailMapper.deleteByPrimaryKey(1);
        ProgramSubmissionDetailEntityExample programSubmissionDetailEntityExample = new ProgramSubmissionDetailEntityExample();
        programSubmissionDetailEntityExample.createCriteria()
                .andSourceCodeEqualTo(123);
        programSubmissionDetailMapper.deleteByExample(programSubmissionDetailEntityExample);
    }


}
