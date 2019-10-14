package com.dcoj.service;


import com.dcoj.entity.ClassEntity;

import java.util.List;

/**
 * 班级 业务层
 *
 * @author Jack Lin
 */
public interface ClassService {
    /**
     * 返回所有班级信息
     * @return 结果
     */
    List<ClassEntity> listAll();

    /**
     * 获取一个班级的详细信息
     *
     * @param classId 用户id
     * @return 班级信息
     */
    ClassEntity getByPrimaryKey(Integer classId);

    /**
     * 更新班级信息
     *
     * @param classId     班级id
     * @param classEntity 班级信息
     */
    void updateClass(Integer classId, ClassEntity classEntity);

    /**
     * 删除一个班级
     *
     * @param classId 班级id
     */
    void removeByPrimaryKey(Integer classId);

    /**
     * 增加一个班级
     * @param classEntity 班级信息
     */

    void insertClass(ClassEntity classEntity);
}
