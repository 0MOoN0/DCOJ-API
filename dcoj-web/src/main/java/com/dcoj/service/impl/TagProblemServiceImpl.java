package com.dcoj.service.impl;

import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.entity.TagEntity;
import com.dcoj.entity.TagProblemEntity;
import com.dcoj.service.TagProblemService;
import com.dcoj.service.TagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WANGQING
 */
@Service
public class TagProblemServiceImpl implements TagProblemService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveProblemTag(long pid, List<Long> tids) {

        TagProblemEntity tagProblemEntity = null;
        //判断是否题目id已经存在于数据库
        //如果是，则更新信息，否则添加到数据库中
        if (mongoTemplate.exists(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), TagProblemEntity.class)) {
            tagProblemEntity = getByPid(pid);
            tagProblemEntity.setTids(tids);
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
        try {
            mongoTemplate.save(tagProblemEntity);
        } catch (Exception e) {
            throw new WebErrorException("修改题目标签失败");
        }
    }

    @Override
    public long countTagProblems(long pid) {
        TagProblemEntity tagProblemEntity = mongoTemplate.findOne(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), TagProblemEntity.class);
        return tagProblemEntity.getTids().size();
    }

    @Override
    public List<Long> getProblemTags(long pid) {
        TagProblemEntity tagProblemEntity =  mongoTemplate.findOne(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), TagProblemEntity.class);
        WebUtil.assertNotNull(tagProblemEntity,"题目不存在，无法获取该题目标签");
        return tagProblemEntity.getTids();
    }

    @Override
    public TagProblemEntity getByPid(long pid) {
        return mongoTemplate.findOne(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), TagProblemEntity.class);
    }

    @Resource
    private TagService tagService;

    @Override
    //TODO:事务处理
    public void removeProblemTag(long pid) {
        Update update = new Update();
        update.set("is_deleted", true);
        Query query = new Query();
        query.addCriteria(Criteria.where("p_id").is(pid));
        List<Long> list = getProblemTags(pid);
        System.out.println("Hello");
        for (int i = 0; i < list.size(); i++) {
            tagService.updateTagUsedTimes(list.get(i),false);
        }
        try {
            mongoTemplate.findAndModify(query, update, TagProblemEntity.class);
        }catch (Exception e){
            throw new WebErrorException("删除题目和标签关联失败");
        }
    }
}
