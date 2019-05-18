package com.dcoj.dao;

import com.dcoj.entity.TestCaseEntity;
import com.dcoj.entity.example.TestCaseEntityExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 测试用例Mapper
 *
 * @author Leon
 */
@Repository
public interface TestCaseMapper {
    /**
     * 根据Example查询条件计算匹配数量
     *
     * @param example 查询条件
     * @return 查询到的数量
     */
    long countByExample(TestCaseEntityExample example);

    /**
     * 根据Example查询条件删除
     *
     * @param example 查询条件
     * @return 删除的数量
     */
    int deleteByExample(TestCaseEntityExample example);

    /**
     * 根据主键删除TestCase
     *
     * @param tcId 要删除的TestCaseID
     * @return 删除的条数
     */
    int deleteByPrimaryKey(Integer tcId);

    /**
     * 插入一条数据
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record 要进行插入的Entity
     * @return 插入的数量
     */
    int insert(TestCaseEntity record);

    /**
     * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record 要进行插入的Entity
     * @return 插入的数量
     */
    int insertSelective(TestCaseEntity record);

    /**
     * 根据Example查询条件查询
     *
     * @param example 查询条件
     * @return 查询结果
     */
    List<TestCaseEntity> selectByExample(TestCaseEntityExample example);

    /**
     * 根据Example查询条件查询第一条数据，使用limit语法限制数据
     *
     * @param example 查询条件
     * @return 查询结果
     */
    TestCaseEntity selectOneByExample(TestCaseEntityExample example);

    /**
     * 根据主键查询Submission
     *
     * @param tcId TestCaseID
     * @return 根据TestCaseID查询到的TestCase
     */
    TestCaseEntity selectByPrimaryKey(Integer tcId);

    /**
     * 按条件更新值不为null的字段
     *
     * @param record  要更新的Entity
     * @param example 查询条件
     * @return 更新的条数
     */
    int updateByExampleSelective(@Param("record") TestCaseEntity record, @Param("example") TestCaseEntityExample example);

    /**
     * 按条件更新
     *
     * @param record  要更新的Entity
     * @param example 查询条件
     * @return 更新的条数
     */
    int updateByExample(@Param("record") TestCaseEntity record, @Param("example") TestCaseEntityExample example);

    /**
     * 更新新的model中不为空的字段
     *
     * @param record 要更新的Entity
     * @return 更新的条数
     */
    int updateByPrimaryKeySelective(TestCaseEntity record);

    /**
     * 根据ID更新model，如果model里面的字段为空，则数据库中的对应字段会被设为NULL
     *
     * @param record 要更新的Entity
     * @return 更新的条数
     */
    int updateByPrimaryKey(TestCaseEntity record);
}