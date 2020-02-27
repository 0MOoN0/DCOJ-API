package com.dcoj.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ExaminationSubmissionDetailEntity;
import com.dcoj.entity.exam.AnswerEntity;
import com.dcoj.entity.example.ExaminationSubmissionDetailEntityExample;
import com.dcoj.judge.LanguageEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

/**
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExaminationSubmissionDetailMapperTest {

    @Autowired
    private ExaminationSubmissionDetailMapper examinationSubmissionDetailMapper;

    @Test
    public void testSave(){
        ExaminationSubmissionDetailEntity entity = new ExaminationSubmissionDetailEntity();
//        entity.setAnswerSheet(new JSONObject());
//        entity.setResultSheet(new JSONObject());
        entity.setExamSubmissionId(1);
        entity.setQueryableTime(new Timestamp(System.currentTimeMillis()));
//        examinationSubmissionDetailMapper.insert(entity);
        examinationSubmissionDetailMapper.insertSelective(entity);
    }

    @Test
    public void testUpdateAnswerSheet(){
        ExaminationSubmissionDetailEntity examinationSubmissionDetailEntity = new ExaminationSubmissionDetailEntity();
        examinationSubmissionDetailEntity.setExamSubmissionDetailId(1);
        AnswerEntity answer1 = new AnswerEntity();
        answer1.setAnswer(new StringBuffer("print(a)"));
        answer1.setLang(LanguageEnum.PYTHON27);
        answer1.setProblemId(9);
        answer1.setProblemType(1);
        AnswerEntity answer2 = new AnswerEntity();
        answer2.setAnswer(new StringBuffer("A"));
        answer2.setProblemId(9);
        answer2.setProblemType(2);
        answer2.setLang(null);
        JSONArray resultArray = new JSONArray();
        resultArray.add(answer1);
        resultArray.add(answer2);
        JSONObject answerSheet = new JSONObject();
        answerSheet.put("answer_sheet", resultArray);
        examinationSubmissionDetailEntity.setAnswerSheet(answerSheet);
        examinationSubmissionDetailMapper.updateByPrimaryKeySelective(examinationSubmissionDetailEntity);
    }

    //保存resultSheet测试
    @Test
    public void testResultSheet(){
        JSONArray resultArray = new JSONArray();
        JSONObject result1 = new JSONObject();
        result1.put("exam_problem_locate", 1);
        result1.put("score", 25);
        JSONObject result2 = new JSONObject();
        result2.put("exam_problem_locate", 2);
        result2.put("score", 25);
        resultArray.add(result1);
        resultArray.add(result2);
        JSONObject resultSheet = new JSONObject();
        resultSheet.put("result_sheet", resultArray);
        ExaminationSubmissionDetailEntity examinationSubmissionDetailEntity = new ExaminationSubmissionDetailEntity();
        examinationSubmissionDetailEntity.setExamSubmissionDetailId(1);
        examinationSubmissionDetailEntity.setResultSheet(resultSheet);
        examinationSubmissionDetailMapper.updateByPrimaryKeySelective(examinationSubmissionDetailEntity);
    }

    @Test
    public void testSelectWithoutQueryableTime(){
        ExaminationSubmissionDetailEntity examinationSubmissionDetailEntity = examinationSubmissionDetailMapper.selectByPrimaryKey(1);
        System.out.println(examinationSubmissionDetailEntity);
    }

}
