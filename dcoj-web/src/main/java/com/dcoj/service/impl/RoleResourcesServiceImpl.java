package com.dcoj.service.impl;

import com.dcoj.dao.ResourcesMapper;
import com.dcoj.dao.RoleMapper;
import com.dcoj.dao.RoleResourcesMapper;
import com.dcoj.service.RoleResourcesService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.ResourceManager;

/**
 * 角色、资源关联 业务层实现
 *
 * @author WANGQING
 */
@Service
public class RoleResourcesServiceImpl implements RoleResourcesService {

    @Autowired
    private RoleResourcesMapper roleResourcesMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourcesMapper resourcesMapper;

    /**
     * 插入一条角色资源关联记录
     *
     * @param roleId      角色id
     * @param resourcesId 资源id
     */
    @Override
    public void save(Integer roleId, Integer resourcesId) {
        boolean flag = roleResourcesMapper.save(roleId, resourcesId) == 1;
        WebUtil.assertIsSuccess(flag, "角色资源添加失败");
    }

    /**
     * 清除角色的资源权限
     *
     * @param roleId 角色id
     */
    @Override
    public void removeResourcesByRoleId(Integer roleId) {
        WebUtil.assertNotNull(roleMapper.getByPrimaryKey(roleId), "角色不存在，删除角色资源失败");
        boolean flag = roleResourcesMapper.removeResourcesByRoleId(roleId) >= 1;
        WebUtil.assertIsSuccess(flag, "角色资源删除失败");
    }

    /**
     * 通过角色id统计资源个数
     *
     * @param roleId 角色id
     * @return 资源个数
     */
    @Override
    public int countResourcesByRoleId(Integer roleId) {
        WebUtil.assertNotNull(roleMapper.getByPrimaryKey(roleId), "角色不存在，查询角色资源失败");
        return roleResourcesMapper.countResourcesByRoleId(roleId);
    }

    /**
     * 通过资源id统计资源被使用的个数
     *
     * @param resourcesId 资源id
     * @return 资源个数
     */
    @Override
    public int countRoleByResourcesId(Integer resourcesId) {
        WebUtil.assertNotNull(resourcesMapper.getByPrimaryKey(resourcesId), "资源不存在，查询角色资源失败");
        return roleResourcesMapper.removeResourcesByResourcesId(resourcesId);
    }

}
