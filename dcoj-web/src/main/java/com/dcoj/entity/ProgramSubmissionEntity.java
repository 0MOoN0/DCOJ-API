package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 提交类，用于记录用户提交判卷的具体情况
 * @author Leon
 */
@Data
public class ProgramSubmissionEntity {

    /** SubmissionID */
    @JSONField(name = "sub_id")
    private Integer subId;

    /** 用户ID */
    private Integer uid;

    /** 题目ID */
    private Integer pid;

    /** 提交的语言类型 */
    private LanguageEnum lang;

    /** 判卷所用的平均时间 */
    @JSONField(name = "using_time")
    private BigDecimal usingTime;

    /** 判卷使用的平均内存 */
    private Integer memory;

    /** 判卷结果 */
    private ResultEnum status;

    /** 提交判卷的时间 */
    @JSONField(name = "submit_time")
    private Timestamp submitTime;

    /** 创建时间 */
    @JSONField(name = "gmt_create")
    private Timestamp gmtCreate;

    /** 修改时间 */
    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;

    /** 试卷ID */
    private Integer eid;

    /** 用户组ID */
    private Integer gid;

    /** 该Submission可以查询的时间 */
    @JSONField(name = "queryable_time")
    private Timestamp queryableTime;

    /** 该次提交的分数 */
    private Byte score;
}