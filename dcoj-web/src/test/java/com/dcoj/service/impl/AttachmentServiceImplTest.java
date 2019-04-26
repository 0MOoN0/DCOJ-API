package com.dcoj.service.impl;

import com.dcoj.entity.AttachmentEntity;
import com.dcoj.service.AttachmentService;
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
public class AttachmentServiceImplTest {

    @Autowired
    private AttachmentService attachmentService;

    @Test
    public void testGetById(){
        AttachmentEntity byAid = attachmentService.getByAid(2);
        System.out.println(byAid);
    }

/*    @Test
    public void testGetURLById(){
        String attachmentURLByAid = attachmentService.getAttachmentURLByAid(2);
        System.out.println(attachmentURLByAid);
    }*/


}
