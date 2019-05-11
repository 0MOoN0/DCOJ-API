package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 编程题目提交详情实体类
 *
 * @author Leon
 */
@Data
public class ProgramSubmissionDetailEntity {
    /** 编程题目详情ID */
    private Integer sdId;

    /** 判卷详情 */
    private JSONObject judgeDetail;

    /** 编程提交ID */
    private Integer subId;


    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    /** 源码附件ID */
    private Integer sourceCode;
}