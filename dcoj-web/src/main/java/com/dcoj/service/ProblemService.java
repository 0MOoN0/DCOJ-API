package com.dcoj.service;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.judge.ResultEnum;

import java.util.List;
import java.util.Map;

/**
 * 题目业务层
 *
 * @author WANGQING
 * @author Leon
 */
public interface ProblemService {

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    int countProblems();

    /**
     * 根据题目类型统计题目数量
     *
     * @param type 所选类型
     * @return 根据题目类型返回该类型的题目数量
     */
    int countProblemsByType(int type);

    /**
     * 删除一道题目
     *
     * @param pid 题目id
     */
    void removeByPid(int pid);

    /**
     * 更新一道题目信息
     *
     * @param pid 要修改的题目id
     * @param newTags 更新后题目的标签
     * @param problemEntity 题目实体类对象
     */
    void updateProblem(int pid, JSONArray newTags, ProblemEntity problemEntity);

    /**
     * 查询所有题目
     *
     * @return 包含所有题目的List集合
     */
    List<ProblemEntity> listAll();

    /**
     * 根据题目类型查询题目
     *
     * @param type 所选题目类型
     * @return 包含该类型所有题目的List集合
     */
    List<ProblemEntity> listByType(int type);

    /**
     * 通过编号查询题目
     * @param pid 题目id
     * @return  题目实体类对象
     */
    ProblemEntity getById(int pid);

    /**
     * 添加一道题目
     *
     * @param tags 题目标签
     * @param problemEntity 题目实体类对象
     * @return 题目id
     */
    int save(JSONArray tags,ProblemEntity problemEntity);

    /**
     * 根据判卷状态更新Problem
     *
     * @param pid           题目业务id
     * @param result        判卷结果
     */
    void updateProblemTimes(int pid, ResultEnum result);

    /**
     * 通过 pid 查询该题所有tag
     *
     * @param pid 题目id
     * @return 结果
     */
    List<Map<String, Object>> listProblemTagsByPid(int pid);

}
