package com.dcoj.dao;

import com.dcoj.entity.ProgramProblemTagEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目标签关联持久层
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
     * 通过pid 获取 TagProblemEntity对象
     *
     * @param pid 题目id
     * @return 返回 ProgramProblemTagEntity 实体类对象
     */
//    ProgramProblemTagEntity getByPid(int pid);

    /**
     * 通过 pid 删除TagProblemEntity对象
     *
     * @param programProblemId 题目id
     * @return 返回值不为0时，删除成功，为0则删除失败
     */
    int removeProblemAllTags(int programProblemId);

    /**
     * 通过 pid 和 tid 删除一条记录
     *
     * @param pid 题目id
     * @param tid 标签id
     * @return 返回值为1时，删除成功，为0则删除失败
     */
//    int removeProblemTag(@Param("pid") int pid, @Param("tid") int tid);

}