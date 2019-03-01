package com.dcoj.service.impl;

import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.entity.TagEntity;
import com.dcoj.entity.TagProblemEntity;
import com.dcoj.service.TagProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author WANGQING
 */
public class TagProblemServiceImpl implements TagProblemService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveProblemTag(long pid, List<Long> tids) {

        TagProblemEntity tagProblemEntity = null;
        //判断是否题目id已经存在于数据库
        //如果是，则更新信息，否则添加到数据库中
        if (mongoTemplate.exists(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), TagEntity.class)) {
            tagProblemEntity = getByPid(pid);
            tagProblemEntity.setTids(tids);
            tagProblemEntity.setDeleted(false);
            try {
                mongoTemplate.save(tagProblemEntity);
            } catch (Exception e) {
                throw new WebErrorException("修改题目标签失败");
            }
            return;
        }
        tagProblemEntity = new TagProblemEntity();
        tagProblemEntity.setPid(pid);
        tagProblemEntity.setTids(tids);
        tagProblemEntity.setDeleted(false);
        try {
            mongoTemplate.save(tagProblemEntity);
        } catch (Exception e) {
            throw new WebErrorException("修改题目标签失败");
        }
    }

    @Override
    public long countTagProblems(long pid) {
        return mongoTemplate.count(new Query(Criteria.where("is_deleted").is(false)), TagProblemEntity.class);
    }

    @Override
    public List<TagProblemEntity> getProblemTags(long pid) {
        return mongoTemplate.find(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), TagProblemEntity.class);
    }

    @Override
    public TagProblemEntity getByPid(long pid) {
        return mongoTemplate.findOne(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), TagProblemEntity.class);
    }

}
