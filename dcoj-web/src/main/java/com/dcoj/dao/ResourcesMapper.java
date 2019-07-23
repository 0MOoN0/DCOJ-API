package com.dcoj.dao;

import com.dcoj.entity.ResourcesEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源 持久层
 *
 * @author WANGQING
 */
public interface ResourcesMapper {
    /**
     * 通过resourcesId删除一个资源
     *
     * @param resourcesId 资源id
     * @return 返回1则删除成功，返回0则删除失败
     */
    int removeByPrimaryKey(Integer resourcesId);

    /**
     * 新增一个资源
     *
     * @param record 资源信息
     * @return 返回 1 则添加成功， 返回 0 则添加失败
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
     * @param record 资源信息
     * @return 返回 1 则更新成功， 返回 0 则更新失败
     */
    int updateResources(ResourcesEntity record);

    /**
     * 列出所有资源的url和permission
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