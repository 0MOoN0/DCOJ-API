package com.dcoj.security;

import com.dcoj.entity.PermissionEntity;

import java.util.Set;

/**
 * @author Smith
 **/
public class UserSession {

    private String token;

    private String uid;

    private Set<String> roleId;

    private Set<PermissionEntity> permissionId;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Set<String> getRoleId() {
        return roleId;
    }

    public void setRoleId(Set<String> roleId) {
        this.roleId = roleId;
    }

    public Set<PermissionEntity> getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Set<PermissionEntity> permissionId) {
        this.permissionId = permissionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
