//package com.dcoj.service.impl;
//
//import com.dcoj.service.TagProblemService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * @author WANGQING
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TagProblemServiceImplTest {
//
//    @Autowired
//    private TagProblemService tagProblemService;
//
//    @Test
//    public void saveProblemTag() {
//
//    }
//
//    @Test
//    public void countTagProblems() {
//        System.out.println(tagProblemService.countTagProblems(1));
//        System.out.println(tagProblemService.countTagProblems(2));
//    }
//
//    @Test
//    public void getProblemTags() {
//        List<Long> list = tagProblemService.getProblemTags(2);
//        for (int i = 0; i < list.size(); i++) {
//            Long aLong =  list.get(i);
//            System.out.println(aLong);
//        }
//    }
//
//    @Test
//    public void getByPid() {
//    }
//}