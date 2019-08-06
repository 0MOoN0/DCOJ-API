package com.dcoj.dao;

import com.dcoj.entity.ObjectProblemUserEntity;
import com.dcoj.entity.example.ObjectProblemUserEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectProblemUserMapper {
    long countByExample(ObjectProblemUserEntityExample example);

    int deleteByExample(ObjectProblemUserEntityExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(ObjectProblemUserEntity record);

    int insertSelective(ObjectProblemUserEntity record);

    List<ObjectProblemUserEntity> selectByExample(ObjectProblemUserEntityExample example);

    ObjectProblemUserEntity selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") ObjectProblemUserEntity record, @Param("example") ObjectProblemUserEntityExample example);

    int updateByExample(@Param("record") ObjectProblemUserEntity record, @Param("example") ObjectProblemUserEntityExample example);

    int updateByPrimaryKeySelective(ObjectProblemUserEntity record);

    int updateByPrimaryKey(ObjectProblemUserEntity record);
}