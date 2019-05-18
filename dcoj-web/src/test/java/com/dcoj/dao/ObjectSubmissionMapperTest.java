package com.dcoj.dao;

import com.dcoj.entity.ObjectProblemEntity;
import com.dcoj.entity.ObjectSubmissionEntity;
import com.dcoj.entity.example.ObjectSubmissionEntityExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

/**
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectSubmissionMapperTest {

    @Autowired
    private ObjectSubmissionMapper objectSubmissionMapper;

    @Test
    public void testSave() {
        ObjectSubmissionEntity objectSubmissionEntity = new ObjectSubmissionEntity();
        objectSubmissionEntity.setAnswer("A");
        objectSubmissionEntity.setObjectProblemId(1);
        objectSubmissionEntity.setResultStatus((byte) 1);
        objectSubmissionEntity.setUid(1);
        objectSubmissionMapper.insertSelective(objectSubmissionEntity);
    }

    @Test
    public void testUpdate() {
        ObjectSubmissionEntity objectSubmissionEntity = new ObjectSubmissionEntity();
        objectSubmissionEntity.setResultStatus((byte) 0);
        ObjectSubmissionEntityExample objectSubmissionEntityExample = new ObjectSubmissionEntityExample();
        objectSubmissionEntityExample.createCriteria()
                .andObjectSubmitIdEqualTo(1);
        objectSubmissionMapper.updateByExampleSelective(objectSubmissionEntity, objectSubmissionEntityExample);
    }

    @Test
    public void testSelect() {
        ObjectSubmissionEntityExample objectSubmissionEntityExample = new ObjectSubmissionEntityExample();
        objectSubmissionEntityExample.createCriteria()
                .andObjectSubmitIdEqualTo(1);
        List<ObjectSubmissionEntity> objectSubmissionEntities = objectSubmissionMapper.selectByExample(objectSubmissionEntityExample);
        Optional.ofNullable(objectSubmissionEntities)
                .ifPresent(
                        entitys -> entitys.parallelStream()
                                .forEach(System.out::print)
                );
    }

    @Test
    public void testDelete() {
        ObjectSubmissionEntityExample objectSubmissionEntityExample = new ObjectSubmissionEntityExample();
        objectSubmissionEntityExample.createCriteria()
                .andObjectSubmitIdEqualTo(1);
        int i = objectSubmissionMapper.deleteByExample(objectSubmissionEntityExample);
        System.out.println("======" + i);
    }

}
