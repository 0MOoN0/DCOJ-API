package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

/**
 * 用户实体类
 *
 * @author Leon WANGQING
 */
@Data
public class UserEntity1 {
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户性别
     */
    private Integer gender;
    /**
     * 用户头像
     */
    private Integer avatar;
    /**
     * 用户权限
     */
    private Set<String> roles;
    /**
     * 用户座右铭
     */
    private String motto;
    /**
     * 用户提交次数
     */
    private Integer submitTimes;
    /**
     * 用户AC次数
     */
    private Integer ACTimes;
    /**
     * 用户WA次数
     */
    private Integer WATimes;
    /**
     * 用户RTE次数
     */
    private Integer RTETimes;
    /**
     * 用户TLE次数
     */
    private Integer TLETimes;
    /**
     * 用户CE次数
     */
    private Integer CETimes;
    /**
     * 用户完成题目数量（不分类型）
     */
    private Integer finishedProblems;
    /**
     * 用户邮箱验证码
     */
    private Integer verified;
    /**
     * 用户注册时间
     */
    private Timestamp createTime;
    /**
     * 用户最后修改时间
     */
    private Timestamp modifiedTime;
    /**
     * 用户是否注销
     */
    private Integer deleted;

}
