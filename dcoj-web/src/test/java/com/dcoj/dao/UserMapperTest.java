package com.dcoj.dao;

import com.dcoj.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void countUsers() {
        System.out.println(userMapper.countUsers());
    }

    @Test
    public void getByPrimaryKey() {
        System.out.println(userMapper.getByPrimaryKey(1));
    }

    @Test
    public void listAll() {
        userMapper.listAll().forEach(System.out::println);
    }

    @Test
    public void updateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin1");
        userEntity.setUserId(1);
        userMapper.updateUser(userEntity);
    }

    @Test
    public void removeByPrimaryKey() {
        System.out.println(userMapper.removeByPrimaryKey(1));
    }
}