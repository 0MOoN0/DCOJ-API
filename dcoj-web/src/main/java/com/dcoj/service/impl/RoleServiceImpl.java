package com.dcoj.service.impl;

import com.dcoj.entity.RoleEntity;
import com.dcoj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Leon
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * 查询该用户的权限列表
     *
     * @param adminId 管理员id
     * @return 结果
     */
    @Override
    public List<RoleEntity> listRolesByAdminId(Integer adminId) {
        return null;
    }

    /**
     * 通过roleId查询角色
     *
     * @param roleId 角色id
     * @return 角色实体类对象
     */
    @Override
    public RoleEntity selectByPrimaryKey(Integer roleId) {
        return null;
    }

    /**
     * 列出所有角色
     *
     * @return 结果
     */
    @Override
    public List<RoleEntity> listAll() {
        return null;
    }

    /**
     * 统计角色数量
     *
     * @return 结果
     */
    @Override
    public int countRoles() {
        return 0;
    }

    /**
     * 修改角色对应的资源权限
     *
     * @param roleId       角色id
     * @param resourcesIds 资源id
     * @return 结果
     */
    @Override
    public Map<String, Object> updateRoleResources(Integer roleId, String resourcesIds) {
        return null;
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 结果
     */
    @Override
    public Map<String, Object> removeByPrimaryKey(Integer roleId) {
        return null;
    }
}
