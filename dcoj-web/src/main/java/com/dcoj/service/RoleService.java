package com.dcoj.service;

import com.dcoj.entity.RoleEntity;

import java.util.List;

/**
 * 角色 业务层
 *
 * @author WANGQING
 */
public interface RoleService {
    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    void removeByPrimaryKey(Integer roleId);

    /**
     *  新增一个角色信息
     *
     * @param roleEntity 角色信息
     * @return 返回值为1时，保存成功，为0则保存失败
     */
    int save(RoleEntity roleEntity);

    /**
     * 通过roleId查询角色
     *
     * @param roleId 角色id
     * @return 角色实体类对象
     */
    RoleEntity getByPrimaryKey(Integer roleId);

    /**
     * 更新一条角色信息
     *
     * @param roleId 角色id
     * @param record record 角色信息
     */
    void updateRole(Integer roleId, RoleEntity record);

    /**
     * 通过userId查询该用户的角色
     *
     * @param userId 管理员id
     * @return 结果
     */
    RoleEntity getRoleByUserId(Integer userId);

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
    //void updateRoleResources(Integer roleId, String resourcesIds);

}
