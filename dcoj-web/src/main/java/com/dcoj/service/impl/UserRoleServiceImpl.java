package com.dcoj.service.impl;

import com.dcoj.dao.RoleMapper;
import com.dcoj.dao.UserMapper;
import com.dcoj.dao.UserRoleMapper;
import com.dcoj.entity.UserRoleEntity;
import com.dcoj.service.UserRoleService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户、角色关联 业务层实现
 *
 * @author WANGQING
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过用户id删除用户角色关联记录
     *
     * @param userId 用户id
     */
    @Override
    public void removeByUserId(Integer userId) {
        WebUtil.assertNotNull(userMapper.getByPrimaryKey(userId), "用户不存在，删除用户角色失败");
        boolean flag = userRoleMapper.removeByUserId(userId) >= 1;
        WebUtil.assertIsSuccess(flag, "用户角色删除失败");
    }

    /**
     * 插入一条用户角色关联记录
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Override
    public void save(int userId, int roleId) {
        boolean flag = userRoleMapper.save(userId, roleId) == 1;
        WebUtil.assertIsSuccess(flag, "用户角色添加失败");
    }

    /**
     * 得到某个用户的用户角色关联记录
     *
     * @param userId 用户id
     * @return 结果
     */
    @Override
    public UserRoleEntity getByUserId(Integer userId) {
        UserRoleEntity userRoleEntity = userRoleMapper.getByUserId(userId);
        WebUtil.assertNotNull(userRoleEntity, "不存在此用户角色关联");
        return userRoleEntity;
    }

    /**
     * 更新一条用户角色关联记录
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Override
    public void updateUserRole(int userId, int roleId) {

        WebUtil.assertNotNull(userMapper.getByPrimaryKey(userId), "该用户不存在，无法更新用户角色关联");

        boolean flag = userRoleMapper.updateUserRole(userId,roleId) == 1;
        WebUtil.assertIsSuccess(flag, "用户角色更新失败");
    }

    /**
     * 通过roleId查询属于该角色的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    @Override
    public int countByRoleId(Integer roleId) {
        WebUtil.assertNotNull(roleMapper.getByPrimaryKey(roleId), "角色不存在，查询用户角色失败");
        return userRoleMapper.countByRoleId(roleId);
    }
    /**
     * 查询今天对应roleId新增的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    @Override
    public int countByRoleIdToday(Integer roleId) {
        return userRoleMapper.countByRoleIdToday(roleId);
    }
    /**
     * 查询昨天对应roleId新增的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    @Override
    public int countByRoleIdYesterday(Integer roleId) {
        return userRoleMapper.countByRoleIdYesterday(roleId);
    }

    /**
     * 查询本月对应roleId新增的user个数
     *
     * @param roleId 角色id
     * @return 结果
     */
    @Override
    public int countByRoleIdMonth(Integer roleId) {
        return userRoleMapper.countByRoleIdMonth(roleId);
    }
}
