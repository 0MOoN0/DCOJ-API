package com.dcoj.service;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.judge.ResultEnum;

import java.util.List;

/**
 * 题目业务层
 * @author WANGQING
 * @author Leon
 */
public interface ProblemService {
    /**
     * 统计题目数量
     * @return
     */
    long countProblems();

    /**
     * 根据题目类型统计题目数量
     * @return
     */
    long countProblems(int type);

    /**
     * 删除一道题目
     * @param objectId
     */
    void removeProblem(int objectId);


    /**
     * 更新一道题目信息
     * @param pid
     * @param tags
     * @param problemEntity
     */
    void updateProblem(int pid, JSONArray tags, ProblemEntity problemEntity);

    /**
     * 查询所有题目
     * @return
     */
    List<ProblemEntity> listAll();

    /**
     * 根据题目类型查询题目
     * @param type
     * @return
     */
    List<ProblemEntity> listByType(int type);

    /**
     * 通过编号查询题目
     * @param pid
     * @return
     */
    ProblemEntity getById(int pid);

    /**
     * 添加一道题目
     * @param tags
     * @param problemEntity
     * @return
     */
    int save(JSONArray tags,ProblemEntity problemEntity);

    /**
     * 根据判卷状态更新Problem
     * @param pid           题目业务id
     * @param result        判卷结果
     */
    void updateProblemTimes(int pid, ResultEnum result);

}
