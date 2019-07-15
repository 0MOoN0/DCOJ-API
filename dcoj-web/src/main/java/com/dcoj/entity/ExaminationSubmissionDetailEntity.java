package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExaminationSubmissionDetailEntity {
    private Integer examSubmissionDetailId;

    private Integer examSubmissionId;

    private JSONObject answerSheet;

    private JSONObject resultSheet;

    private Timestamp queryableTime;

}