package com.dcoj.dao;

import com.dcoj.entity.ProgramSubmissionEntity;
import com.dcoj.entity.example.ProgramSubmissionEntityExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SubmissionMapper
 *
 * @author Leon
 */
@Repository
public interface ProgramSubmissionMapper {
    long countByExample(ProgramSubmissionEntityExample example);

    int deleteByExample(ProgramSubmissionEntityExample example);

    int deleteByPrimaryKey(Integer subId);

    int insert(ProgramSubmissionEntity record);

    int insertSelective(ProgramSubmissionEntity record);

    List<ProgramSubmissionEntity> selectByExample(ProgramSubmissionEntityExample example);

    List<ProgramSubmissionEntity> listProblemLeaderboard(ProgramSubmissionEntityExample example);

    ProgramSubmissionEntity selectByPrimaryKey(Integer subId);

    int updateByExampleSelective(@Param("record") ProgramSubmissionEntity record, @Param("example") ProgramSubmissionEntityExample example);

    int updateByExample(@Param("record") ProgramSubmissionEntity record, @Param("example") ProgramSubmissionEntityExample example);

    int updateByPrimaryKeySelective(ProgramSubmissionEntity record);

    int updateByPrimaryKey(ProgramSubmissionEntity record);
}