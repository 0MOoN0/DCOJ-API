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
        int i1 = objectProblemUserService.insertOrUpdate(22,3, (byte) 1);
        System.out.println("插入了 "+ i1 +" 条数据");

        int i2 = objectProblemUserService.insertOrUpdate(22,3, (byte) 0);
        System.out.println("更新了 "+ i2 +" 条数据");
    }

}
