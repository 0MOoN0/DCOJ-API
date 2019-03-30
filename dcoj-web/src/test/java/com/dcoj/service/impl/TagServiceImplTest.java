package com.dcoj.service.impl;

import com.dcoj.entity.TagEntity;
import com.dcoj.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 标签业务层测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagServiceImplTest {

    @Autowired
    private TagService tagService;

    @Test
    public void save() {
        //tagService.save("标签111");
        tagService.save("标签888");
    }

    @Test
    public void getByName() {
        System.out.println(tagService.getByName("标签888"));
    }

    @Test
    public void getById() {
        System.out.println(tagService.getById(10));
    }

    @Test
    public void removeByTagName() {
        tagService.removeByTagName("标签888");
    }

    @Test
    public void removeById() {
        tagService.removeById(10);
    }

    @Test
    public void listAll() {
        tagService.listAll().forEach(System.out::println);
    }

    @Test
    public void updateByTid() {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTagName("标签88");
        tagEntity.setTid(10);
        tagService.updateByTid(tagEntity);
    }

    @Test
    public void updateTagUsedTimes() {
        tagService.updateTagUsedTimes(10,true);
        tagService.updateTagUsedTimes(10,true);
        tagService.updateTagUsedTimes(10,true);
        tagService.updateTagUsedTimes(10,false);
    }

    @Test
    public void countTags() {
        System.out.println(tagService.countTags());
    }
}