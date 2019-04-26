package com.dcoj.dao;

import com.dcoj.entity.SubmissionDetailEntity;
import com.dcoj.entity.example.SubmissionDetailEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Leon
 */
@Repository
public interface SubmissionDetailMapper {
    long countByExample(SubmissionDetailEntityExample example);

    int deleteByExample(SubmissionDetailEntityExample example);

    int deleteByPrimaryKey(Integer sdId);

    int insert(SubmissionDetailEntity record);

    int insertSelective(SubmissionDetailEntity record);

    List<SubmissionDetailEntity> selectByExample(SubmissionDetailEntityExample example);

    SubmissionDetailEntity selectByPrimaryKey(Integer sdId);

    int updateByExampleSelective(@Param("record") SubmissionDetailEntity record, @Param("example") SubmissionDetailEntityExample example);

    int updateByExample(@Param("record") SubmissionDetailEntity record, @Param("example") SubmissionDetailEntityExample example);

    int updateByPrimaryKeySelective(SubmissionDetailEntity record);

    int updateByPrimaryKey(SubmissionDetailEntity record);
}