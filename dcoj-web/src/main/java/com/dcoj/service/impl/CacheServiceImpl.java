package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.PermissionEntity;
import com.dcoj.entity.RoleEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.CacheService;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Leon
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 重新加载指定缓存
     *
     * @param cacheName 缓存名称
     */
    @Override
    public void reloadCache(String cacheName) {
        List<UserEntity> userEntities;
        switch (cacheName) {
            case "permissionCache":
                userEntities = mongoTemplate.findAll(UserEntity.class);
                Cache<String, Set> permissionCache = GlobalCacheManager.getPermissionCache();
                // 遍历用户
                userEntities.forEach(user -> {
                            // 判断用户角色是否为空，如果不为空则根据遍历用户角色
                            Optional.ofNullable(user.getRoles()).ifPresent(roleIds ->
                                    //为每个用户根据角色设置集合，如果角色不存在或没有权限，则权限集合为空(非null)
                                    permissionCache.put(user.getUid(),
                                            roleIds.parallelStream().map(roleId -> {
                                                        RoleEntity roleEntity = mongoTemplate.findOne(new Query(Criteria.where("_id").is(roleId)), RoleEntity.class);
                                                        return Optional.ofNullable(roleEntity.getPermissionId())
                                                                .map(permissionIds -> permissionIds.parallelStream().map(
                                                                        permissionId -> mongoTemplate.findOne(new Query(Criteria.where("_id").is(permissionId)), PermissionEntity.class))
                                                                                .collect(Collectors.toSet())
                                                                        //如果权限为空，返回一个空集合
                                                                ).orElse(new HashSet<>());
                                                    }
                                            ).reduce((SetA, SetB) ->
                                                    Optional.ofNullable(SetA).map(setA -> {
                                                                setA.addAll(SetB);
                                                                return setA;
                                                            }
                                                    ).orElse(new HashSet<>())
                                            ).orElse(new HashSet<>())
                                    )

                            );
                        }
                );
                break;
        }
    }

    @Override
    public void reloadPermissionCacheByUid(String uid) {
        UserEntity user = mongoTemplate.findOne(new Query(Criteria.where("_id").is(uid)), UserEntity.class);
        Cache<String, Set> permissionCache = GlobalCacheManager.getPermissionCache();
        Optional.ofNullable(user.getRoles()).ifPresent(roleIds ->
                //为每个用户根据角色设置集合，如果角色不存在或没有权限，则权限集合为空(非null)
                permissionCache.put(user.getUid(),
                        roleIds.parallelStream().map(roleId ->
                                        Optional.ofNullable(mongoTemplate.findOne(new Query(Criteria.where("_id").is(roleId)), RoleEntity.class)
                                                .getPermissionId())
                                                .map(permissionIds -> permissionIds
                                                                .parallelStream()
                                                                .map(permissionId ->
                                                                                mongoTemplate.findOne(new Query(Criteria.where("_id").is(permissionId)), PermissionEntity.class)
                                                                        //将所有查询到的PermissionEntity收集起来
                                                                ).collect(Collectors.toSet())
                                                        //如果权限为空，返回一个空集合
                                                ).orElse(new HashSet<>())
                                //将所有Permission集合合并为一个集合
                        ).reduce((SetA, SetB) ->
                                        Optional.ofNullable(SetA).map(setA -> {
                                                    setA.addAll(SetB);
                                                    return setA;
                                                }
                                                //如果SetA不存在，则返回一个空的HashSet，以供后续的Reduce收集
                                        ).orElse(new HashSet<>())
                                //如果所有的Role为空或者所有的Permission为空，则返回一个空的HashSet
                        ).orElse(new HashSet<>())
                )
        );
    }


    /**
     * 清空缓存
     */
    @Override
    public void clearCache() {
        GlobalCacheManager.getPermissionCache().clear();
        GlobalCacheManager.getAuthCache().clear();
    }

    /**
     * 重新加载所有缓存
     */
    @Override
    public void reloadCache() {

    }
}
