package com.dcoj.service;

/**
 * Id自增器
 * @author WANGQING
 */
public interface IdGenerateCacheService {

    /**
     * 题目编号自增缓存
     */
    void initIdGenerateCache();

    /**
     * 更新题目编号自增缓存
     */
    void updateIdGenerateCache();
}
