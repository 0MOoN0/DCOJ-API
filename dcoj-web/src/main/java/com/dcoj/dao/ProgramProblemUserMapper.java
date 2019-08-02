package com.dcoj.dao;

import com.dcoj.entity.ProgramProblemUserEntity;
import com.dcoj.entity.example.ProgramProblemUserEntityExample;

import java.util.List;
import java.util.Map;

import com.dcoj.judge.ResultEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ProblemUser实体DAO
 *
 * @author Leon
 */
@Repository
public interface ProgramProblemUserMapper {
    /**
     * 根据Example查询条件计算匹配数量
     *
     * @param example 查询条件
     * @return 查询到的数量
     */
    long countByExample(ProgramProblemUserEntityExample example);

    /**
     * 根据Example查询条件删除
     *
     * @param example 查询条件
     * @return 删除的数量
     */
    int deleteByExample(ProgramProblemUserEntityExample example);

    /**
     * 插入一条数据
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record 要进行插入的Entity
     * @return 插入的数量
     */
    int insert(ProgramProblemUserEntity record);

    /**
     * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record 要进行插入的Entity
     * @return 插入的数量
     */
    int insertSelective(ProgramProblemUserEntity record);

    /**
     * 根据Example查询条件查询
     *
     * @param example 查询条件
     * @return 查询结果
     */
    List<ProgramProblemUserEntity> selectByExample(ProgramProblemUserEntityExample example);

    /**
     * 按条件更新值不为null的字段
     *
     * @param record  要更新的Entity
     * @param example 查询条件
     * @return 更新的条数
     */
    int updateByExampleSelective(@Param("record") ProgramProblemUserEntity record, @Param("example") ProgramProblemUserEntityExample example);

    /**
     * 按条件更新
     *
     * @param record  要更新的Entity
     * @param example 查询条件
     * @return 更新的条数
     */
    int updateByExample(@Param("record") ProgramProblemUserEntity record, @Param("example") ProgramProblemUserEntityExample example);

    /**
     * 根据用户id查询对应的Problem title
     *
     * @param uid 用户ID
     * @param result 用户做题题目状态
     * @return Map：{key: columnName, value: columnValue}
     */
    List<Map<String, Object>> listUserProblemsByUid(int uid, String result);

}