package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(name = "object_problem_id")
    private Integer objectProblemId;
    /** 客观题标签id */
    @JSONField(name = "object_tag_id")
    private Integer objectTagId;
    /** 题目最后更新时间 */
    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;
    /** 题目创建时间 */
    @JSONField(name = "gmt_create")
    private Timestamp gmtCreate;
    /** 是否删除题目（题目是否存在） */
    private Integer deleted;

}