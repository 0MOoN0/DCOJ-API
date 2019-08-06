package com.dcoj.dao;

import com.dcoj.entity.ObjectProblemUserEntity;
import com.dcoj.entity.example.ObjectProblemUserEntityExample;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectProblemUserMapperTest {

    @Autowired
    private ObjectProblemUserMapper objectProblemUserMapper;

    @Test
    public void testSave(){
        ObjectProblemUserEntity objectProblemUserEntity = new ObjectProblemUserEntity();
        objectProblemUserEntity.setPid(10);
        objectProblemUserEntity.setPid(21);
        objectProblemUserEntity.setUid(2);
        int record = objectProblemUserMapper.insertSelective(objectProblemUserEntity);
        System.out.println(record);
    }

    @Test
    public void testSelect(){
        ObjectProblemUserEntityExample example = new ObjectProblemUserEntityExample();
        ObjectProblemUserEntityExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(4);
        criteria.andUidEqualTo(1);
        List<ObjectProblemUserEntity> objectProblemUserEntities = objectProblemUserMapper.selectByExample(example);
        Optional.ofNullable(objectProblemUserEntities).ifPresent(x->x.forEach(System.out::println));
    }

    @Test
    public void testUpdate(){
        ObjectProblemUserEntity entity = new ObjectProblemUserEntity();
        entity.setPid(4);
        entity.setUid(2);
        ObjectProblemUserEntityExample example = new ObjectProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(4).andUidEqualTo(1);
        int i = objectProblemUserMapper.updateByExampleSelective(entity, example);
        System.out.println("更新了 "+i +" 条数据");
    }

    @Test
    public void delete(){
        ObjectProblemUserEntityExample example = new ObjectProblemUserEntityExample();
        example.createCriteria().andUidEqualTo(2).andPidEqualTo(21);
        int i = objectProblemUserMapper.deleteByExample(example);
        System.out.println("删除了了 "+i +" 条数据");
    }


}
