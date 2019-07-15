package com.dcoj.cache;


import com.dcoj.config.DefaultConfig;
import com.dcoj.entity.exam.UserExamEntity;
import com.dcoj.judge.JudgeResult;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
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
     * 格式:["emailToken":"verify:email:sendTime",...]
     */
    private static Cache<String, String> emailVerifyCache;

    /**
     * 格式:["taskId":JudgeResult]
     */
    private static Cache<String, JudgeResult> submissionCache;

    /**
     * [{"XXXIdGenerate":number}]
     */
    private static Cache<String, Long> idGenerateCache;

    private static Cache<String, UserExamEntity> examCache;


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
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, MemoryUnit.MB)
                        )
                );
        // 邮箱认证缓存，5分钟之内如果没有访问，则缓存超时
        emailVerifyCache = cacheManager
                .createCache("emailVerifyCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(2, MemoryUnit.MB)
                        ).withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.of(DefaultConfig.CAPTCHA_EXPIRED_TIME, ChronoUnit.MINUTES)))
                );

        submissionCache = cacheManager
                .createCache("submissionCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                JudgeResult.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100, MemoryUnit.MB))
                                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.of(1, ChronoUnit.HOURS)))
                                .withSizeOfMaxObjectGraph(5000)
                                .build());

        idGenerateCache = cacheManager
                .createCache("idGenerateCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                Long.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(2, MemoryUnit.MB)
                        )
                );
        // TODO: Leon 20190708 完善试卷缓存内容
        // 试卷缓存默认5小时后失效
        examCache = cacheManager
                .createCache("examCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                UserExamEntity.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100, MemoryUnit.MB))
                                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.of(5, ChronoUnit.HOURS)))
                                .build());

    }

    public static Cache<String, String> getAuthCache() {
        return authCache;
    }

    public static Cache<String, Set> getPermissionCache() {
        return permissionCache;
    }

    public static Cache<String, String> getEmailVerifyCache() {
        return emailVerifyCache;
    }

    public static Cache<String, JudgeResult> getSubmissionCache() {
        return submissionCache;
    }

    public static Cache<String, UserExamEntity> getExamCache() {
        return examCache;
    }

    public static Cache<String, Long> getIdGenerateCache() {
        return idGenerateCache;
    }


}
