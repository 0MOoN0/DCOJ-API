package com.dcoj.service.impl;

import com.dcoj.entity.ObjectSubmissionEntity;
import com.dcoj.service.ObjectSubmissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectSubmissionServiceImplTest {

    @Autowired
    private ObjectSubmissionService objectSubmissionService;


    @Test
    public void testSave() {
        int c = objectSubmissionService.save(2, 2, 1, "C");
        System.out.println(c);
    }

    @Test
    public void testList(){
        List<ObjectSubmissionEntity> objectSubmissionEntities = objectSubmissionService.listObjectSubmissionByUid(2);
        objectSubmissionEntities.parallelStream().forEach(System.out::println);
    }


}
