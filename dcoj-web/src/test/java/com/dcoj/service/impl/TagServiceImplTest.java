package com.dcoj.service.impl;

import com.dcoj.entity.TagEntity;
import com.dcoj.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author WANGQING
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagServiceImplTest {

    @Autowired
    private TagService tagService;

    @Test
    public void save() {
        tagService.save("标签1");
        tagService.save("标签2");
        tagService.save("标签3");
    }

    @Test
    public void getByName() {
        TagEntity tagEntity1 = tagService.getByName("标签1");
        TagEntity tagEntity2 = tagService.getByName("标签2");
        TagEntity tagEntity3 = tagService.getByName("标签3");
        System.out.println(tagEntity1);
        System.out.println(tagEntity2);
        System.out.println(tagEntity3);
    }

    @Test
    public void removeTag() {
        tagService.removeTag("标签1");
    }

    @Test
    public void listAll() {
        List<TagEntity> list = tagService.listAll();
        for (TagEntity tag : list ) {
            System.out.println(tag);
        }
    }

    @Test
    public void updateTagName() {
        tagService.updateTagName("标签2","修改后的标签2");
        //tagService.updateTagName("标签1","修改后的标签1");
    }

    @Test
    public void updateTagUsedTimes() {
        tagService.updateTagUsedTimes("标签2");
    }

    @Test
    public void countTags() {
        System.out.println(tagService.countTags());
    }
}