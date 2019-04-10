package com.dcoj.dao;

import com.dcoj.entity.SubmissionEntity;
import com.dcoj.entity.SubmissionEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Submission实体DAO
 * @author Leon
 */
@Repository
public interface SubmissionMapper {
    /**
     * 根据Example查询条件计算匹配数量
     * @param example   查询条件
     * @return          查询到的数量
     */
    long countByExample(SubmissionEntityExample example);

    /**
     * 根据Example查询条件删除
     * @param example   查询条件
     * @return          删除的数量
     */
    int deleteByExample(SubmissionEntityExample example);

    /**
     * 根据主键删除Submission
     * @param subId     要删除的SubmissionID
     * @return          删除的条数
     */
    int deleteByPrimaryKey(Integer subId);

    /**
     * 插入一条数据
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     * @param record    要进行插入的Entity
     * @return          插入的数量
     */
    int insert(SubmissionEntity record);

    /**
     * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     * @param record    要进行插入的Entity
     * @return          插入的数量
     */
    int insertSelective(SubmissionEntity record);

    /**
     * 根据Example查询条件查询
     * @param example   查询条件
     * @return          查询结果
     */
    List<SubmissionEntity> selectByExample(SubmissionEntityExample example);

    /**
     * 根据主键查询Submission
     * @param subId     SubmissionID
     * @return          根据SubmissionID查询到的Submission
     */
    SubmissionEntity selectByPrimaryKey(Integer subId);

    /**
     * 按条件更新值不为null的字段
     * @param record    要更新的Entity
     * @param example   查询条件
     * @return          更新的条数
     */
    int updateByExampleSelective(@Param("record") SubmissionEntity record, @Param("example") SubmissionEntityExample example);

    /**
     * 按条件更新
     * @param record    要更新的Entity
     * @param example   查询条件
     * @return          更新的条数
     */
    int updateByExample(@Param("record") SubmissionEntity record, @Param("example") SubmissionEntityExample example);

    /**
     * 更新新的model中不为空的字段
     * @param record    要更新的Entity
     * @return          更新的条数
     */
    int updateByPrimaryKeySelective(SubmissionEntity record);

    /**
     * 根据ID更新Submission，如果Submission里面的字段为空，则数据库中的对应字段会被设为NULL
     * @param record     要更新的Submission
     * @return           更新的条数
     */
    int updateByPrimaryKey(SubmissionEntity record);

}