package com.dcoj.service;

/**
 * 缓存 业务层
 *
 * @author Leon WANGQING
 */
public interface CacheService {

    void reloadCache(String cacheName);

    void reloadPermissionCacheByUid(String uid);

    void clearCache();

    void reloadCache();

}
