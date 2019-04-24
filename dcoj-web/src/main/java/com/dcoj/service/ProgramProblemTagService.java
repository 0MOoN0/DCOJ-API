package com.dcoj.service;

import com.dcoj.entity.ProgramProblemTagEntity;

import java.util.List;

/**
 *  题目标签关联业务层
 *
 * @author WANGQING
 */
public interface ProgramProblemTagService {
    /**
     * 为某道题添加一个或者多个标签
     *
     * @param programProblemId 题目id
     * @param programTagId 标签id
     */
    void save(int programProblemId, int programTagId);

    /**
     * 统计某道题标签的个数
     *
     * @param programTagId 题目id
     * @return 返回该题目的标签个数
     */
    int countTagsByProgramProblemId(int programTagId);

    /**
     * 得到某道题的所有标签
     *
     * @param programTagId 题目id
     * @return 包含所有标签id的List集合
     */
    List<Integer> getTagsByProgramProblemId(int programTagId);

    /**
     * 通过pid 获取 TagProblemEntity对象
     *
     * @param pid 题目id
     * @return 返回 ProgramProblemTagEntity 实体类对象
     */
//    ProgramProblemTagEntity getByPid(int pid);

    /**
     * 通过 pid 删除TagProblemEntity对象
     *
     * @param pid 题目id
     */
    void removeProblemAllTags(int pid);

    /**
     * 通过 pid 和 tid 删除一条记录
     *
     * @param pid 题目id
     * @param tid 标签id
     */
//    void removeProblemTag(int pid, int tid);
}
