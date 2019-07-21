package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 编程题目提交详情实体类
 *
 * @author Leon
 */
@Data
public class ProgramSubmissionDetailEntity {
    /**
     * 编程题目详情ID
     */
    private Integer sdId;

    /**
     * 判卷详情
     */
    @JSONField(name = "judge_detail")
    private JSONObject judgeDetail;

    /**
     * 编程提交ID
     */
    @JSONField(name = "sub_id")
    private Integer subId;

    /**
     * 源码附件ID
     */
    @JSONField(name = "source_code")
    private Integer sourceCode;
}