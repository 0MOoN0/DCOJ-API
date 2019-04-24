package com.dcoj.service;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ObjectProblemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客观题 业务层
 *
 * @author WANGQING
 */
public interface ObjectProblemService {
    /**
     * 删除一道题目
     *
     * @param objectProblemId 题目id
     * @return 返回值为1时，删除成功，为0则删除失败
     */
    void removeByPrimaryKey(Integer objectProblemId);

    /**
     * 插入一道题目
     *
     * @param tags   题目id集合
     * @param record 客观题实体类对象
     * @return 返回题目id
     */
    int insertSelective(JSONArray tags, ObjectProblemEntity record);

    /**
     * 通过题目objectProblemId查找题目
     *
     * @param objectProblemId 题目id
     * @return 客观题实体类对象
     */
    ObjectProblemEntity getByPrimaryKey(Integer objectProblemId);

    /**
     * 更新一道题目和题目关联的标签
     *
     * @param newTags 更新的标签id集合
     * @param record  客观题实体类对象
     */
    void updateProblemAndTags(Integer objectProblemId, JSONArray newTags, ObjectProblemEntity record);

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    int countObjectProblems();

    /**
     * 根据题目类型统计题目数量
     *
     * @param type 题目类型
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

//    /**
//     * 查询所有题目
//     *
//     * @return 包含所有题目的List集合
//     */
//    List<ObjectProblemEntity> listAll();

    /**
     * 判断用户提交的答案是否正确
     *
     * @param objectProblemId 客观题id
     * @param userAnswer      用户提交的答案
     * @return 返回值为1时，答案正确，为0则答案错误
     */
    int judgeObjectProblem(Integer objectProblemId, String userAnswer);

    /**
     * 根据题目状态查询所有题目
     *
     * @param status 题目状态
     * @return 包含该状态下的所有题目的List集合
     */
    List<ObjectProblemEntity> listByStatus(Integer status);

    /**
     * 根据题目状态统计题目数量
     *
     * @return 根据题目状态返回该类型的题目数量
     */
    int countObjectProblemsByStatus(Integer status);

    /**
     * 根据状态、类型统计客观题数量(不加参数则查询全部)
     *
     * @param status 题目状态
     * @param type 题目类型
     * @return 根据题目状态、题目类型返回该类型的题目数量
     */
    List<ObjectProblemEntity> listAll(Integer status, Integer type);
}
