package com.dcoj.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 角色、资源关联 持久层
 *
 * @author WANGQING
 */
public interface RoleResourcesMapper {

    /**
     * 插入一条角色资源关联记录
     *
     * @param roleId 角色id
     * @param resourcesId 资源id
     * @return 返回1则插入成功，返回0则插入失败
     */
    int save(@Param("roleId") Integer roleId, @Param("resourcesId") Integer resourcesId);

    /**
     * 清除角色的资源权限
     *
     * @param roleId 角色id
     * @return 结果
     */
    int removeResourcesByRoleId(Integer roleId);

    /**
     * 删除一条资源权限
     *
     * @param resourcesId 资源id
     * @return 返回值为1则删除成功，返回值为0则删除失败
     */
    int removeResourcesByResourcesId(Integer resourcesId);

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