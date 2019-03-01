package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProblemService;
import com.dcoj.util.WebUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author WANGQING
 * @author Leon
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

    /**
     * 根据判卷状态更新Problem
     * @param pid    题目业务id
     * @param result 判卷结果
     */
    @Override
    public void updateProblemTimes(int pid, ResultEnum result) {
        ProblemEntity problemEntity = mongoTemplate.findOne(new Query(Criteria.where("pid").is(pid)), ProblemEntity.class);
        WebUtil.assertNotNull(problemEntity,"题目不存在，无法更新");
        Optional.ofNullable(problemEntity.getSubmitTimes()).ifPresent(subTimes->problemEntity.setSubmitTimes(subTimes+1));
        switch (result) {
            case AC:
                Optional.ofNullable(problemEntity.getACTimes()).ifPresent(acTimes->problemEntity.setACTimes(acTimes+1));
//                problemEntity.setACTimes(1);
                break;
            case TLE:
                Optional.ofNullable(problemEntity.getTLETimes()).ifPresent(TLETimes->problemEntity.setTLETimes(TLETimes+1));
//                problemEntity.setTLETimes(1);
                break;
            case RTE:
                Optional.ofNullable(problemEntity.getRTETimes()).ifPresent(RTETimes->problemEntity.setRTETimes(RTETimes+1));
//                problemEntity.setRTETimes(1);
                break;
            case WA:
                Optional.ofNullable(problemEntity.getWATimes()).ifPresent(WATimes->problemEntity.setWATimes(WATimes+1));
//                problemEntity.setWATimes(1);
                break;
            case CE:
                Optional.ofNullable(problemEntity.getCETimes()).ifPresent(CETimes->problemEntity.setCETimes(CETimes+1));
//                problemEntity.setCETimes(1);
                break;
        }
        //保存题目
        mongoTemplate.save(problemEntity);
    }
}
