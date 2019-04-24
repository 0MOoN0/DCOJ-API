package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 客观题标签关联实体类
 *
 * @author WANGQING
 */
@Data
public class ObjectProblemTagEntity {
    /** 客观题id */
    private Integer objectProblemId;
    /** 客观题标签id */
    private Integer objectTagId;
    /** 题目最后更新时间 */
    private Timestamp gmtModified;
    /** 题目创建时间 */
    private Timestamp gmtCreate;
    /** 是否删除题目（题目是否存在） */
    private Integer deleted;

}