package com.dcoj.service;


import com.dcoj.entity.ProblemUserEntity;
import com.dcoj.judge.ResultEnum;

import java.util.List;
import java.util.Map;

/**
 * ProblemUser 服务类
 * @author Leon
 **/
public interface ProblemUserService {

    /**
     * 根据题目id、用户id、做题结果保存
     * @param pid       题目业务id
     * @param uid       用户id
     * @param status    做题状态
     */
    void save(int pid, int uid, ResultEnum status);

    /**
     * 根据用户id和题目业务id获取ProblemUser
     * @param pid       题目业务id
     * @param uid       用户id
     * @return          ProblemUser
     */
    ProblemUserEntity get(int pid, int uid);

    /**
     * 根据用户id获取ProblemUser
     * @param uid       用户id
     * @return          ProblemUser集合
     */
    List<Map<String, Object>> listUserProblemHistory(int uid);

    /**
     * 根据用户id、题目id、做题结果更新ProblemUser
     * @param pid
     * @param uid
     * @param result
     */
    void updateByPidUid(int pid, int uid, ResultEnum result);
}
