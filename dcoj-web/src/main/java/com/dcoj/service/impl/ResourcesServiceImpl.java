package com.dcoj.service.impl;

import com.dcoj.dao.ResourcesMapper;
import com.dcoj.dao.RoleMapper;
import com.dcoj.entity.ResourcesEntity;
import com.dcoj.service.ResourcesService;
import com.dcoj.service.RoleResourcesService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 资源 业务层实现
 *
 * @author WANGQING
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourcesService roleResourcesService;

    /**
     * 删除一条资源权限
     *
     * @param resourcesId 资源id
     */
    @Override
    public void removeByPrimaryKey(Integer resourcesId) {
        WebUtil.assertNotNull(resourcesMapper.getByPrimaryKey(resourcesId), "资源不存在，删除失败");
        int countRole = roleResourcesService.countRoleByResourcesId(resourcesId);
        if (countRole > 0){
            WebUtil.assertIsSuccess(false, "该资源已关联角色，删除失败");
        }
        // 删除problem
        boolean flag = resourcesMapper.removeByPrimaryKey(resourcesId) == 1;
        WebUtil.assertIsSuccess(flag, "删除资源失败");
    }

    /**
     * 新增一个资源
     *
     * @param record 资源信息
     * @return 返回 资源id
     */
    @Override
    public int save(ResourcesEntity record) {
        boolean flag = resourcesMapper.save(record) == 1;
        WebUtil.assertIsSuccess(flag, "资源添加失败");
        int resourceId = record.getResourcesId();
        return resourceId;
    }

    /**
     * 获取一个资源
     *
     * @param resourcesId 资源id
     * @return 结果
     */
    @Override
    public ResourcesEntity getByPrimaryKey(Integer resourcesId) {
        ResourcesEntity resourcesEntity = resourcesMapper.getByPrimaryKey(resourcesId);
        WebUtil.assertNotNull(resourcesEntity, "不存在此资源");
        return resourcesEntity;
    }

    /**
     * 更新一个资源
     *
     * @param resourcesId 资源id
     * @param record 资源信息
     */
    @Override
    public void updateResources(Integer resourcesId, ResourcesEntity record) {
        WebUtil.assertNotNull(resourcesMapper.getByPrimaryKey(resourcesId), "该资源不存在，无法更新");
        record.setResourcesId(resourcesId);
        boolean flag = resourcesMapper.updateResources(record) == 1;
        WebUtil.assertIsSuccess(flag, "资源更新失败");
    }

    /**
     * 列出url和权限
     *
     * @return 结果
     */
    @Override
    public List<ResourcesEntity> listUrlAndPermission() {
        return resourcesMapper.listUrlAndPermission();
    }

    /**
     * 通过roleId列出角色的权限资源
     *
     * @param roleId 角色id
     * @return 结果
     */
    @Override
    public List<ResourcesEntity> listByRoleId(Integer roleId) {
        WebUtil.assertNotNull(roleMapper.getByPrimaryKey(roleId), "该角色不存在");
        return resourcesMapper.listByRoleId(roleId);
    }

    /**
     * 根据角色id查询资源并选中
     *
     * @param roleId 角色id
     * @return 结果
     */
    @Override
    public List<Map<String, Object>> listResourcesWithSelectedByRoleId(Integer roleId) {
        WebUtil.assertNotNull(roleMapper.getByPrimaryKey(roleId), "该角色不存在");
        return resourcesMapper.listResourcesWithSelectedByRoleId(roleId);
    }

    /**
     * 列出所有权限资源
     *
     * @return 结果
     */
    @Override
    public List<ResourcesEntity> listAll() {
        return resourcesMapper.listAll();
    }

    /**
     * 统计权限资源的个数
     *
     * @return 结果
     */
    @Override
    public int countResources() {
        return resourcesMapper.countResources();
    }
}
