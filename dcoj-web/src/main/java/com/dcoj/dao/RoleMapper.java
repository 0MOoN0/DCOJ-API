package com.dcoj.dao;

import com.dcoj.entity.RoleEntity;

import java.util.List;

/**
 * 角色 持久层
 *
 * @author WANGQING
 */
public interface RoleMapper {

    /**
     * 通过roleId删除一个角色
     *
     * @param roleId 角色id
     * @return 返回1则删除成功，返回0则删除失败
     */
    int removeByPrimaryKey(Integer roleId);

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
     * @param record 角色信息
     * @return 返回值为1时，更新成功，为0则更新失败
     */
    int updateRole(RoleEntity record);

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

}