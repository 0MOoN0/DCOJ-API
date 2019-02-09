package com.dcoj.service;

import com.dcoj.entity.RoleEntity;

import java.util.List;

/**
 * @author Leon
 */
public interface RoleService {

    List<RoleEntity> getRoles();

    RoleEntity findById(String roleId);

    void addRole(RoleEntity roleEntity);

}
