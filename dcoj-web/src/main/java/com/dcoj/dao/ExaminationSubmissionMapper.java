package com.dcoj.dao;

import com.dcoj.entity.ExaminationSubmissionEntity;
import com.dcoj.entity.example.ExaminationSubmissionEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Leon
 */
@Repository
public interface ExaminationSubmissionMapper {
    long countByExample(ExaminationSubmissionEntityExample example);

    int deleteByExample(ExaminationSubmissionEntityExample example);

    int deleteByPrimaryKey(Integer examSubmissionId);

    int insert(ExaminationSubmissionEntity record);

    int insertSelective(ExaminationSubmissionEntity record);

    List<ExaminationSubmissionEntity> selectByExample(ExaminationSubmissionEntityExample example);

    ExaminationSubmissionEntity selectByPrimaryKey(Integer examSubmissionId);

    int updateByExampleSelective(@Param("record") ExaminationSubmissionEntity record, @Param("example") ExaminationSubmissionEntityExample example);

    int updateByExample(@Param("record") ExaminationSubmissionEntity record, @Param("example") ExaminationSubmissionEntityExample example);

    int updateByPrimaryKeySelective(ExaminationSubmissionEntity record);

    int updateByPrimaryKey(ExaminationSubmissionEntity record);
}