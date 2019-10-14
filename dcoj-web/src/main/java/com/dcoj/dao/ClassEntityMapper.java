package com.dcoj.dao;

import com.dcoj.entity.ClassEntity;

import java.util.List;

public interface ClassEntityMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(ClassEntity record);

    int insertSelective(ClassEntity record);

    ClassEntity selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(ClassEntity record);

    int updateByPrimaryKey(ClassEntity record);

    List<ClassEntity> listAll();
}