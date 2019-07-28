package com.dcoj.service;

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
public class ResourcesServiceTest {

    @Autowired
    private ResourcesService resourcesService;

    @Test
    public void removeByPrimaryKey() {
    }

    @Test
    public void save() {
    }

    @Test
    public void getByPrimaryKey() {
    }

    @Test
    public void updateResources() {
    }

    @Test
    public void listUrlAndPermission() {
    }

    @Test
    public void listByRoleId() {
        System.out.println(resourcesService.listByRoleId(3));
        for (ResourcesEntity resourcesEntity : resourcesService.listByRoleId(3)) {
            System.out.println(resourcesEntity);
        }
    }

    @Test
    public void listResourcesWithSelectedByRoleId() {
    }

    @Test
    public void listAll() {
    }

    @Test
    public void countResources() {
    }
}