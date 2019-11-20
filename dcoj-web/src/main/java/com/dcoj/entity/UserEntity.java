package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户实体类
 *
 * @author Leon WANGQING
 */
@Data
public class UserEntity {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户头像
     */
    private Integer avatar;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 提交次数
     */
    private Integer submitTimes;
    /**
     * 通过次数
     */
    private Integer ACTimes;
    /**
     * WA错误次数
     */
    private Integer WATimes;
    /**
     * 运行时错误次数
     */
    @JSONField(name = "rte_times")
    private Integer RTETimes;
    /**
     * 超出时间限制错误次数
     */
    @JSONField(name = "tle_times")
    private Integer TLETimes;
    /**
     * 编译错误次数
     */
    @JSONField(name = "ce_times")
    private Integer CETimes;
    /**
     * 完成题目数量
     */
    private Integer finishedProblems;

    /**
     * 格言
     */
    private String motto;
    /**
     * 用户最后更新时间
     */
    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;
    /**
     * 用户创建时间
     */
    @JSONField(name = "gmt_create")
    private Timestamp gmtCreate;
    /**
     * 是否删除用户（用户是否存在）
     */
    private Integer deleted;

    /**
     * 用户角色
     */
    private RoleEntity userRole;
}