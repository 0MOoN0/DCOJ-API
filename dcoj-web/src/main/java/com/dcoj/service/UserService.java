package com.dcoj.service;

import com.dcoj.controller.format.index.IndexLoginFormat;
import com.dcoj.entity.UserEntity;

import java.util.List;

/**
 * 用户 业务层
 *
 * @author WANGQING
 */
public interface UserService {
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
     * 获取一个用户的详细信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserEntity getByUsername(String username);

    /**
     * 通过用户名模糊查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    List<UserEntity> listAll(String username);

    /**
     * 更新用户信息
     *
     * @param userId     用户id
     * @param userEntity 用户信息
     */
    void updateUser(Integer userId, UserEntity userEntity);

    /**
     * 删除一个用户
     *
     * @param userId 用户id
     */
    void removeByPrimaryKey(Integer userId);

    /**
     * 更新用户密码
     *
     * @param userId      用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updateUserPassword(Integer userId, String oldPassword, String newPassword);

    /**
     * 登录
     *
     * @param format 登录验证
     * @return 结果
     */
    UserEntity login(IndexLoginFormat format);

    /**
     * 根据token获取用户信息
     *
     * @param token 密钥
     * @return 结果
     */
    UserEntity getByToken(String token);

    // TODO：2019.7.6 WANGQING 注释
//    void register(String studentId, String email, String nickname, String password);
//
//    int countUsers();
//
//    UserEntity login(IndexLoginFormat format);
//
//    UserEntity getUserByUid(int uid);
//
//    UserEntity getUserByEmail(String email);
//
//    UserEntity getUserByNickname(String nickname);
//
//    UserEntity getUserByStudentId(String studentId);
//
//    List<UserEntity> listAll();
//
//    void updateUserProfile(Integer uid, String nickname, String motto, Integer gender);
//
//    void updateUser(int uid, UserEntity userEntity);
//
//    void uploadUserAvatar(Integer uid, MultipartFile file);
//
//    void updateUserPassword(int uid, String oldPassword, String newPassword);
//
//    void updateUserEmail(int uid, String email);
//
//    void verifyUserEmail(int uid, String code);
//
//    void resetUserPassword(String email, String password, String emailToken);
//
//    boolean checkUserByEmail(String email);

}
