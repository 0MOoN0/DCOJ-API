package com.dcoj.service;

/**
 * @author Leon
 */
public interface CacheService {

    void reloadCache(String cacheName);

    void reloadPermissionCacheByUid(String uid);

    void clearCache();

    void reloadCache();

}
