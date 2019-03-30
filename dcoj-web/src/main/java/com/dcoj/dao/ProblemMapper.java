package com.dcoj.dao;

import com.dcoj.entity.ProblemEntity;

import java.util.List;

/**
 * 题目管理持久层
 *
 * @author WANGQING
 */
public interface ProblemMapper {

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    int countProblems();

    /**
     * 根据题目类型统计题目数量
     *
     * @return 根据题目类型返回该类型的题目数量
     */
    int countProblemsByType(int type);

    /**
     * 删除一道题目
     *
     * @param pid 返回值为1时，删除成功，为0则删除失败
     */
    int removeByPid(int pid);


    /**
     * 更新一道题目信息
     *
     * @param problemEntity 返回值为1时更新成功，否则失败
     */
    int updateProblem(ProblemEntity problemEntity);

    /**
     * 查询所有题目
     *
     * @return 包含所有题目的List集合
     */
    List<ProblemEntity> listAll();

    /**
     * 根据题目类型查询题目
     *
     * @param type 所选题目类型
     * @return 包含该类型所有题目的List集合
     */
    List<ProblemEntity> listByType(int type);

    /**
     * 通过编号查询题目
     *
     * @param pid 题目id
     * @return 题目实体类对象
     */
    ProblemEntity getById(int pid);

    /**
     * 添加一道题目
     *
     * @param problemEntity 题目实体类对象
     * @return 返回值为1时，保存成功，为0则保存失败
     */
    int save(ProblemEntity problemEntity);

    /**
     * 根据判卷状态更新Problem
     *
     * @param pid           题目业务id
     * @param result        判卷结果
     */
    //TODO: 2019.03.18 WANGQING 未在mapper.xml里写此方法
    //void updateProblemTimes(int pid, ResultEnum result);


}
