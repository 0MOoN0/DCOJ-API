package com.dcoj.security;

import java.util.Set;

/**
 * @author Smith
 **/
public class UserSession {

    private String token;

    private String uid;

    private int role;

    private Set<String> permission;

    public String getUid() {
        return uid;
    }

        public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Set<String> getPermission() {
        return permission;
    }

    public void setPermission(Set<String> permission) {
        this.permission = permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
