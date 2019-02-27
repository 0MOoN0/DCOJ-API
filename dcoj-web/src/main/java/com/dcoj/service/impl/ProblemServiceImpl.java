package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WANGQING
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long countProblems() {
        return mongoTemplate.count(new Query(Criteria.where("is_deleted").is(false)), ProblemEntity.class);
    }

    @Override
    public long countProblems(int type) {
        return mongoTemplate.count(new Query(Criteria.where("is_deleted").is(false).
                andOperator(Criteria.where("type").is(type))), ProblemEntity.class);
    }

    @Override
    public void removeProblem(int objectId) {
        Update update = new Update();
        update.set("is_deleted", true);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(objectId));
        try {
            mongoTemplate.findAndModify(query, update, ProblemEntity.class);
        }catch (Exception e){
            throw new WebErrorException("删除题目失败");
        }
    }

    @Override
    public void updateProblem(int pid, JSONArray tags, ProblemEntity problemEntity) {

    }

    @Override
    public List<ProblemEntity> listAll() {
        return mongoTemplate.find(new Query(Criteria.where("is_deleted").is(false)),ProblemEntity.class);
    }

    @Override
    public List<ProblemEntity> listByType(int type) {
        return mongoTemplate.find(new Query(Criteria.where("is_deleted").is(false).
                andOperator(Criteria.where("type").is(type))),ProblemEntity.class);
    }

    @Override
    public ProblemEntity getById(int pid) {
        return mongoTemplate.findOne(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), ProblemEntity.class);
    }

    @Override
    public int save(JSONArray tags, ProblemEntity problemEntity) {
        return 0;
    }
}
