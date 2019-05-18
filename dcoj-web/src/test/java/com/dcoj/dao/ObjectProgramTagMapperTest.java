package com.dcoj.dao;

import com.dcoj.entity.ObjectTagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Action;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectProgramTagMapperTest {

    @Autowired
    private ObjectTagMapper objectTagMapper;

    @Test
    public void removeByPrimaryKey() {
        System.out.println(objectTagMapper.removeByPrimaryKey(4));
    }

    @Test
    public void insert() {
        objectTagMapper.save("Java");
        objectTagMapper.save("Python");
        objectTagMapper.save("C/C++");
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(objectTagMapper.getByPrimaryKey(1));
        System.out.println(objectTagMapper.getByPrimaryKey(2));
        System.out.println(objectTagMapper.getByPrimaryKey(3));
        System.out.println(objectTagMapper.getByPrimaryKey(4));
    }

    @Test
    public void getByTagName() {
        System.out.println(objectTagMapper.getByTagName("Java"));
        System.out.println(objectTagMapper.getByTagName("Python"));
        System.out.println(objectTagMapper.getByTagName("xx"));
        System.out.println(objectTagMapper.getByTagName("x"));
    }


    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void listAll() {
        List<ObjectTagEntity> list = objectTagMapper.listAll();
        list.forEach(System.out::println);
    }

    @Test
    public void updateByPrimaryKey() {
        objectTagMapper.updateByPrimaryKey(1, "JXXX");
    }

    @Test
    public void updateTagUsedTimes() {
        System.out.println(objectTagMapper.updateTagUsedTimes(1, true));
        System.out.println(objectTagMapper.updateTagUsedTimes(1, true));
        System.out.println(objectTagMapper.updateTagUsedTimes(1, false));
    }

    @Test
    public void countTags() {
        System.out.println(objectTagMapper.countTags());
    }
}