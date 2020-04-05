package com.dcoj.service;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.entity.ObjectProblemEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 客观题 业务层
 *
 * @author WANGQING Leon
 */
public interface ObjectProblemService {
    /**
     * 删除一道题目
     *
     * @param objectProblemId 题目id
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
     * 统计今天题目新增数量
     *
     * @return 返回今天新增题目数量
     */
    int countObjectProblemsToday();

    /**
     * 统计昨天题目新增数量
     *
     * @return 返回昨天新增题目数量
     */
    int countObjectProblemsYesterday();

    /**
     * 统计当前月题目新增数量
     *
     * @return 返回当月新增题目数量
     */
    int countObjectProblemsMonth();

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

    /**
     * 判断用户提交的答案是否正确
     *
     * @param objectProblemId 客观题id
     * @param userAnswer      用户提交的答案
     * @return 返回值为1时，答案正确，为0则答案错误
     */
    Integer judgeObjectProblem(Integer objectProblemId, String userAnswer);

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
     * @param type 题目类型
     * @return 结果
     */
    List<Map<String, Object>> listAll(List<Integer> list,
                                      Integer uid,
                                      String query,
                                      Integer type);
    /**
     * 更新题目次数内容
     * @param objectProblemId       题目ID
     * @param status                做题状态
     * @return
     */
    int updateProblemTimesByUidPid(Integer objectProblemId, Byte status);

    /**
     *  通过试卷id查询对应题目
     * @param examId
     * @return
     */
    List<Map<String,Object>> listByExamIdAndType(int examId);

    /**
     *  通过类别id查询关联题目信息
     * @param cateId
     * @return
     */
    List<Map<String,Object>> listAllByCateId(int cateId);
    /**
     * 查询所有客观题题目
     * @param query 查询关键字
     * @return 结果
     */
    List<Map<String, Object>> findAllNotByPage(String query);

    /**
     * 批量导入客观题
     * @param files 选择题Excel文件
     * @return 结果
     */
    String importObjectProblem(MultipartFile files) throws IOException;
}
