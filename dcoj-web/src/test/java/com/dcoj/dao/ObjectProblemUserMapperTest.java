package com.dcoj.dao;

import com.dcoj.entity.ObjectProblemUserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        int record = objectProblemUserMapper.updateByPrimaryKeySelective(objectProblemUserEntity);
        System.out.println(record);
    }


}
