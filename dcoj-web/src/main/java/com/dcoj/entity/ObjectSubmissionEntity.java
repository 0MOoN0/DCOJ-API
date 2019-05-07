package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 客观题提交实体类
 *
 * @author Leon
 */
@Data
public class ObjectSubmissionEntity {
    /** 客观题提交ID */
    private Integer objectSubmitId;

    /** 用户ID */
    private Integer uid;

    /** 客观题ID */
    private Integer objectProblemId;

    /** 判题结果状态, 0:错误  1：正确 */
    private Byte resultStatus;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    /** 用户提交的回答 */
    private String answer;

}