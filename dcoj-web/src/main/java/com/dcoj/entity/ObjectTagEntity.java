package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 客观题标签实体类
 *
 * @author WANGQING
 */
@Data
public class ObjectTagEntity {
    /** 客观题标签id */
    private Integer objectTagId;
    /** 标签名 */
    private String tagName;
    /** 标签使用次数 */
    private Integer usedTimes;
    /** 题目最后更新时间 */
    private Timestamp gmtModified;
    /** 题目创建时间 */
    private Timestamp gmtCreate;
    /** 是否删除题目（题目是否存在） */
    private Integer deleted;
}