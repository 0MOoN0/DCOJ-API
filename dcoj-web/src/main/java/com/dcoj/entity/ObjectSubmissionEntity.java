package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 客观题提交实体类
 *
 * @author Leon
 */
@Data
public class ObjectSubmissionEntity {

    /**
     * 客观题提交ID
     */
    @JSONField(name = "object_submit_id")
    private Integer objectSubmitId;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 客观题ID
     */
    @JSONField(name = "object_problem_id")
    private Integer objectProblemId;

    /**
     * 判题结果状态, 0:错误  1：正确
     */
    @JSONField(name = "result_status")
    private Byte resultStatus;

    /**
     * 用户提交的回答
     */
    private String answer;

    /**
     * 可查询时间
     */
    @JSONField(name = "queryable_time")
    private Timestamp queryableTime;

    /**
     * 分数
     */
    private Byte score;

}