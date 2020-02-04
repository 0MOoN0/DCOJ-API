package com.dcoj.service;

import com.dcoj.entity.UserRoleEntity;

/**
 * 用户、角色关联 业务层
 *
 * @author WANGQING
 */
public interface UserRoleService {
    /**
     * 通过用户id删除用户角色关联记录
     *
     * @param userId 用户id
     */
    void removeByUserId(Integer userId);

    /**
     * 插入一条用户角色关联记录
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    void save(int userId,  int roleId);

    /**
     * 得到某个用户的用户角色关联记录
     *
     * @param userId 用户id
     * @return 结果
     */
    UserRoleEntity getByUserId(Integer userId);

    /**
     * 更新一条用户角色关联记录
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    void updateUserRole(int userId, int roleId);

    /**
     * 通过roleId查询属于该角色的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    int countByRoleId(Integer roleId);


    /**
     * 查询今天对应roleId新增的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    int countByRoleIdToday(Integer roleId);

    /**
     * 查询昨天对应roleId新增的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    int countByRoleIdYesterday(Integer roleId);


    /**
     * 查询本月对应roleId新增的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    int countByRoleIdMonth(Integer roleId);
}
