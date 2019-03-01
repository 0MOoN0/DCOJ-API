package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.entity.TagEntity;
import com.dcoj.service.TagService;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WANGQING
 * @description
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(String name) {
        if (mongoTemplate.exists(new Query(Criteria.where("tag_name").is(name).
                andOperator(Criteria.where("is_deleted").is(false))), TagEntity.class)) {
            throw new WebErrorException("标签已经存在");
        }
        Cache<String,Long> idGenerateCache = GlobalCacheManager.getIdGenerateCache();
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTid(idGenerateCache.get("tidGenerate")+1);
        tagEntity.setTagName(name);
        tagEntity.setUsedTimes(0);
        tagEntity.setDeleted(false);
        try {
            mongoTemplate.save(tagEntity);
            idGenerateCache.put("tidGenerate",idGenerateCache.get("tidGenerate")+1);
        } catch (Exception e) {
            throw new WebErrorException("新增标签失败");
        }
    }

    @Override
    public TagEntity getByName(String tagName) {
        return mongoTemplate.findOne(new Query(Criteria.where("tag_name").is(tagName).
                andOperator(Criteria.where("is_deleted").is(false))), TagEntity.class);
    }

    @Override
    public TagEntity getById(long tid) {
        return mongoTemplate.findOne(new Query(Criteria.where("t_id").is(tid).
                andOperator(Criteria.where("is_deleted").is(false))), TagEntity.class);
    }

    @Override
    public void removeTag(String tagName) {
        Update update = new Update();
        update.set("is_deleted", true);
        Query query = new Query();
        query.addCriteria(Criteria.where("tag_name").is(tagName));
        try {
            mongoTemplate.findAndModify(query, update, TagEntity.class);
        }catch (Exception e){
            throw new WebErrorException("删除标签失败");
        }
    }

    @Override
    public List<TagEntity> listAll() {
        return mongoTemplate.find(new Query(Criteria.where("is_deleted").is(false)),TagEntity.class);
    }

    @Override
    public void updateTagName(String oldName, String newName) {
        TagEntity tagEntity = getByName(oldName);
        tagEntity.setTagName(newName);
        try {
            mongoTemplate.save(tagEntity);
        } catch (Exception e) {
            throw new WebErrorException("修改标签名失败");
        }
    }

    @Override
    public void updateTagUsedTimes(long tid) {
        TagEntity tagEntity = getById(tid);
        tagEntity.setUsedTimes(tagEntity.getUsedTimes()+1);
        try {
            mongoTemplate.save(tagEntity);
        } catch (Exception e) {
            throw new WebErrorException("更新标签次数失败");
        }
    }

    @Override
    public long countTags() {
        return mongoTemplate.count(new Query(Criteria.where("is_deleted").is(false)), TagEntity.class);
    }
}
