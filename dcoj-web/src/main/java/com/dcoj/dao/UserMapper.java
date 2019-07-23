package com.dcoj.dao;

import com.dcoj.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 持久层
 *
 * @author WANGQING
 */
public interface UserMapper {

    /**
     * 新增一个用户
     *
     * @param userEntity 用户信息
     * @return 返回1则插入成功，返回0则插入失败
     */
    // TODO: 2019.7.18 WANGQING 未在xml中添加此方法
    //int save(UserEntity userEntity);

    /**
     * 统计学生数量
     *
     * @return 学生数量
     */
    int countUsers();

    /**
     * 获取一个用户的详细信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    UserEntity getByPrimaryKey(Integer userId);

    /**
     * 显示用户列表
     *
     * @return 结果
     */
    List<UserEntity> listAll();

    /**
     * 更新用户信息
     *
     * @param userEntity 用户信息
     * @return 返回1则更新成功，返回0则更新失败
     */
    int updateUser(UserEntity userEntity);

    /**
     * 删除一个用户
     *
     * @param userId 用户id
     * @return 返回1则删除成功，返回0则删除失败
     */
    int removeByPrimaryKey(Integer userId);
}
