package com.dcoj.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ExaminationProblemEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Leon
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExaminationProblemMapperTest {

    @Autowired
    private ExaminationProblemMapper examinationProblemMapper;

    @Test
    public void testListByExamId(){
        Map<Integer, ExaminationProblemEntity> map = examinationProblemMapper.listByExamId(1);
//        List<ExaminationProblemEntity> list = examinationProblemMapper.selectAll();
        Optional.ofNullable(map).ifPresent(examProblem->examProblem.forEach
                ((integer, examinationProblemEntity) -> System.out.println("The key is +"+integer+" The value is "+examinationProblemEntity)));
    }

    @Test
    public void testUpdateByLocateSelective(){
        ExaminationProblemEntity entity = new ExaminationProblemEntity();
        entity.setExamId(2);
        entity.setExamProblemLocate(2);
        entity.setScore(10);
        examinationProblemMapper.updateByLocateSelective(entity);
    }

    @Test
    public void testSaveAll(){
        ExaminationProblemEntity entity1 = new ExaminationProblemEntity();
        entity1.setExamId(2);
        entity1.setProblemType(1);
        entity1.setProblemId(21);
        JSONObject lang = new JSONObject();
        JSONArray langs = new JSONArray();
        langs.add("JAVA8");
        langs.add("PYTHON32");
        langs.add("c");
        lang.put("lang", langs);
        entity1.setLang(lang);
        entity1.setExamProblemLocate(1);
        entity1.setScore(25);
        ExaminationProblemEntity entity2 = new ExaminationProblemEntity();
        entity2.setExamId(2);
        entity2.setProblemType(2);
        entity2.setProblemId(1);
        entity2.setScore(10);
        entity2.setExamProblemLocate(2);
        List<ExaminationProblemEntity> list = Arrays.asList(entity1, entity2);
        System.out.println(examinationProblemMapper.saveAll(list));
    }
}
