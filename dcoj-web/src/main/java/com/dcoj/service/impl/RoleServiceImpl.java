package com.dcoj.service.impl;

import com.dcoj.dao.RoleMapper;
import com.dcoj.entity.RoleEntity;
import com.dcoj.service.RoleResourcesService;
import com.dcoj.service.RoleService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 业务层实现
 *
 * @author WANGQING
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourcesService roleResourcesService;

    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    @Override
    public void removeByPrimaryKey(Integer roleId) {
        WebUtil.assertNotNull(roleMapper.getByPrimaryKey(roleId), "角色不存在，删除失败");
        // 判断是否跟资源关联
        int countRescources = roleResourcesService.countResourcesByRoleId(roleId);
        if (countRescources > 0){
            WebUtil.assertIsSuccess(false, "该角色已关联资源，删除失败");
        }
        //TODO:WANGQING 2019.7.23
        // 判断是否跟用户关联


        // 删除role
        boolean flag = roleMapper.removeByPrimaryKey(roleId) == 1;
        WebUtil.assertIsSuccess(flag, "删除角色失败");

    }

    /**
     * 新增一个角色信息
     *
     * @param roleEntity 角色信息
     * @return 返回值为1时，保存成功，为0则保存失败
     */
    @Override
    public int save(RoleEntity roleEntity) {
        boolean flag = roleMapper.save(roleEntity) == 1;
        WebUtil.assertIsSuccess(flag, "角色添加失败");
        int roleId = roleEntity.getRoleId();
        return roleId;
    }

    /**
     * 通过roleId查询角色
     *
     * @param roleId 角色id
     * @return 角色实体类对象
     */
    @Override
    public RoleEntity getByPrimaryKey(Integer roleId) {
        RoleEntity roleEntity = roleMapper.getByPrimaryKey(roleId);
        WebUtil.assertNotNull(roleEntity, "不存在此角色");
        return roleEntity;
    }

    /**
     * 更新一条角色信息
     *
     * @param roleId 角色id
     * @param record record 角色信息
     */
    @Override
    public void updateRole(Integer roleId, RoleEntity record) {
        WebUtil.assertNotNull(roleMapper.getByPrimaryKey(roleId), "该角色不存在，无法更新");
        record.setRoleId(roleId);
        boolean flag = roleMapper.updateRole(record) == 1;
        WebUtil.assertIsSuccess(flag, "角色更新失败");
    }

    /**
     * 通过userId查询该用户的角色
     *
     * @param userId 管理员id
     * @return 结果
     */
    @Override
    public List<RoleEntity> listRolesByUserId(Integer userId) {
        //TODO :WANGQING 2019.7.23 判断用户不存在
        //WebUtil.assertNotNull(roleMapper.getByPrimaryKey(roleId), "该用户不存在");
        return roleMapper.listRolesByUserId(userId);
    }

    /**
     * 列出所有角色
     *
     * @return 结果
     */
    @Override
    public List<RoleEntity> listAll() {
        return roleMapper.listAll();
    }

    /**
     * 统计角色数量
     *
     * @return 结果
     */
    @Override
    public int countRoles() {
        return roleMapper.countRoles();
    }
}
