package com.dcoj.service.impl;

import com.dcoj.service.ObjectProblemService;
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
public class ObjectProblemServiceImplTest {

    @Autowired
    private ObjectProblemService objectProblemService;

    @Test
    public void updateProblemTimes(){
        int updateCord = objectProblemService.updateProblemTimesByUidPid(7, (byte) 1);
        System.out.println("更新了 "+ updateCord +" 条数据");
    }

}
