package com.dcoj.dao;

import com.dcoj.entity.UserRoleEntity;
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
public class UserRoleMapperTest {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Test
    public void removeByUserId() {
    }

    @Test
    public void save() {
        userRoleMapper.save(1,3);
    }

    @Test
    public void getByUserId() {
        System.out.println(userRoleMapper.getByUserId(1));
    }

    @Test
    public void updateUserRole() {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(1);
        userRoleEntity.setUserId(1);
        userRoleMapper.updateUserRole(1,1);
    }

    @Test
    public void countByRoleId() {
        System.out.println(userRoleMapper.countByRoleId(1));
    }
}