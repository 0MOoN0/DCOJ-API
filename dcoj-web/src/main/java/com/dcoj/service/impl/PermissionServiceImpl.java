package com.dcoj.service.impl;

import com.dcoj.entity.PermissionEntity;
import com.dcoj.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leon
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<PermissionEntity> getPermissions() {
        return mongoTemplate.findAll(PermissionEntity.class);
    }

    @Override
    public void addPermission(PermissionEntity permissionEntity) {
        mongoTemplate.save(permissionEntity);
    }

    @Override
    public PermissionEntity findById(String permissionId) {
        return mongoTemplate.findById(permissionId, PermissionEntity.class);
    }
}
