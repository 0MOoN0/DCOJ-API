package com.dcoj.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 编程题、标签关联 业务层
 *
 * @author WANGQING
 */
public interface ProgramProblemTagService {
    /**
     * 为某道题添加一个或者多个标签
     *
     * @param programProblemId 题目id
     * @param programTagId     标签id
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
     * @param programProblemId 题目id
     * @return 包含所有标签id的List集合
     */
    List<Integer> getTagsByProgramProblemId(int programProblemId);

    /**
     * 通过 pid 删除TagProblemEntity对象
     *
     * @param programProblemId 题目id
     */
    void removeProblemAllTags(int programProblemId);

    /**
     *  批量新增记录
     *
     * @param programProblemId 题目id
     * @param tagIdList 标签数组id
     */
    int batchInsert(int programProblemId,Integer[] tagIdList);
}
