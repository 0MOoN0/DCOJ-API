package com.dcoj.dao;

import com.dcoj.entity.ObjectSubmissionEntity;
import com.dcoj.entity.example.ObjectSubmissionEntityExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 使用Mybatis-Generator生成
 *
 * @author Leon
 */
@Repository
public interface ObjectSubmissionMapper {
    long countByExample(ObjectSubmissionEntityExample example);

    int deleteByExample(ObjectSubmissionEntityExample example);

    int deleteByPrimaryKey(Integer objectSubmitId);

    int insert(ObjectSubmissionEntity record);

    int insertSelective(ObjectSubmissionEntity record);

    List<ObjectSubmissionEntity> selectByExample(ObjectSubmissionEntityExample example);

    ObjectSubmissionEntity selectByPrimaryKey(Integer objectSubmitId);

    int updateByExampleSelective(@Param("record") ObjectSubmissionEntity record, @Param("example") ObjectSubmissionEntityExample example);

    int updateByExample(@Param("record") ObjectSubmissionEntity record, @Param("example") ObjectSubmissionEntityExample example);

    int updateByPrimaryKeySelective(ObjectSubmissionEntity record);

    int updateByPrimaryKey(ObjectSubmissionEntity record);

    int deleteByObjectProblemId(@Param("objectProblemId") Integer objectProblemId);
}