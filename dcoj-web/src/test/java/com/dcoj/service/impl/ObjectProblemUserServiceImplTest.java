package com.dcoj.service.impl;

import com.dcoj.entity.ObjectProblemUserEntity;
import com.dcoj.service.ObjectProblemUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leon
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ObjectProblemUserServiceImplTest {

    @Autowired
    private ObjectProblemUserService objectProblemUserService;

    @Test
    public void testUpdateOrSave(){
        // 用于测试增加数据
        ObjectProblemUserEntity entity1 = new ObjectProblemUserEntity();
        entity1.setPid(21);
        entity1.setUid(3);
        entity1.setStatus((byte)1);
        int i1 = objectProblemUserService.insertOrUpdate(entity1);
        System.out.println("插入了 "+ i1 +" 条数据");

        ObjectProblemUserEntity entity2 = new ObjectProblemUserEntity();
        entity2.setPid(21);
        entity2.setUid(3);
        entity2.setStatus((byte) 0);
        int i2 = objectProblemUserService.insertOrUpdate(entity2);
    }

}
