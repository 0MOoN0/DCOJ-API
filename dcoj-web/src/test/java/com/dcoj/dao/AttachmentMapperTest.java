package com.dcoj.dao;

import com.dcoj.entity.AttachmentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

/**
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AttachmentMapperTest {

    @Autowired
    private AttachmentMapper attachmentMapper;


    @Test
    public void testInsert(){
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setStatus((byte) 0);
        attachmentEntity.setUrl("XXX/XXX/XXX");
        attachmentEntity.setUid(0);
        attachmentEntity.setUploadTime(new Timestamp(System.currentTimeMillis()));
        attachmentMapper.insert(attachmentEntity);
        System.out.println("EntityID =============== "+attachmentEntity.getAid());
    }

    @Test
    public void testUpdateByExample(){

    }

}
