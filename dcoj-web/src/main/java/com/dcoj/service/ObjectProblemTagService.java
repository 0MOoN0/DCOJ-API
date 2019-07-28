package com.dcoj.service;

import java.util.List;

/**
 * 客观题、标签关联 业务层
 *
 * @author WANGQING
 */
public interface ObjectProblemTagService {
    /**
     * 为某道题添加一个或者多个标签
     *
     * @param objectProblemId 题目id
     * @param objectTagId     标签id
     */
    void save(int objectProblemId, int objectTagId);

    /**
     * 统计某道题标签的个数
     *
     * @param objectProblemId 题目id
     * @return 返回该题目的标签个数
     */
    int countTagsByObjectProblemId(int objectProblemId);

    /**
     * 得到某道题的所有标签
     *
     * @param objectProblemId 题目id
     * @return 包含所有标签id的List集合
     */
    List<Integer> getTagsByObjectProblemId(int objectProblemId);

    /**
     * 通过 objectProblemId 删除TagProblemEntity对象
     *
     * @param objectProblemId 题目id
     */
    void removeProblemAllTags(int objectProblemId);

}
