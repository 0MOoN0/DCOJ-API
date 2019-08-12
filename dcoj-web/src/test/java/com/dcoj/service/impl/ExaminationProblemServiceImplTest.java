package com.dcoj.service.impl;

import com.dcoj.entity.ExaminationProblemEntity;
import com.dcoj.service.ExaminationProblemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Optional;

/**
 * @author Leon
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExaminationProblemServiceImplTest {

    @Autowired
    private ExaminationProblemService examinationProblemService;

    @Test
    public void testListByExamId(){
        Map<Integer, ExaminationProblemEntity> integerExaminationProblemEntityMap = examinationProblemService.listByExamId(1);
        Optional.ofNullable(integerExaminationProblemEntityMap).ifPresent(
                map -> map.forEach(((k,v)->System.out.println("The key is + "+k+" The value is "+v)))
        );
    }


}
