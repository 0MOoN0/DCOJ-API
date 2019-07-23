package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 角色实体类
 *
 * @author WANGQING
 */
@Data
public class RoleEntity {
    /**
     * 角色id
     */
    @JSONField(name = "role_id")
    private Integer roleId;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否可用
     */
    private Integer available;
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
