package com.dcoj.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 编程题、标签关联 持久层
 *
 * @author WANGQING
 */
public interface ProgramProblemTagMapper {

    /**
     * 为某道题添加一个或者多个标签
     *
     * @param programProblemId 题目id
     * @param programTagId     标签id
     * @return 返回值为1时，则保存成功，否则失败
     */
    int save(@Param("programProblemId") int programProblemId, @Param("programTagId") int programTagId);

    /**
     * 统计某道题标签的个数
     *
     * @param programProblemId 题目id
     * @return 返回该题目的标签个数
     */
    int countTagsByProgramProblemId(int programProblemId);

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
     * @return 返回值不为0时，删除成功，为0则删除失败
     */
    int removeProblemAllTags(@Param("programProblemId") int programProblemId);

    /**
     *  批量新增记录
     *
     * @param programProblemId 题目id
     * @param tagIdList 标签数组id
     */
    int batchInsert(@Param("programProblemId") int programProblemId,@Param("tagIdList")  Integer[] tagIdList);

}
