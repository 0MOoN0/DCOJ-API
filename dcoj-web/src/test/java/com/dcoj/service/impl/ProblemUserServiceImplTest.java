package com.dcoj.service.impl;

import com.dcoj.entity.ProblemUserEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProblemUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemUserServiceImplTest {

    @Autowired
    private ProblemUserService problemUserService;

    @Test
    public void testSave() {
        problemUserService.save(0, 0, ResultEnum.SE);
    }

    @Test
    public void testUpdate() {
        problemUserService.updateByPidUid(0, 0, ResultEnum.AC);
    }

    @Test
    public void testSelect() {
        ProblemUserEntity problemUserEntity = problemUserService.getByPidUid(0, 0);
        System.out.println(problemUserEntity);
    }

    @Test
    public void testListUserProblemsByUid() {
        List<Map<String, Object>> maps = problemUserService.listUserProblemHistory(0);
        Optional.ofNullable(maps).ifPresent(maps1 -> maps1.parallelStream().forEach(stringObjectMap -> stringObjectMap.entrySet().parallelStream()
                .forEach(stringObjectEntry -> {
                    System.out.println("The key is = "+stringObjectEntry.getKey()+"The value is = "+stringObjectEntry.getValue());
                })));
    }


}
