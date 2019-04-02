package com.dcoj.entity;

import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 提交类，用于记录用户提交判卷的具体情况
 * @author Leon
 */
@Data
public class SubmissionEntity {

    /** SubmissionID */
    private Integer subId;

    /** 用户ID */
    private Integer uid;

    /** 题目ID */
    private Integer pid;

    /** 提交的语言类型 */
    private LanguageEnum lang;

    /** 判卷所用的平均时间 */
    private BigDecimal usingTime;

    /** 判卷使用的平均内存 */
    private Integer memory;

    /** 判卷结果 */
    private ResultEnum status;

    /** 提交判卷的时间 */
    private Timestamp submitTime;

    /** 创建时间 */
    private Timestamp gmtCreate;

    /** 修改时间 */
    private Timestamp gmtModify;

    /** 用户提交的源码 */
    private Integer sourceCode;
}