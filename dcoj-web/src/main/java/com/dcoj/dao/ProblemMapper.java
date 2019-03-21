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
     * @return
     */
    int countProblems();

    /**
     * 根据题目类型统计题目数量
     *
     * @return
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
     * @return
     */
    List<ProblemEntity> listAll();

    /**
     * 根据题目类型查询题目
     *
     * @param type
     * @return
     */
    List<ProblemEntity> listByType(int type);

    /**
     * 通过编号查询题目
     *
     * @param pid
     * @return
     */
    ProblemEntity getById(int pid);

    /**
     * 添加一道题目
     *
     * @param problemEntity
     * @return 返回值为1时，保存成功，为0则保存失败
     */
    int save(ProblemEntity problemEntity);

    /**
     * 根据判卷状态更新Problem
     * @param pid           题目业务id
     * @param result        判卷结果
     */
    //TODO: 2019.03.18 WANGQING 未在mapper.xml里写此方法
    //void updateProblemTimes(int pid, ResultEnum result);


}
