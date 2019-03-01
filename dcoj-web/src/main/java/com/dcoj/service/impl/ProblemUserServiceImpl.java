package com.dcoj.service.impl;

import com.dcoj.entity.ProblemUserEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProblemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ProblemUserService实现
 * @author Leon
 */
@Service
public class ProblemUserServiceImpl implements ProblemUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据题目id、用户id、做题结果保存
     *
     * @param pid    题目业务id
     * @param uid    用户id
     * @param status 做题状态
     */
    @Override
    public void save(int pid, String uid, ResultEnum status) {
        ProblemUserEntity problemUserEntity = new ProblemUserEntity();
        problemUserEntity.setUid(uid);
        problemUserEntity.setStatus(status);
        problemUserEntity.setPid(pid);
        mongoTemplate.save(problemUserEntity);
    }

    /**
     * 根据用户id和题目业务id获取ProblemUser
     *
     * @param pid 题目业务id
     * @param uid 用户id
     * @return ProblemUser
     */
    @Override
    public ProblemUserEntity get(int pid, String uid) {
        ProblemUserEntity entity = mongoTemplate.findOne(new Query(Criteria.where("pid").is(pid))
                .addCriteria(Criteria.where("uid").is(uid)), ProblemUserEntity.class);
        return entity;
    }

    /**
     * 根据用户id获取ProblemUser
     *
     * @param uid 用户id
     * @return ProblemUser集合
     */
    @Override
    public List<Map<String, Object>> listUserProblemHistory(String uid) {
        return null;
    }

    /**
     * 根据用户id、题目id、做题结果更新ProblemUser
     *
     * @param pid
     * @param uid
     * @param result
     */
    @Override
    public void updateByPidUid(int pid, String uid, ResultEnum result) {
        Query query = new Query(Criteria.where("pid").is(pid).and("uid").is(uid));
        Update update = new Update();
        update.set("result",result);
        mongoTemplate.findAndModify(query,update,ProblemUserEntity.class);
    }
}
