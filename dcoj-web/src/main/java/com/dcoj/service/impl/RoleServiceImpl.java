package com.dcoj.service.impl;

import com.dcoj.entity.RoleEntity;
import com.dcoj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leon
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<RoleEntity> getRoles() {
        return mongoTemplate.findAll(RoleEntity.class);
    }

    @Override
    public RoleEntity findById(String roleId) {
        return mongoTemplate.findById(roleId,RoleEntity.class);
    }

    @Override
    public void addRole(RoleEntity roleEntity) {
        mongoTemplate.insert(roleEntity);
        //TODO:2019.04.03 Leon REFRESH CACHE
    }
}
