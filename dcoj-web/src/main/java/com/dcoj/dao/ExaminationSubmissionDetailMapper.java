package com.dcoj.dao;

import com.dcoj.entity.ExaminationSubmissionDetailEntity;
import com.dcoj.entity.example.ExaminationSubmissionDetailEntityExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Leon
 */
@Repository
public interface ExaminationSubmissionDetailMapper {
    long countByExample(ExaminationSubmissionDetailEntityExample example);

    int deleteByExample(ExaminationSubmissionDetailEntityExample example);

    int deleteByPrimaryKey(Integer examSubmissionDetailId);

    int insert(ExaminationSubmissionDetailEntity record);

    int insertSelective(ExaminationSubmissionDetailEntity record);

    List<ExaminationSubmissionDetailEntity> selectByExample(ExaminationSubmissionDetailEntityExample example);

    ExaminationSubmissionDetailEntity selectByPrimaryKey(Integer examSubmissionDetailId);

    int updateByExampleSelective(@Param("record") ExaminationSubmissionDetailEntity record, @Param("example") ExaminationSubmissionDetailEntityExample example);

    int updateByExample(@Param("record") ExaminationSubmissionDetailEntity record, @Param("example") ExaminationSubmissionDetailEntityExample example);

    int updateByPrimaryKeySelective(ExaminationSubmissionDetailEntity record);

    int updateByPrimaryKey(ExaminationSubmissionDetailEntity record);
}