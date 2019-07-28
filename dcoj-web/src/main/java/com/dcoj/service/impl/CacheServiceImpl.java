package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.service.CacheService;
import org.springframework.stereotype.Service;

/**
 * 缓存 业务层实现
 *
 * @author Leon WANGQING
 */
@Service
public class CacheServiceImpl implements CacheService {


    /**
     * 重新加载指定缓存
     *
     * @param cacheName 缓存名称
     */
    @Override
    public void reloadCache(String cacheName) {
        //TODO:2019.7.25 WANGQING 此方法未完成
    }

    @Override
    public void reloadPermissionCacheByUid(String uid) {

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
