package com.dcoj.service.impl;

import com.dcoj.dao.ClassEntityMapper;
import com.dcoj.entity.ClassEntity;
import com.dcoj.service.ClassService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * 班级 业务层实现
 *
 * @author Jack Lin
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassEntityMapper classEntityMapper;

    /**
     * 获取所有班级的详细信息
     *
     * @return 班级信息
     */
    @Override
    public List<ClassEntity> listAll() {
        return classEntityMapper.listAll();
    }



    /**
     * 获取一个班级的详细信息
     *
     * @param classId 用户id
     * @return 班级信息
     */
    @Override
    public ClassEntity getByPrimaryKey(Integer classId) {
        ClassEntity classEntity  = classEntityMapper.selectByPrimaryKey(classId);
        WebUtil.assertNotNull(classEntity, "该班级不存在");
        return classEntity ;
    }

    /**
     * 更新班级信息
     *
     * @param classId     班级id
     * @param classEntity 班级信息
     */
    @Override
    public void updateClass(Integer classId, ClassEntity classEntity) {
        WebUtil.assertNotNull(classEntityMapper.selectByPrimaryKey(classId), "该班级不存在，无法更新");
        classEntity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
        boolean flag = classEntityMapper.updateByPrimaryKey(classEntity)== 1;
        WebUtil.assertIsSuccess(flag, "用户更新失败");
    }

    /**
     * 删除一个班级
     *
     * @param classId 班级id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByPrimaryKey(Integer classId) {
        WebUtil.assertNotNull(classEntityMapper.selectByPrimaryKey(classId), "该班级不存在，删除失败");
        boolean flag = classEntityMapper.deleteByPrimaryKey(classId) == 1;
        WebUtil.assertIsSuccess(flag, "删除用户失败");
    }

    /**
     * 增加一个班级
     * @param classEntity 班级信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertClass(ClassEntity classEntity) {
        WebUtil.assertNull(classEntityMapper.selectByPrimaryKey(classEntity.getClassId()), "该班级已存在，新增失败");
        classEntity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
        classEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        classEntity.setDeleted(0);
        classEntity.setIsOrder(0);
        boolean flag =  classEntityMapper.insert(classEntity) == 1;
        WebUtil.assertIsSuccess(flag, "添加用户失败");
    }
}
