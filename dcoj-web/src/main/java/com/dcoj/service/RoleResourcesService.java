package com.dcoj.service;


/**
 * 角色、资源关联 业务层
 *
 * @author guard
 */
public interface RoleResourcesService {

    /**
     * 插入一条角色资源关联记录
     *
     * @param roleId 角色id
     * @param resourcesId 资源id
     */
    void save(Integer roleId, Integer resourcesId);

    /**
     * 清除角色的资源权限
     *
     * @param roleId 角色id
     */
    void removeResourcesByRoleId(Integer roleId);

    /**
     * 通过角色id统计资源个数
     *
     * @param roleId 角色id
     * @return 资源个数
     */
    int countResourcesByRoleId(Integer roleId);

    /**
     * 通过资源id统计资源被使用的个数
     *
     * @param resourcesId 资源id
     * @return 资源个数
     */
    int countRoleByResourcesId(Integer resourcesId);
}
