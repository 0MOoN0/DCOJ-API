package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 题目标签关联实体类
 *
 * @author WANGQING
 */
@Data
public class ProgramProblemTagEntity {
    /**
     * 标签id
     */
    @JSONField(name = "program_tag_id")
    private Integer programTagId;
    /**
     * 题目id
     */
    @JSONField(name = "program_problem_id")
    private Integer programProblemId;
    /**
     * 标签是否删除
     */
    private Integer deleted;
    /**
     * 记录创建时间
     */
    @JSONField(name = "gmt_create")
    private Timestamp gmtCreate;
    /**
     * 记录最后修改时间
     */
    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;
}
