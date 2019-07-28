package com.dcoj.service;


import com.dcoj.entity.UserEntity;

import java.util.Map;

/**
 * 权限业务层
 *
 * @author WANGQING
 */
public interface ShiroService {
    /**
     * 初始化权限
     *
     * @return 结果
     */
    Map<String, String> loadFilterChainDefinitions();


    /**
     * 重新加载权限
     */
    void updatePermission();

    /**
     * 重新加载管理员权限
     *
     * @param userEntity 用户
     */
    void reloadAuthorizingByUserId(UserEntity userEntity);

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     *
     * @param roleId 角色id
     */
    void reloadAuthorizingByRoleId(Integer roleId);

}
