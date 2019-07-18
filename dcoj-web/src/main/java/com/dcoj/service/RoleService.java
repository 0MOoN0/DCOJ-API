package com.dcoj.service;

import com.dcoj.entity.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 权限表 业务层
 *
 * @author WANGQING
 */
public interface RoleService {

    /**
     * 查询该用户的权限列表
     *
     * @param adminId 管理员id
     * @return 结果
     */
    List<RoleEntity> listRolesByAdminId(Integer adminId);

    /**
     * 通过roleId查询角色
     *
     * @param roleId 角色id
     * @return 角色实体类对象
     */
    RoleEntity selectByPrimaryKey(Integer roleId);

    /**
     * 列出所有角色
     *
     * @return 结果
     */
    List<RoleEntity> listAll();

    /**
     * 统计角色数量
     *
     * @return 结果
     */
    int countRoles();

    /**
     * 修改角色对应的资源权限
     *
     * @param roleId       角色id
     * @param resourcesIds 资源id
     * @return 结果
     */
    Map<String, Object> updateRoleResources(Integer roleId, String resourcesIds);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 结果
     */
    Map<String, Object> removeByPrimaryKey(Integer roleId);


}
