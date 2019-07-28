package com.dcoj.service.impl;

import com.dcoj.controller.format.index.IndexLoginFormat;
import com.dcoj.dao.UserMapper;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.UserService;
import com.dcoj.util.JWTUtil;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户 业务层实现
 *
 * @author WANGQING
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 统计学生数量
     *
     * @return 学生数量
     */
    @Override
    public int countUsers() {
        return userMapper.countUsers();
    }

    /**
     * 获取一个用户的详细信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public UserEntity getByPrimaryKey(Integer userId) {
        UserEntity userEntity = userMapper.getByPrimaryKey(userId);
        WebUtil.assertNotNull(userEntity, "不存在此用户");
        return userEntity;
    }

    /**
     * 获取一个用户的详细信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public UserEntity getByUsername(String username) {
        UserEntity userEntity = userMapper.getByUsername(username);
        WebUtil.assertNotNull(userEntity, "不存在此用户");
        return userEntity;
    }

    /**
     * 显示用户列表
     *
     * @return 结果
     */
    @Override
    public List<UserEntity> listAll() {
        return userMapper.listAll();
    }

    /**
     * 更新用户信息
     *
     * @param userId     用户id
     * @param userEntity 用户信息
     */
    @Override
    public void updateUser(Integer userId, UserEntity userEntity) {
        WebUtil.assertNotNull(userMapper.getByPrimaryKey(userId), "该用户不存在，无法更新");
        userEntity.setUserId(userId);
        boolean flag = userMapper.updateUser(userEntity) == 1;
        WebUtil.assertIsSuccess(flag, "用户更新失败");
    }

    /**
     * 删除一个用户
     *
     * @param userId 用户id
     * @return 返回1则删除成功，返回0则删除失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Integer userId) {
        WebUtil.assertNotNull(userMapper.getByPrimaryKey(userId), "用户不存在，删除失败");
        //TODO:WANGQING 2019.7.23
        // 判断是否跟角色关联


        // 删除role
        boolean flag = userMapper.removeByPrimaryKey(userId) == 1;
        WebUtil.assertIsSuccess(flag, "删除用户失败");
    }

    /**
     * 更新用户密码
     *
     * @param userId      用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @Override
    public void updateUserPassword(Integer userId, String oldPassword, String newPassword) {
        UserEntity userEntity = userMapper.getByPrimaryKey(userId);
        WebUtil.assertNotNull(userEntity, "不存在此用户");
        // 重置用户密码
        if (newPassword != null && oldPassword != null && oldPassword.equals(userEntity.getPassword())) {
            userEntity.setPassword(newPassword);
        } else {
            // 密码默认000000
            userEntity.setPassword("000000");
        }
        boolean flag = userMapper.updateUser(userEntity) == 1;
        WebUtil.assertIsSuccess(flag, "密码更新或者重置失败");
        // TODO: WANGQING 2019.7.23 该方法未完善未优化
    }

    /**
     * 登录
     *
     * @param format 登录验证
     * @return 结果
     */
    @Override
    public UserEntity login(IndexLoginFormat format) {
        UserEntity userEntity = userMapper.getByUsername(format.getUsername());
        // 验证密码
        if (userEntity.getPassword().equals(format.getPassword())) {
            return userEntity;
        }
        return null;
    }

    /**
     * 根据token获取用户信息
     *
     * @param token 密钥
     * @return 结果
     */
    @Override
    public UserEntity getByToken(String token) {
        // 获取当前用户
        int userId = JWTUtil.getUid(token);
        UserEntity user = userMapper.getByPrimaryKey(userId);
        if (Optional.ofNullable(user).isPresent())
            return user;
        return null;
    }

    // TODO : 2019.7.6 WANGQING 注释
