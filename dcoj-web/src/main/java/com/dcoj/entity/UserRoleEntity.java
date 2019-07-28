package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户角色关联表
 *
 * @author WANGQING
 */
@Data
public class UserRoleEntity {
    /**
     * 用户id
     */
    @JSONField(name = "user_id")
    private Integer userId;
    /**
     * 角色id
     */
    @JSONField(name = "role_id")
    private Integer roleId;
    /**
     * 最后更新时间
     */
    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;
    /**
     * 创建时间
     */
    @JSONField(name = "gmt_create")
    private Timestamp gmtCreate;
    /**
     * 是否存在
     */
    private Integer deleted;
}
