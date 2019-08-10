package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 考试提交详情类
 *
 * @author Leon
 */
@Data
public class ExaminationSubmissionDetailEntity {

    @JSONField(name = "exam_submission_detail_id")
    private Integer examSubmissionDetailId;

    @JSONField(name = "exam_submission_id")
    private Integer examSubmissionId;

    @JSONField(name = "answer_sheet")
    private JSONObject answerSheet;

    @JSONField(name = "result_sheet")
    private JSONObject resultSheet;

    @JSONField(name = "queryable_time")
    private Timestamp queryableTime;

}