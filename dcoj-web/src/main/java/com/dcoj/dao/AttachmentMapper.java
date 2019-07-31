package com.dcoj.dao;

import com.dcoj.entity.AttachmentEntity;
import com.dcoj.entity.example.AttachmentEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Leon
 */
@Repository
public interface AttachmentMapper {
    long countByExample(AttachmentEntityExample example);

    int deleteByExample(AttachmentEntityExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(AttachmentEntity record);

    int insertSelective(AttachmentEntity record);

    List<AttachmentEntity> selectByExample(AttachmentEntityExample example);

    AttachmentEntity selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") AttachmentEntity record, @Param("example") AttachmentEntityExample example);

    int updateByExample(@Param("record") AttachmentEntity record, @Param("example") AttachmentEntityExample example);

    int updateByPrimaryKeySelective(AttachmentEntity record);

    int updateByPrimaryKey(AttachmentEntity record);

}