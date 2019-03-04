package com.dcoj.service;

import com.dcoj.entity.TagProblemEntity;

import java.util.List;

/**
 *
 * @author WANGQING
 */
public interface TagProblemService {
    /**
     * 为某道题添加一个或者多个标签
     * @param pid
     * @param tids
     */
    void saveProblemTag(long pid,List<Long> tids);


    /**
     * 统计某道题标签的个数
     * @param pid
     * @return
     */
    long countTagProblems(long pid);

    /**
     * 得到某道题的所有标签
     * @param pid
     * @return
     */
    List<Long> getProblemTags(long pid);

    /**
     * 通过pid 获取 TagProblemEntity对象
     * @param pid
     * @return
     */
    TagProblemEntity getByPid(long pid);

    /**
     * 通过pid 删除
     * @param pid TagProblemEntity对象
     */
    void removeProblemTag(long pid);
}
