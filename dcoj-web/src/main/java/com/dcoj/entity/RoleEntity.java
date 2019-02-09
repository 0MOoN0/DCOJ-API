package com.dcoj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * @author Leon
 */
@Document("RoleEntity")
public class RoleEntity {

    @Id
    private String roleId;
    private String roleName;
    private Set<String> permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<String> getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Set<String> permissionId) {
        this.permissionId = permissionId;
    }
}
