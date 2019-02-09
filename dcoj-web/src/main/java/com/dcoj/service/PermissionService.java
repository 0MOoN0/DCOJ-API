package com.dcoj.service;

import com.dcoj.entity.PermissionEntity;

import java.util.List;

/**
 * @author Leon
 */
public interface PermissionService {

    List<PermissionEntity> getPermissions();

    void addPermission(PermissionEntity permissionEntity);

    PermissionEntity findById(String permissionId);

}
