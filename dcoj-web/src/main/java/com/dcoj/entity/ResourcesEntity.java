package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 资源表
 *
 * @author WANGQING
 */
@Data
public class ResourcesEntity {
    /**
     * 资源id
     */
    @JSONField(name = "resources_id")
    private Integer resourcesId;
    /**
     * 资源名
     */
    private String name;
    /**
     * 资源类型
     */
    private String type;
    /**
     * url
     */
    private String url;
    /**
     * 资源
     */
    private String permission;
    /**
     * 父级id
     */
    @JSONField(name = "parent_id")
    private Integer parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否有外部连接
     */
    private Integer external;
    /**
     * 是否禁用
     */
    private Integer available;
    /**
     * 图标
     */
    private String icon;
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
