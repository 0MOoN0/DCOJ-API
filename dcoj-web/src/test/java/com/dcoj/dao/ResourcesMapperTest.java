package com.dcoj.dao;

import com.dcoj.entity.ResourcesEntity;
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
public class ResourcesMapperTest {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Test
    public void removeByPrimaryKey() {
    }

    @Test
    public void save() {
        ResourcesEntity resources = new ResourcesEntity();
        resources.setName("hel");
        resourcesMapper.save(resources);
    }

    @Test
    public void getByPrimaryKey() {
        System.out.println(resourcesMapper.getByPrimaryKey(21));
    }

    @Test
    public void updateResources() {
        ResourcesEntity resources = new ResourcesEntity();
        resources.setName("hel11");
        resources.setResourcesId(21);
        resourcesMapper.updateResources(resources);
    }

    @Test
    public void listUrlAndPermission() {
    }

    @Test
    public void listByRoleId() {

    }

    @Test
    public void listResourcesWithSelectedByRoleId() {
    }

    @Test
    public void listAll() {
        resourcesMapper.listAll().forEach(System.out::println);
    }

    @Test
    public void countResource() {
        System.out.println(resourcesMapper.countResources());
    }
}