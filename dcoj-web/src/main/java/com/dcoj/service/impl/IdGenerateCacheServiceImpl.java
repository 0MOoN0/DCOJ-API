package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.ProgramTagEntity;
import com.dcoj.service.IdGenerateCacheService;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author WANGQING
 */
@Service
public class IdGenerateCacheServiceImpl implements IdGenerateCacheService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void initIdGenerateCache() {
        Long problemCount = mongoTemplate.count(new Query(), ProgramProblemEntity.class);
        Long tagCount = mongoTemplate.count(new Query(), ProgramTagEntity.class);
        Cache<String, Long> idGenerateCache = GlobalCacheManager.getIdGenerateCache();
        idGenerateCache.put("pidGenerate", problemCount);
        idGenerateCache.put("tidGenerate", tagCount);
    }

    @Override
    public void updateIdGenerateCache() {
        Cache<String, Long> idGenerateCache = GlobalCacheManager.getIdGenerateCache();
        idGenerateCache.put("pidGenerate", idGenerateCache.get("problemIdGenerateCache") + 1);
    }
}
