package com.dcoj.service.impl;

import com.dcoj.dao.ProgramProblemUserMapper;
import com.dcoj.entity.ProgramProblemUserEntity;
import com.dcoj.entity.example.ProgramProblemUserEntityExample;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProgramProblemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ProblemUserService实现
 * @author Leon
 */
@Service
public class ProgramProblemUserServiceImpl implements ProgramProblemUserService {

    @Autowired
    private ProgramProblemUserMapper problemUserMapper;

    /**
     * 根据题目id、用户id、做题结果保存
     *
     * @param pid    题目业务id
     * @param uid    用户id
     * @param status 做题状态
     */
    @Override
    public void save(int pid, int uid, ResultEnum status) {
        ProgramProblemUserEntity problemUserEntity = new ProgramProblemUserEntity();
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
    public ProgramProblemUserEntity getByPidUid(int pid, int uid) {
        ProgramProblemUserEntityExample example = new ProgramProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(pid).andUidEqualTo(uid);
        List<ProgramProblemUserEntity> problemUserEntities = problemUserMapper.selectByExample(example);
        if(problemUserEntities.size()>0){
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
        ProgramProblemUserEntity problemUserEntity = new ProgramProblemUserEntity();
        problemUserEntity.setUid(uid);
        problemUserEntity.setPid(pid);
        problemUserEntity.setStatus(result);
        ProgramProblemUserEntityExample example = new ProgramProblemUserEntityExample();
        example.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
        problemUserMapper.updateByExample(problemUserEntity,example);
    }
}
