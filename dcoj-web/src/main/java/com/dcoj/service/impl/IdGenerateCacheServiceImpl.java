package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.service.IdGenerateCacheService;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author WANGQING
 */
public class IdGenerateCacheServiceImpl implements IdGenerateCacheService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void initIdGenerateCache() {
        Long count = mongoTemplate.count(new Query(), ProblemEntity.class);
        Cache<String,Long> problemIdGenerateCache = GlobalCacheManager.getIdGenerateCache();
        problemIdGenerateCache.put("pidGenerate",count);
    }

    @Override
    public void updateIdGenerateCache() {
        Cache<String,Long> problemIdGenerateCache = GlobalCacheManager.getIdGenerateCache();
        problemIdGenerateCache.put("pidGenerate",problemIdGenerateCache.get("problemIdGenerateCache")+1);
    }
}
