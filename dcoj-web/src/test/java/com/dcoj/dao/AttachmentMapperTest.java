package com.dcoj.dao;

import com.dcoj.entity.AttachmentEntity;
import com.dcoj.entity.example.AttachmentEntityExample;
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


    /**
     * 测试增加
     */
    @Test
    public void testInsert() {
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setStatus((byte) 0);
        attachmentEntity.setUrl("XXX/XXX/XXX");
        attachmentEntity.setUid(0);
        attachmentEntity.setUploadTime(new Timestamp(System.currentTimeMillis()));
        attachmentMapper.insert(attachmentEntity);
        System.out.println("EntityID =============== " + attachmentEntity.getAid());
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdateByExample() {
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setAid(1);
        attachmentEntity.setStatus((byte) 1);
        attachmentMapper.updateByPrimaryKeySelective(attachmentEntity);
        AttachmentEntityExample attachmentEntityExample = new AttachmentEntityExample();
        attachmentEntity.setAid(null);
        attachmentEntity.setStatus((byte) 0);
        attachmentEntityExample.createCriteria()
                .andAidEqualTo(1);
        attachmentMapper.updateByExampleSelective(attachmentEntity, attachmentEntityExample);
    }

    @Test
    public void testSelect() {
        AttachmentEntityExample attachmentEntityExample = new AttachmentEntityExample();
        attachmentEntityExample.createCriteria()
                .andAidEqualTo(1);
        attachmentMapper.selectByExample(attachmentEntityExample);
    }

    @Test
    public void testDelete() {
        AttachmentEntityExample attachmentEntityExample = new AttachmentEntityExample();
        attachmentEntityExample.createCriteria()
                .andAidEqualTo(1);
        attachmentMapper.deleteByExample(attachmentEntityExample);
    }

}
