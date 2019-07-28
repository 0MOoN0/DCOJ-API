package com.dcoj.dao;

import com.dcoj.entity.RoleEntity;
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
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void removeByPrimaryKey() {
        System.out.println(roleMapper.removeByPrimaryKey(3));
    }

    @Test
    public void save() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("root");
        roleEntity.setDescription("超级管理员");
        roleMapper.save(roleEntity);
    }

    @Test
    public void getByPrimaryKey() {
        System.out.println(roleMapper.getByPrimaryKey(3));
    }

    @Test
    public void updateRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(3);
        roleEntity.setName("root");
        roleEntity.setDescription("超级管理员1");
        roleMapper.updateRole(roleEntity);
    }

    @Test
    public void listRolesByUserId() {
        System.out.println(roleMapper.getRoleByUserId(1));
    }

    @Test
    public void listAll() {
        roleMapper.listAll().forEach(System.out::println);
    }

    @Test
    public void countRoles() {
        System.out.println(roleMapper.countRoles());
    }
}