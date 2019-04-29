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
    private Integer objectSubmitId;

    private Integer uid;

    private Integer objectProblemId;

    private Byte resultStatus;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private String answer;

}