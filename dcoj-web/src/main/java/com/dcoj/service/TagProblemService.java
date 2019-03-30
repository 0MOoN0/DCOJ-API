package com.dcoj.service;

import com.dcoj.entity.TagProblemEntity;

import java.util.List;

/**
 *  题目标签关联业务层
 *
 * @author WANGQING
 */
public interface TagProblemService {
    /**
     * 为某道题添加一个或者多个标签
     *
     * @param pid 题目id
     * @param tid 标签id
     */
    void save(int pid, int tid);

    /**
     * 统计某道题标签的个数
     *
     * @param pid 题目id
     * @return 返回该题目的标签个数
     */
    int countTagsByPid(int pid);

    /**
     * 得到某道题的所有标签
     *
     * @param pid 题目id
     * @return 包含所有标签id的List集合
     */
    List<Integer> getTagsByPid(int pid);

    /**
     * 通过pid 获取 TagProblemEntity对象
     *
     * @param pid 题目id
     * @return 返回 TagProblemEntity 实体类对象
     */
    TagProblemEntity getByPid(int pid);

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
    void removeProblemTag(int pid, int tid);
}
