package com.dcoj.dao;

import com.dcoj.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 用户、角色关联 持久层
 *
 * @author WANGQING
 */
public interface UserRoleMapper {

    /**
     * 通过用户id删除用户角色关联记录
     *
     * @param userId 用户id
     * @return 返回 1 则删除成功， 返回 0 则删除失败
     */
    int removeByUserId(Integer userId);

    /**
     * 插入一条用户角色关联记录
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 返回 1 则添加成功， 返回 0 则添加失败
     */
    int save(@Param("userId") int userId, @Param("roleId") int roleId);

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
     * @return 返回 1 则更新成功， 返回 0 则更新失败
     */
    int updateUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

    /**
     * 通过roleId查询属于该角色的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    int countByRoleId(Integer roleId);

}
