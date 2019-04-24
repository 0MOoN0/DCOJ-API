package com.dcoj.dao;

import com.dcoj.entity.ProblemUserEntity;
import com.dcoj.entity.example.ProblemUserEntityExample;
import com.dcoj.judge.ResultEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ProblemUser测试类
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemUserMapperTest {

    @Autowired
    private ProblemUserMapper problemUserMapper;

    @Test
    public void testSave(){
        ProblemUserEntity userEntity = new ProblemUserEntity();
        userEntity.setPid(1);
        userEntity.setStatus(ResultEnum.AC);
        userEntity.setUid(1);
        problemUserMapper.insert(userEntity);
    }

    @Test
    public void testUpdate(){
        ProblemUserEntity userEntity = new ProblemUserEntity();
        userEntity.setPid(1);
        userEntity.setStatus(ResultEnum.WA);
        userEntity.setUid(1);

        ProblemUserEntityExample example = new ProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(1).andUidEqualTo(1);
        problemUserMapper.updateByExample(userEntity, example);
    }

    @Test
    public void testSelect(){
        ProblemUserEntityExample example = new ProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(1).andUidEqualTo(1);
        List<ProblemUserEntity> problemUserEntities = problemUserMapper.selectByExample(example);
        problemUserEntities.parallelStream().forEach(problemUserEntity -> System.out.println(problemUserEntity));
    }

    @Test
    public void testDelete(){
        ProblemUserEntityExample example = new ProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(1).andUidEqualTo(1);
        problemUserMapper.deleteByExample(example);
    }

    @Test
    public void testCountByExample(){
        ProblemUserEntityExample problemUserEntityExample = new ProblemUserEntityExample();
        problemUserEntityExample.createCriteria().andUidEqualTo(1).andPidEqualTo(3);
        long countByExample = problemUserMapper.countByExample(problemUserEntityExample);
        System.out.println(countByExample);
    }
}
