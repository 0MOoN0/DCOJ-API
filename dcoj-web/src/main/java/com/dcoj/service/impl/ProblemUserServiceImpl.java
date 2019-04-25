package com.dcoj.service.impl;

import com.dcoj.dao.ProblemUserMapper;
import com.dcoj.entity.ProblemUserEntity;
import com.dcoj.entity.example.ProblemUserEntityExample;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProblemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ProblemUserService实现
 * @author Leon
 */
@Service
public class ProblemUserServiceImpl implements ProblemUserService {

    @Autowired
    private ProblemUserMapper problemUserMapper;

    /**
     * 根据题目id、用户id、做题结果保存
     *
     * @param pid    题目业务id
     * @param uid    用户id
     * @param status 做题状态
     */
    @Override
    public void save(int pid, int uid, ResultEnum status) {
        ProblemUserEntity problemUserEntity = new ProblemUserEntity();
        problemUserEntity.setUid(uid);
        problemUserEntity.setStatus(status);
        problemUserEntity.setPid(pid);
        problemUserMapper.insert(problemUserEntity);
    }

    /**
     * 根据用户id和题目业务id获取ProblemUser
     *
     * @param pid 题目业务id
     * @param uid 用户id
     * @return ProblemUser
     */
    @Override
    public ProblemUserEntity getByPidUid(int pid, int uid) {
        ProblemUserEntityExample example = new ProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(pid).andUidEqualTo(uid);
        List<ProblemUserEntity> problemUserEntities = problemUserMapper.selectByExample(example);
        if(Optional.ofNullable(problemUserEntities).isPresent()){
            return problemUserEntities.get(0);
        }
        return null;
    }

    /**
     * 根据用户id获取ProblemUser
     *
     * @param uid 用户id
     * @return ProblemUser集合
     */
    @Override
    public List<Map<String, Object>> listUserProblemHistory(int uid) {
        List<Map<String, Object>> maps = problemUserMapper.listUserProblemsByUid(uid);
        return maps;
    }

    /**
     * 根据用户id、题目id、做题结果更新ProblemUser
     *
     * @param pid
     * @param uid
     * @param result
     */
    @Override
    public void updateByPidUid(int pid, int uid, ResultEnum result) {
        ProblemUserEntity problemUserEntity = new ProblemUserEntity();
        problemUserEntity.setUid(uid);
        problemUserEntity.setPid(pid);
        problemUserEntity.setStatus(result);
        ProblemUserEntityExample example = new ProblemUserEntityExample();
        example.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
        problemUserMapper.updateByExample(problemUserEntity,example);
    }
}
