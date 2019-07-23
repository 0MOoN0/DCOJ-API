package com.dcoj.service;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.judge.ResultEnum;

import java.util.List;
import java.util.Map;

/**
 * 编程题 业务层
 *
 * @author WANGQING
 * @author Leon
 */
public interface ProgramProblemService {

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    int countProgramProblems();

    /**
     * 删除一道题目
     *
     * @param programProblemId 题目id
     */
    void removeByPrimaryKey(int programProblemId);

    /**
     * 更新一道题目信息
     *
     * @param programProblemId     要修改的题目id
     * @param newTags              更新后题目的标签
     * @param programProblemEntity 题目实体类对象
     */
    void updateProblemAndTags(Integer programProblemId, JSONArray newTags, ProgramProblemEntity programProblemEntity);

    /**
     * 通过编号查询题目
     *
     * @param programProblemId 题目id
     * @return 题目实体类对象
     */
    ProgramProblemEntity getByPrimaryKey(int programProblemId);

    /**
     * 添加一道题目
     *
     * @param tags                 题目标签
     * @param programProblemEntity 题目实体类对象
     * @return 题目id
     */
    int save(JSONArray tags, ProgramProblemEntity programProblemEntity);

    /**
     * 根据判卷状态更新Problem
     *
     * @param pid    题目业务id
     * @param result 判卷结果
     */
    void updateProblemTimes(int pid, ResultEnum result);

    /**
     * 通过 pid 查询该题所有tag
     *
     * @param programProblemId 题目id
     * @return 结果
     */
    List<Map<String, Object>> listProgramProblemTagsByPid(int programProblemId);

    /**
     * 查询所有编程题目
     *
     * @param list      标签列表
     * @param uid       用户id
     * @param difficult 难度
     * @param query     查询关键字
     * @return 结果
     */
    List<Map<String, Object>> listAll(List<Integer> list,
                                      Integer uid,
                                      Integer difficult,
                                      String query);


}
