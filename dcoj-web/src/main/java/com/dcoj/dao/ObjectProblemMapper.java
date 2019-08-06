package com.dcoj.dao;

import com.dcoj.entity.ObjectProblemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客观题 持久层
 *
 * @author WANGQING
 */
public interface ObjectProblemMapper {
    /**
     * 删除一道题目
     *
     * @param objectProblemId 题目id
     * @return 返回值为1时，删除成功，为0则删除失败
     */
    int removeByPrimaryKey(Integer objectProblemId);

    /**
     * 插入一道题目
     *
     * @param record 客观题实体类对象
     * @return 返回值为1时，插入成功，为0则插入失败
     */
    int insertSelective(ObjectProblemEntity record);

    /**
     * 通过题目objectProblemId查找题目
     *
     * @param objectProblemId 题目id
     * @return 客观题实体类对象
     */
    ObjectProblemEntity getByPrimaryKey(Integer objectProblemId);

    /**
     * 更新一道题目
     *
     * @param record 客观题实体类对象
     * @return 返回值为1时，更新成功，为0则更新失败
     */
    int updateByPrimaryKeySelective(ObjectProblemEntity record);

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    int countObjectProblems();

    /**
     * 根据题目类型统计题目数量
     *
     * @return 根据题目类型返回该类型的题目数量
     */
    int countObjectProblemsByType(Integer type);

    /**
     * 通过 objectProblemId 查询该题所有tag
     *
     * @param objectProblemId 题目id
     * @return 结果
     */
    List<Map<String, Object>> listObjectProblemTagsByPrimaryKey(Integer objectProblemId);

    /**
     * 获取某道题答案
     *
     * @param objectProblemId 客观题id
     * @return 结果
     */
    String getAnswerByPrimaryKey(Integer objectProblemId);

    /**
     * 根据题目状态统计题目数量
     *
     * @return 根据题目状态返回该类型的题目数量
     */
    int countObjectProblemsByStatus(Integer status);

    /**
     * 查询所有编程题目
     *
     * @param list  标签列表
     * @param uid   用户id
     * @param query 查询关键字
     * @return 结果
     */
    List<Map<String, Object>> listAll(@Param("tagList") List<Integer> list,
                                      @Param("uid") Integer uid,
                                      @Param("query") String query);


    // TODO: Leon 20190806 ：完成updateProblemTimes(更新题目次数)相关内容

}