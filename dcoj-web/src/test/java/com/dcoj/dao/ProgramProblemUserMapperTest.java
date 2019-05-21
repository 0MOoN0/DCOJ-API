package com.dcoj.dao;

import com.dcoj.entity.ProgramProblemUserEntity;
import com.dcoj.entity.example.ProgramProblemUserEntityExample;
import com.dcoj.judge.ResultEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ProblemUser测试类
 *
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramProblemUserMapperTest {

    @Autowired
    private ProgramProblemUserMapper problemUserMapper;

    @Test
    public void testSave() {
        ProgramProblemUserEntity userEntity = new ProgramProblemUserEntity();
        userEntity.setPid(1);
        userEntity.setStatus(ResultEnum.AC);
        userEntity.setUid(1);
        problemUserMapper.insert(userEntity);
    }

    @Test
    public void testUpdate() {
        ProgramProblemUserEntity userEntity = new ProgramProblemUserEntity();
        userEntity.setPid(1);
        userEntity.setStatus(ResultEnum.WA);
        userEntity.setUid(1);

        ProgramProblemUserEntityExample example = new ProgramProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(1).andUidEqualTo(1);
        problemUserMapper.updateByExample(userEntity, example);
    }

    @Test
    public void testSelect() {
        ProgramProblemUserEntityExample example = new ProgramProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(1).andUidEqualTo(1);
        List<ProgramProblemUserEntity> problemUserEntities = problemUserMapper.selectByExample(example);
        problemUserEntities.parallelStream().forEach(problemUserEntity -> System.out.println(problemUserEntity));
    }

    @Test
    public void testDelete() {
        ProgramProblemUserEntityExample example = new ProgramProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(1).andUidEqualTo(1);
        problemUserMapper.deleteByExample(example);
    }

    @Test
    public void testCountByExample() {
        ProgramProblemUserEntityExample problemUserEntityExample = new ProgramProblemUserEntityExample();
        problemUserEntityExample.createCriteria().andUidEqualTo(1).andPidEqualTo(3);
        long countByExample = problemUserMapper.countByExample(problemUserEntityExample);
        System.out.println(countByExample);
    }
}
