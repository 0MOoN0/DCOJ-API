package com.dcoj.dao;

import com.dcoj.entity.ProgramSubmissionDetailEntity;
import com.dcoj.entity.example.ProgramSubmissionDetailEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Leon
 */
@Repository
public interface ProgramSubmissionDetailMapper {
    long countByExample(ProgramSubmissionDetailEntityExample example);

    int deleteByExample(ProgramSubmissionDetailEntityExample example);

    int deleteByPrimaryKey(Integer sdId);

    int insert(ProgramSubmissionDetailEntity record);

    int insertSelective(ProgramSubmissionDetailEntity record);

    List<ProgramSubmissionDetailEntity> selectByExample(ProgramSubmissionDetailEntityExample example);

    ProgramSubmissionDetailEntity selectByPrimaryKey(Integer sdId);

    int updateByExampleSelective(@Param("record") ProgramSubmissionDetailEntity record, @Param("example") ProgramSubmissionDetailEntityExample example);

    int updateByExample(@Param("record") ProgramSubmissionDetailEntity record, @Param("example") ProgramSubmissionDetailEntityExample example);

    int updateByPrimaryKeySelective(ProgramSubmissionDetailEntity record);

    int updateByPrimaryKey(ProgramSubmissionDetailEntity record);
}