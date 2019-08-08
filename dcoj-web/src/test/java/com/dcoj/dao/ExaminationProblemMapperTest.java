package com.dcoj.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
        // TODO: Leon 20190808 FIXBUGS
        List<ExaminationProblemMapper> list = examinationProblemMapper.listByExamId(1);
        Optional.ofNullable(list).ifPresent(examProblem->examProblem.forEach(System.out::println));
    }

}
