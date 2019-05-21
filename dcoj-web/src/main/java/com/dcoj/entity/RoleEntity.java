package com.dcoj.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author Leon
 */
@Data
public class RoleEntity {

    private String roleId;
    private String roleName;
    private Set<String> permissionIds;
}
