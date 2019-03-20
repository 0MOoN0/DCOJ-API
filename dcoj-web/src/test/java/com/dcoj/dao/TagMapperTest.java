package com.dcoj.dao;

import com.dcoj.entity.TagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.HTML;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * TagMapper测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagMapperTest {

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void save() {
//        tagMapper.save("标签111");
//        tagMapper.save("标签222");
//        tagMapper.save("标签333");
//        tagMapper.save("标签444");
        tagMapper.save("标签777");
    }

    @Test
    public void getByName() {
        System.out.println(tagMapper.getByName("标签111"));
        System.out.println(tagMapper.getByName("标签222"));
    }

    @Test
    public void getById() {
        System.out.println(tagMapper.getById(1));
        System.out.println(tagMapper.getById(2));
    }

    @Test
    public void removeByTagName() {
        System.out.println(tagMapper.getByName("标签222"));
        System.out.println(tagMapper.removeByTagName("标签222"));
        System.out.println(tagMapper.getByName("标签222"));
    }

    @Test
    public void removeById() {
        System.out.println(tagMapper.getById(1));
        System.out.println(tagMapper.removeById(1));
        System.out.println(tagMapper.getById(1));
    }

    @Test
    public void listAll() {
        List<TagEntity> list = tagMapper.listAll();
        list.forEach(System.out::println);
    }

    @Test
    public void updateByTid() {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTid(6);
        tagEntity.setTagName("修改后的标签");
        System.out.println(tagMapper.updateByTid(tagEntity));
    }

    @Test
    public void updateTagUsedTimes() {
        System.out.println(tagMapper.updateTagUsedTimes(1,true));
        System.out.println(tagMapper.updateTagUsedTimes(2,true));
        System.out.println(tagMapper.updateTagUsedTimes(3,true));
        System.out.println(tagMapper.updateTagUsedTimes(3,true));
        System.out.println(tagMapper.updateTagUsedTimes(3,true));
        System.out.println(tagMapper.updateTagUsedTimes(3,false));
        System.out.println(tagMapper.updateTagUsedTimes(3,false));
    }

    @Test
    public void countTags() {
        System.out.println(tagMapper.countTags());
    }



}