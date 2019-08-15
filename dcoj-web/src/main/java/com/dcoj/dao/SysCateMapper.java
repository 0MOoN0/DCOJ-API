package com.dcoj.dao;

import com.dcoj.entity.SysCate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysCateMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(SysCate record);

    int insertSelective(SysCate record);

    SysCate selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(SysCate record);

    int updateByPrimaryKey(SysCate record);

    List<SysCate> getAllCate();

    //获取同一层级子类别
    List<SysCate> getChildCateListByLevel(@Param("level") String level);

    //批量更新层级
    void batchUpdateLevel(@Param("sysCateList") List<SysCate> sysCateList);

    //查重名
    int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);

    //查看上级数目
    int countByParentId(@Param("cateId") int cateId);
}