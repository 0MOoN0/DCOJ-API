package com.dcoj.service.impl;

import com.dcoj.entity.UserEntity;
import com.dcoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void register(String email, String nickname, String password) {

    }

    @Override
    public int countUsers() {
        return 0;
    }

    @Override
    public UserEntity login(String email, String password) {
        return null;
    }

    @Override
    public UserEntity getUserByUid(String uid) {
        return mongoTemplate.findById(uid,UserEntity.class);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserEntity> listAll() {
        return null;
    }

    @Override
    public void updateUserProfile(Integer uid, String nickname, String motto, Integer gender) {

    }

    @Override
    public void updateUser(int uid, UserEntity userEntity) {

    }

    @Override
    public void uploadUserAvatar(Integer uid, MultipartFile file) {

    }

    @Override
    public void updateUserPassword(int uid, String oldPassword, String newPassword) {

    }

    @Override
    public void updateUserEmail(int uid, String email) {

    }

    @Override
    public void verifyUserEmail(int uid, String code) {

    }

    @Override
    public void resetUserPassword(String email, String password, String code) {

    }
}
