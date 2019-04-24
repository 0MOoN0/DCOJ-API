package com.dcoj.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ObjectProblemTagMapper {

    /**
     * 为某道题添加一个或者多个标签
     *
     * @param objectProblemId 题目id
     * @param objectTagId 标签id
     * @return 返回值为1时，则保存成功，否则失败
     */
    int save(@Param("objectProblemId") int objectProblemId, @Param("objectTagId") int objectTagId);

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
     * @return 返回值不为0时，删除成功，为0则删除失败
     */
    int removeProblemAllTags(int objectProblemId);

}