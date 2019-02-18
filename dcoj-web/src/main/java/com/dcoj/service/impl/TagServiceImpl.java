package com.dcoj.service.impl;

import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.entity.TagEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.TagService;
import org.apache.shiro.crypto.hash.Md5Hash;
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
        if (mongoTemplate.exists(new Query(Criteria.where("tag_name").is(name).andOperator(Criteria.where("isDeleted").is(false))), TagEntity.class)) {
            throw new WebErrorException("标签已经存在");
        }
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTagName(name);
        tagEntity.setDeleted(false);
        try {
            mongoTemplate.save(tagEntity);
        } catch (Exception e) {
            throw new WebErrorException("新增标签失败");
        }
    }

    @Override
    public TagEntity getByName(String tagName) {
        if (!mongoTemplate.exists(new Query(Criteria.where("tag_name").is(tagName).andOperator(Criteria.where("isDeleted").is(false))), TagEntity.class)) {
            throw new WebErrorException("标签不存在");
        }
        return mongoTemplate.findOne(new Query(Criteria.where("tag_name").is(tagName)), TagEntity.class);
    }

    @Override
    public void deleteTag(String tagName) {
        Update update = new Update();
        update.set("isDeleted", true);
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
        return mongoTemplate.find(new Query(Criteria.where("isDeleted").is(false)),TagEntity.class);
    }

    @Override
    public void updateTagName(String oldName, String newName) {
        TagEntity tagEntity = getByName(oldName);
        tagEntity.setTagName(newName);
        try {
            mongoTemplate.save(tagEntity);
        } catch (Exception e) {
            throw new WebErrorException("更新标签失败");
        }
    }

    @Override
    public long countTags() {
        return mongoTemplate.count(new Query(Criteria.where("isDeleted").is(false)), TagEntity.class);
    }
}
