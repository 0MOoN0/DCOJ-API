package com.dcoj.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.util.Set;

/**
 * @author Leon
 **/
public class GlobalCacheManager {

    /**
     * [{token, password}, ...]
     */
    private static Cache<String, String> authCache;

    /**
     * [{uid/"guest",Set<PermissionEntity>}, ...]
     */
    private static Cache<String, Set> permissionCache;

    static {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        authCache = cacheManager
                .createCache("authCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, MemoryUnit.MB)));
        permissionCache = cacheManager
                .createCache("permissionCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                Set.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10,MemoryUnit.MB)
                        )
                );
    }

    public static Cache<String, String> getAuthCache() {
        return authCache;
    }

    public static Cache<String, Set> getPermissionCache() {
        return permissionCache;
    }

}
