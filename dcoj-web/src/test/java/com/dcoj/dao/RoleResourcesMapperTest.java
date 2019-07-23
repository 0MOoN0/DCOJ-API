package com.dcoj.dao;

import lombok.experimental.Accessors;
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
public class RoleResourcesMapperTest {

    @Autowired
    private RoleResourcesMapper roleResourcesMapper;

    @Test
    public void save() {
        roleResourcesMapper.save(3,21);
    }

    @Test
    public void removeResourcesByRoleId() {
        roleResourcesMapper.removeResourcesByRoleId(3);
    }

    @Test
    public void removeResourcesByResourcesId() {
        roleResourcesMapper.removeResourcesByResourcesId(21);
    }

    @Test
    public void countResourcesByRoleId() {
        System.out.println(roleResourcesMapper.countResourcesByRoleId(3));
    }

    @Test
    public void countRoleIdByResourcesId(){
        System.out.println(roleResourcesMapper.countRoleByResourcesId(21));
    }

}