package com.dcoj.entity;

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
    /** 标签id */
    private Integer programTagId;
    /** 题目id */
    private Integer programProblemId;
     /** 标签是否删除 */
    private Integer deleted;
    /** 记录创建时间 */
    private Timestamp gmtCreate;
    /** 记录最后修改时间 */
    private Timestamp gmtModified;
}
