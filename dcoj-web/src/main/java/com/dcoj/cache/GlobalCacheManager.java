package com.dcoj.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.Optional;
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

    /**
     * 格式:["emailToken":"verify:sendTime",...]
     */
    private static Cache<String,String> emailVerifyCache;

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

        // 邮箱认证缓存，默认5分钟超时
        emailVerifyCache = cacheManager
                .createCache("emailVerifyCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(2,MemoryUnit.MB)
                        ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.of(5,ChronoUnit.MINUTES)))
                );

    }

    public static Cache<String, String> getAuthCache() {
        return authCache;
    }

    public static Cache<String, Set> getPermissionCache() {
        return permissionCache;
    }

    /**
     * 获取邮箱验证缓存
     * @return
     */
    public static Cache<String,String> getEmailVerifyCache(){
        return emailVerifyCache;
    }

    /**
     * 根据条件获取邮箱验证缓存
     * @param flag  Boolean：验证邮箱缓存是否存在的条件;
     *              true：当邮箱缓存不存在时创建并返回一个邮箱认证
     *              false：不进行邮箱缓存存在检查，直接返回邮箱缓存引用
     * @return      邮箱认证缓存
     */
    public static Cache<String,String> getEmailVerifyCache(boolean flag){
        if(flag){
            return Optional.ofNullable(emailVerifyCache).orElseGet(()->{
                emailVerifyCache = CacheManagerBuilder.newCacheManagerBuilder().build(true)
                        .createCache("emailVerifyCache",
                                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                        String.class,
                                        String.class,
                                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(2,MemoryUnit.MB)
                                ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.of(5,ChronoUnit.MINUTES)))
                        );
                return emailVerifyCache;
            });
        }else{
            return getEmailVerifyCache();
        }
    }
}
