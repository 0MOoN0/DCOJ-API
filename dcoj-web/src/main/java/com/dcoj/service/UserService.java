package com.dcoj.service;

import com.dcoj.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Smith
 **/
public interface UserService {

    void register(String email, String nickname, String password);

    int countUsers();

    UserEntity login(String email, String password);

    UserEntity getUserByUid(String uid);

    UserEntity getUserByEmail(String email);

    List<UserEntity> listAll();

    void updateUserProfile(Integer uid, String nickname, String motto, Integer gender);

    void updateUser(int uid, UserEntity userEntity);

    void uploadUserAvatar(Integer uid, MultipartFile file);

    void updateUserPassword(int uid, String oldPassword, String newPassword);

    void updateUserEmail(int uid, String email);

    void verifyUserEmail(int uid, String code);

    void resetUserPassword(String email, String password, String code);
}
