package com.dcoj.service;

import com.dcoj.entity.ResourcesEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源 业务层
 *
 * @author WANGQING
 */
public interface ResourcesService {

    /**
     * 删除一条资源权限
     *
     * @param resourcesId 资源id
     */
    void removeByPrimaryKey(Integer resourcesId);

    /**
     * 新增一个资源
     *
     * @param record 资源信息
     * @return 返回 资源id
     */
    int save(ResourcesEntity record);

    /**
     * 获取一个资源
     *
     * @param resourcesId 资源id
     * @return 结果
     */
    ResourcesEntity getByPrimaryKey(Integer resourcesId);

    /**
     * 更新一个资源
     *
     * @param resourcesId 资源id
     * @param record 资源信息
     */
    void updateResources(Integer resourcesId, ResourcesEntity record);

    /**
     * 列出url和权限
     *
     * @return 结果
     */
    List<ResourcesEntity> listUrlAndPermission();

    /**
     * 通过roleId列出角色的权限资源
     *
     * @param roleId 角色id
     * @return 结果
     */
    List<ResourcesEntity> listByRoleId(Integer roleId);

    /**
     * 根据角色id查询资源并选中
     *
     * @param roleId 角色id
     * @return 结果
     */
    List<Map<String, Object>> listResourcesWithSelectedByRoleId(Integer roleId);

    /**
     * 列出所有权限资源
     *
     * @return 结果
     */
    List<ResourcesEntity> listAll();

    /**
     * 统计权限资源的个数
     *
     * @return 结果
     */
    int countResources();



}
