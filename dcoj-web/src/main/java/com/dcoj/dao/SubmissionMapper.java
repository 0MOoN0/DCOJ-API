package com.dcoj.dao;

import com.dcoj.entity.SubmissionEntity;
import com.dcoj.entity.SubmissionEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SubmissionMapper
 * @author Leon
 */
@Repository
public interface SubmissionMapper {
    long countByExample(SubmissionEntityExample example);

    int deleteByExample(SubmissionEntityExample example);

    int deleteByPrimaryKey(Integer subId);

    int insert(SubmissionEntity record);

    int insertSelective(SubmissionEntity record);

    List<SubmissionEntity> selectByExample(SubmissionEntityExample example);

    List<SubmissionEntity> listProblemLeaderboard(SubmissionEntityExample example);

    SubmissionEntity selectByPrimaryKey(Integer subId);

    int updateByExampleSelective(@Param("record") SubmissionEntity record, @Param("example") SubmissionEntityExample example);

    int updateByExample(@Param("record") SubmissionEntity record, @Param("example") SubmissionEntityExample example);

    int updateByPrimaryKeySelective(SubmissionEntity record);

    int updateByPrimaryKey(SubmissionEntity record);
}