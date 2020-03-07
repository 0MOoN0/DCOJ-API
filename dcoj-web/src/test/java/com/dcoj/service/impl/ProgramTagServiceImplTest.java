package com.dcoj.service.impl;

import com.dcoj.service.ProgramProblemService;
import com.dcoj.service.ProgramProblemTagService;
import com.dcoj.service.ProgramTagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 标签业务层测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramTagServiceImplTest {

    @Autowired
    private ProgramTagService programTagService;

    @Autowired
    private ProgramProblemTagService programProblemTagService;

    @Test
    public void remove() {
        programProblemTagService.removeProblemAllTags(102);
    }

    @Test
    public void save() {
        //programTagService.save("标签111");
        programTagService.save("标签888");
    }
//
//    @Test
//    public void getByName() {
//        System.out.println(programTagService.getByName("标签888"));
//    }
//
//    @Test
//    public void getById() {
//        System.out.println(programTagService.getById(10));
//    }
//
//    @Test
//    public void removeByTagName() {
//        programTagService.removeByTagName("标签888");
//    }
//
//    @Test
//    public void removeById() {
//        programTagService.removeById(10);
//    }
//
//    @Test
//    public void listAll() {
//        programTagService.listAll().forEach(System.out::println);
//    }
//
//    @Test
//    public void updateByTid() {
//        programTagService.updateByTid(10,"标签88");
//    }

    @Test
    public void updateTagUsedTimes() {
        programTagService.updateTagUsedTimes(10, true);
        programTagService.updateTagUsedTimes(10, true);
        programTagService.updateTagUsedTimes(10, true);
        programTagService.updateTagUsedTimes(10, false);
    }

    @Test
    public void countTags() {
        System.out.println(programTagService.countTags());
    }
}