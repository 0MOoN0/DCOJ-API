package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 客观题标签实体类
 *
 * @author WANGQING
 */
@Data
public class ObjectTagEntity {
    /**
     * 客观题标签id
     */
    @JSONField(name = "object_tag_id")
    private Integer objectTagId;
    /**
     * 标签名
     */
    @JSONField(name = "tag_name")
    private String tagName;
    /**
     * 标签使用次数
     */
    @JSONField(name = "used_times")
    private Integer usedTimes;
    /**
     * 题目最后更新时间
     */
    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;
    /**
     * 题目创建时间
     */
    @JSONField(name = "gmt_create")
    private Timestamp gmtCreate;
    /**
     * 是否删除题目（题目是否存在）
     */
    private Integer deleted;
}