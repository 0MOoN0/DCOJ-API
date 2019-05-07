package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProgramSubmissionDetailEntity {
    private Integer sdId;

    private JSONObject judgeDetail;

    private Integer subId;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

}