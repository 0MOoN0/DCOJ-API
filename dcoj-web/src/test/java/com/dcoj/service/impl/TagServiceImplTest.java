//package com.dcoj.service.impl;
//
//import com.dcoj.entity.TagEntity;
//import com.dcoj.service.TagService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//
///**
// * @author WANGQING
// * @description
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TagServiceImplTest {
//
//    @Autowired
//    private TagService tagService;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Test
//    public void save() {
////        tagService.save("标签1");
//////        tagService.save("标签2");
//////        tagService.save("标签3");
////        tagService.save("标签4");
////        tagService.save("标签5");
////        tagService.save("标签6");
//
//        TagEntity tagEntity = new TagEntity();
//        tagEntity.setObjectId("5c79205ccd64aa1b8816504e");
//        tagEntity.setTagName("标签1");
//        tagEntity.setTid(1L);
//        tagEntity.setUsedTimes(0);
//        tagEntity.setDeleted(false);
//        mongoTemplate.save(tagEntity);
//    }
//
//    @Test
//    public void getByName() {
//        TagEntity tagEntity1 = tagService.getByName("标签1");
//        TagEntity tagEntity2 = tagService.getByName("标签2");
//        TagEntity tagEntity3 = tagService.getByName("标签3");
//        System.out.println(tagEntity1);
//        System.out.println(tagEntity2);
//        System.out.println(tagEntity3);
//    }
//
//    @Test
//    public void getById(){
//        TagEntity tagEntity1 = tagService.getById(1);
//        TagEntity tagEntity2 = tagService.getById(2);
//        System.out.println(tagEntity1);
//        System.out.println(tagEntity2);
//    }
//
//
//    @Test
//    public void removeTag() {
//        tagService.removeTag("标签1");
//    }
//
//    @Test
//    public void listAll() {
//        List<TagEntity> list = tagService.listAll();
//        for (TagEntity tag : list ) {
//            System.out.println(tag);
//        }
//    }
//
//    @Test
//    public void updateTagName() {
//        tagService.updateTagName("标签2","修改后的标签2");
//        //tagService.updateTagName("标签1","修改后的标签1");
//    }
//
//    @Test
//    public void updateTagUsedTimes() {
//        tagService.updateTagUsedTimes(1,true);
//        tagService.updateTagUsedTimes(2,true);
//        tagService.updateTagUsedTimes(2,true);
//
//
//
//    }
//
//    @Test
//    public void countTags() {
//        System.out.println(tagService.countTags());
//    }
//}