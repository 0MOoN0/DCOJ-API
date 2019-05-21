package com.dcoj.dao;

import com.dcoj.entity.ProgramTagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * TagMapper测试类
 *
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramTagMapperTest {

    @Autowired
    private ProgramTagMapper programTagMapper;

    @Test
    public void save() {
//        programTagMapper.save("标签111");
//        programTagMapper.save("标签222");
//        programTagMapper.save("标签333");
//        programTagMapper.save("标签444");
        programTagMapper.save("标签777");
    }

    @Test
    public void getByName() {
//        System.out.println(programTagMapper.getByName("标签111"));
//        System.out.println(programTagMapper.getByName("标签222"));
    }

    @Test
    public void getById() {
//        System.out.println(programTagMapper.getById(1));
//        System.out.println(programTagMapper.getById(2));
    }

    @Test
    public void removeByTagName() {
//        System.out.println(programTagMapper.getByName("标签222"));
//        System.out.println(programTagMapper.removeByTagName("标签222"));
//        System.out.println(programTagMapper.getByName("标签222"));
    }

    @Test
    public void removeById() {
//        System.out.println(programTagMapper.getById(1));
//        System.out.println(programTagMapper.removeById(1));
//        System.out.println(programTagMapper.getById(1));
    }

    @Test
    public void listAll() {
        List<ProgramTagEntity> list = programTagMapper.listAll();
        list.forEach(System.out::println);
    }

    @Test
    public void updateByTid() {
//        System.out.println(programTagMapper.updateByTid(6,"修改后的标签"));
    }

    @Test
    public void updateTagUsedTimes() {
        System.out.println(programTagMapper.updateTagUsedTimes(1, true));
        System.out.println(programTagMapper.updateTagUsedTimes(2, true));
        System.out.println(programTagMapper.updateTagUsedTimes(3, true));
        System.out.println(programTagMapper.updateTagUsedTimes(3, true));
        System.out.println(programTagMapper.updateTagUsedTimes(3, true));
        System.out.println(programTagMapper.updateTagUsedTimes(3, false));
        System.out.println(programTagMapper.updateTagUsedTimes(3, false));
    }

    @Test
    public void countTags() {
        System.out.println(programTagMapper.countTags());
    }


}