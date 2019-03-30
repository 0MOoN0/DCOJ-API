package com.dcoj.dao;

import com.dcoj.entity.TagProblemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目标签关联持久层
 *
 * @author WANGQING
 */
public interface TagProblemMapper {

    /**
     * 为某道题添加一个或者多个标签
     *
     * @param pid 题目id
     * @param tid 标签id
     * @return 返回值为1时，则保存成功，否则失败
     */
    int save(@Param("pid") int pid, @Param("tid") int tid);

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
     * @return 返回值不为0时，删除成功，为0则删除失败
     */
    int removeProblemAllTags(int pid);

    /**
     * 通过 pid 和 tid 删除一条记录
     *
     * @param pid 题目id
     * @param tid 标签id
     * @return 返回值为1时，删除成功，为0则删除失败
     */
    int removeProblemTag(@Param("pid") int pid, @Param("tid") int tid);

}
