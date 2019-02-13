package com.dcoj.service.impl;

import com.dcoj.cache.GlobalCacheManager;
import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.controller.format.index.IndexLoginFormat;
import com.dcoj.entity.RoleEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 进行用户注册
     * @param studentId     账号学号
     * @param email         账号邮箱
     * @param nickname      账号昵称
     * @param password      账号密码
     */
    @Override
    public void register(String studentId, String email, String nickname, String password) {
        if(mongoTemplate.exists(new Query(Criteria.where("email").is(email)),UserEntity.class)){
            throw new WebErrorException("邮箱已经注册");
        }else if(mongoTemplate.exists(new Query(Criteria.where("nickName").is(nickname)),UserEntity.class)){
            throw new WebErrorException("昵称已经存在");
        }else if(mongoTemplate.exists(new Query(Criteria.where("nickName").is(nickname)),UserEntity.class)){
            throw new WebErrorException("学号已经注册");
        }
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setEmail(email);
        newUserEntity.setNickname(nickname);
        newUserEntity.setPassword(new Md5Hash(password).toString());
        // 设施初始化权限
        String roleId = mongoTemplate.findOne(new Query(Criteria.where("roleName").is("STUDENT")), RoleEntity.class).getRoleId();
        Set<String> roles = new HashSet<>();
        roles.add(roleId);
        newUserEntity.setRoles(roles);
        newUserEntity.setRegisterTime(System.currentTimeMillis());
        newUserEntity.setVerified(1);
        try{
            mongoTemplate.save(newUserEntity);
        } catch (Exception e){
            throw new WebErrorException("用户注册失败");
        }
    }

    @Override
    public int countUsers() {
        return 0;
    }

    @Override
    public UserEntity login(IndexLoginFormat format) {
        UserEntity userEntity = null;
        if(format.getEmail()!=null && !format.getEmail().trim().equals("")){
            userEntity = mongoTemplate.findOne(new Query(Criteria.where("email").is(format.getEmail()).
                            andOperator(Criteria.where("password").is(format.getPassword()))),
                    UserEntity.class);
        }else if(format.getStudentId()!=null && !format.getStudentId().trim().equals("")){
            userEntity = mongoTemplate.findOne(new Query(Criteria.where("sutdentId").is(format.getStudentId()).
                    andOperator(Criteria.where("password").is(format.getStudentId()))),
                    UserEntity.class);
        }
        if (userEntity == null){
            throw new WebErrorException("用户名或密码错误");
        }
        return userEntity;
    }

    @Override
    public UserEntity getUserByUid(String uid) {
        return mongoTemplate.findById(uid, UserEntity.class);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)),UserEntity.class);
    }

    @Override
    public UserEntity getUserByNickname(String nickname) {
        return mongoTemplate.findOne(new Query(Criteria.where("nickName").is(nickname)),UserEntity.class);
    }

    @Override
    public UserEntity getUserByStudentId(String studentId) {
        return mongoTemplate.findOne(new Query(Criteria.where("studentId").is(studentId)),UserEntity.class);
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
    public void resetUserPassword(String email, String password, String emailToken) {
        Cache<String, String> cache =null;
        // 进行缓存校验
        if (Optional.ofNullable(GlobalCacheManager.getEmailVerifyCache()).isPresent()){
            cache = GlobalCacheManager.getEmailVerifyCache();
            if (cache.get(emailToken).split(":")[1].equals(email)){
                Update update = new Update();
                update.set("password",new Md5Hash(password).toString());
                Query query = new Query();
                query.addCriteria(Criteria.where("email").is(email));
                UserEntity modifiedUser = mongoTemplate.findAndModify(query, update, UserEntity.class);
                // 如果无法通过email找到用户，说明用户不存在
                if(!Optional.ofNullable(modifiedUser).isPresent()){
                    throw new WebErrorException("无法重置密码，用户不存在");
                }
            }else {
                throw new WebErrorException("无法重置密码，邮箱与缓存不匹配");
            }
        }else {
            throw new WebErrorException("无法重置密码，缓存不存在");
        }
    }

    /**
     *  通过邮箱检查用户是否存在
     * @param email     用户邮箱
     * @return      Boolean，检查结果.true：用户存在;false：用户不存在
     */
    @Override
    public boolean checkUserByEmail(String email) {
        return mongoTemplate.exists(new Query(Criteria.where("email").is(email)),UserEntity.class);
    }
}
