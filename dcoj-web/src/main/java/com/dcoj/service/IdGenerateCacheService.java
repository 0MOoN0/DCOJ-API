package com.dcoj.service;

/**
 * Id自增器
 * @author WANGQING
 */
public interface IdGenerateCacheService {

    /**
     * 初始化编号自增缓存
     */
    void initIdGenerateCache();

    /**
     * 更新题目编号自增缓存
     */
    void updateIdGenerateCache();
}
