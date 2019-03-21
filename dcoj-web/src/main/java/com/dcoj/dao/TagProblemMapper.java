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
     * @param pid
     * @param tid
     * @return 返回值为1时，则保存成功，否则失败
     */
    int save(@Param("pid") int pid, @Param("tid") int tid);

    /**
     * 统计某道题标签的个数
     * 
     * @param pid
     * @return
     */
    int countTagsByPid(int pid);

    /**
     * 得到某道题的所有标签
     * 
     * @param pid
     * @return
     */
    List<Integer> getTagsByPid(int pid);

    /**
     * 通过pid 获取 TagProblemEntity对象
     * 
     * @param pid
     * @return
     */
    TagProblemEntity getByPid(int pid);

    /**
     * 通过 pid 删除TagProblemEntity对象
     *
     * @param pid
     * @return 返回值为1时，删除成功，为0则删除失败
     */
    int removeProblemAllTags(int pid);

    /**
     * 通过 pid 和 tid 删除一条记录
     *
     * @param pid
     * @param tid
     * @return 返回值为1时，删除成功，为0则删除失败
     */
    int removeProblemTag(@Param("pid") int pid, @Param("tid") int tid);

}
