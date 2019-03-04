package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProblemService;
import com.dcoj.service.TagProblemService;
import com.dcoj.service.TagService;
import com.dcoj.util.WebUtil;
import org.ehcache.Cache;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author WANGQING
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private TagProblemService tagProblemService;

    @Resource
    private TagService tagService;

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
    //TODO：事务
    public void removeProblem(long pid) {
        Update update = new Update();
        update.set("is_deleted", true);
        Query query = new Query();
        query.addCriteria(Criteria.where("p_id").is(pid));
        tagProblemService.removeProblemTag(pid);
        try {
            mongoTemplate.findAndModify(query, update, ProblemEntity.class);
        }catch (Exception e){
            throw new WebErrorException("删除题目失败");
        }
    }

    @Override
    public List<ProblemEntity> listByType(int type) {
        return mongoTemplate.find(new Query(Criteria.where("is_deleted").is(false).
                andOperator(Criteria.where("type").is(type))),ProblemEntity.class);
    }

    @Override
    public ProblemEntity getById(long pid) {
        return mongoTemplate.findOne(new Query(Criteria.where("p_id").is(pid).
                andOperator(Criteria.where("is_deleted").is(false))), ProblemEntity.class);
    }

    @Override
    public List<ProblemEntity> listAll() {
        return mongoTemplate.find(new Query(Criteria.where("is_deleted").is(false)),ProblemEntity.class);
    }

    @Override
    //TODO:事务操作
    public void updateProblem(JSONArray tags, ProblemEntity problemEntity) {
        List<Long> newTagList = new ArrayList<>(tags.size());
        long pid = problemEntity.getPid();
        List<Long> oldTagList = tagProblemService.getProblemTags(pid);
        //更新原本未修改题目的标签使用次数
        for (long tid:oldTagList) {
            tagService.updateTagUsedTimes(tid,false);
        }
        //更新修改后的题目标签使用次数
        for(int i=0; i<tags.size(); i++) {
            long tid = tags.getLong(i);
            tagService.updateTagUsedTimes(tid,true);
            newTagList.add(tid);
        }
        if (newTagList.size() == 0) {
            throw new WebErrorException("标签非法");
        }
        problemEntity.setUpdateTime(new Date());
        try {
            mongoTemplate.save(problemEntity);
            tagProblemService.saveProblemTag(problemEntity.getPid(),newTagList);
        }catch (Exception e){
            throw new WebErrorException("更新题目失败");
        }
    }

    @Override
    //TODO:事务操作
    public void save(JSONArray tags, ProblemEntity problemEntity) {
        List<Long> tagList = new ArrayList<>(tags.size());
        for(int i=0; i<tags.size(); i++) {
            long tid = tags.getLong(i);
            tagService.updateTagUsedTimes(tid,true);
            tagList.add(tid);
        }
        if (tagList.size() == 0) {
            throw new WebErrorException("标签非法");
        }
        Cache<String,Long> idGenerateCache = GlobalCacheManager.getIdGenerateCache();
        problemEntity.setPid(idGenerateCache.get("pidGenerate")+1);
        problemEntity.setUpdateTime(new Date());
        problemEntity.setCreateTime(new Date());
        problemEntity.setACTimes(0);
        problemEntity.setWATimes(0);
        if (problemEntity.getType() == 3){
            problemEntity.setCETimes(0);
            problemEntity.setRTETimes(0);
            problemEntity.setTLETimes(0);
        }
        try {
            mongoTemplate.save(problemEntity);
            tagProblemService.saveProblemTag(problemEntity.getPid(),tagList);
            idGenerateCache.put("pidGenerate",idGenerateCache.get("pidGenerate")+1);
        }catch (Exception e){
            throw new WebErrorException("保存题目失败");
        }
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