//    /**
//     * 进行用户注册
//     *
//     * @param studentId 账号学号
//     * @param email     账号邮箱
//     * @param nickname  账号昵称
//     * @param password  账号密码
//     */
//    @Override
//    public void register(String studentId, String email, String nickname, String password) {
//        if (mongoTemplate.exists(new Query(Criteria.where("email").is(email)), UserEntity.class)) {
//            throw new WebErrorException("邮箱已经注册");
//        } else if (mongoTemplate.exists(new Query(Criteria.where("nickName").is(nickname)), UserEntity.class)) {
//            throw new WebErrorException("昵称已经存在");
//        } else if (mongoTemplate.exists(new Query(Criteria.where("nickName").is(nickname)), UserEntity.class)) {
//            throw new WebErrorException("学号已经注册");
//        }
//        UserEntity newUserEntity = new UserEntity();
//        newUserEntity.setEmail(email);
//        newUserEntity.setNickname(nickname);
//        newUserEntity.setPassword(new Md5Hash(password).toString());
//        // 设施初始化权限
//        String roleId = mongoTemplate.findOne(new Query(Criteria.where("roleName").is("STUDENT")), RoleEntity.class).getRoleId();
//        Set<String> roles = new HashSet<>();
//        roles.add(roleId);
//        newUserEntity.setRoles(roles);
//        newUserEntity.setRegisterTime(System.currentTimeMillis());
//        newUserEntity.setVerified(1);
//        try {
//            mongoTemplate.save(newUserEntity);
//        } catch (Exception e) {
//            throw new WebErrorException("用户注册失败");
//        }
//    }
//
//    @Override
//    public int countUsers() {
//        return 0;
//    }
//
//    @Override
//    public UserEntity login(IndexLoginFormat format) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUid(1);
//        userEntity.setPassword(format.getPassword());
///*      TODO 20190426 Leon完成Login方法
//        if(Optional.ofNullable(format.getEmail()).isPresent() && !format.getEmail().trim().equals("")){
//            userEntity = mongoTemplate.findOne(new Query(Criteria.where("email").is(format.getEmail()).
//                            andOperator(Criteria.where("password").is(format.getPassword()))),
//                    UserEntity.class);
//        }else
//        if(Optional.ofNullable(format.getStudentId()).isPresent() && !format.getStudentId().trim().equals("")){
//            userEntity = mongoTemplate.findOne(new Query(Criteria.where("sutdentId").is(format.getStudentId()).
//                    andOperator(Criteria.where("password").is(format.getStudentId()))),
//                    UserEntity.class);
//        }
//        if (!Optional.ofNullable(userEntity).isPresent()){
//            throw new WebErrorException("用户名或密码错误");
//        }*/
//        return userEntity;
//    }
//
//    @Override
//    public UserEntity getUserByUid(int uid) {
//        return null;
//    }
//
//    @Override
//    public UserEntity getUserByEmail(String email) {
//        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), UserEntity.class);
//    }
//
//    @Override
//    public UserEntity getUserByNickname(String nickname) {
//        return mongoTemplate.findOne(new Query(Criteria.where("nickName").is(nickname)), UserEntity.class);
//    }
//
//    @Override
//    public UserEntity getUserByStudentId(String studentId) {
//        return mongoTemplate.findOne(new Query(Criteria.where("studentId").is(studentId)), UserEntity.class);
//    }
//
//    @Override
//    public List<UserEntity> listAll() {
//        return null;
//    }
//
//    @Override
//    public void updateUserProfile(Integer uid, String nickname, String motto, Integer gender) {
//
//    }
//
//    @Override
//    public void updateUser(int uid, UserEntity userEntity) {
//
//    }
//
//    @Override
//    public void uploadUserAvatar(Integer uid, MultipartFile file) {
//
//    }
//
//    @Override
//    public void updateUserPassword(int uid, String oldPassword, String newPassword) {
//
//    }
//
//    @Override
//    public void updateUserEmail(int uid, String email) {
//
//    }
//
//    @Override
//    public void verifyUserEmail(int uid, String code) {
//
//    }
//
//    @Override
//    public void resetUserPassword(String email, String password, String emailToken) {
//        Cache<String, String> cache = null;
//        // 进行缓存校验
//        if (Optional.ofNullable(GlobalCacheManager.getEmailVerifyCache()).isPresent()) {
//            cache = GlobalCacheManager.getEmailVerifyCache();
//            if (cache.get(emailToken).split(":")[1].equals(email)) {
//                Update update = new Update();
//                update.set("password", new Md5Hash(password).toString());
//                Query query = new Query();
//                query.addCriteria(Criteria.where("email").is(email));
//                UserEntity modifiedUser = mongoTemplate.findAndModify(query, update, UserEntity.class);
//                // 如果无法通过email找到用户，说明用户不存在
//                if (!Optional.ofNullable(modifiedUser).isPresent()) {
//                    throw new WebErrorException("无法重置密码，用户不存在");
//                }
//            } else {
//                throw new WebErrorException("无法重置密码，邮箱与缓存不匹配");
//            }
//        } else {
//            throw new WebErrorException("无法重置密码，缓存不存在");
//        }
//    }
//
//    /**
//     * 通过邮箱检查用户是否存在
//     *
//     * @param email 用户邮箱
//     * @return Boolean，检查结果.true：用户存在;false：用户不存在
//     */
//    @Override
//    public boolean checkUserByEmail(String email) {
//        return mongoTemplate.exists(new Query(Criteria.where("email").is(email)), UserEntity.class);
//    }
}
