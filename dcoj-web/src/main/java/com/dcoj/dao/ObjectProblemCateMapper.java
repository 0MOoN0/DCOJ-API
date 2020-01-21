package com.dcoj.dao;

import com.dcoj.entity.ObjectProblemCateEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zxw
 * @Desriiption: 客观题类别Mapper
 */
@Repository
public interface ObjectProblemCateMapper {

    int insertSelective(ObjectProblemCateEntity objectProblemCateEntity);

    int deleteByObjectProblemId(@Param("objectProblemId") Integer objectProblemId);
}
