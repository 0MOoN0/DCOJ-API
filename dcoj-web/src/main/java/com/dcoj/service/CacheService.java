package com.dcoj.service;

/**
 * @author Leon
 */
public interface CacheService {

    void reloadCache(String cacheName);

    void clearCache();

    void reloadCache();

}
