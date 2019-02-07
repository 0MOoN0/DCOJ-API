package com.dcoj.security;

import com.dcoj.entity.PermissionEntity;

import java.util.Set;

/**
 * @author Smith
 **/
public class SessionHelper {
    private static ThreadLocal<UserSession> threadLocal = new ThreadLocal<>();

    public static UserSession get() {
        return threadLocal.get();
    }

    public static void init(String token, String uid, Set<String> roleId, Set<PermissionEntity> permissionId) {
        UserSession session = new UserSession();
        session.setToken(token);
        session.setUid(uid);
        session.setRoleId(roleId);
        session.setPermissionId(permissionId);
        threadLocal.set(session);
    }
}
