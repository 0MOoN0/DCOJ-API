package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import java.sql.Timestamp;

public class SubmissionDetailEntity {
    private Integer sdId;

    private JSONObject judgeDetail;

    private Integer subId;

    private Timestamp gmtCreate;

    private Timestamp gmtModify;

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public JSONObject getJudgeDetail() {
        return judgeDetail;
    }

    public void setJudgeDetail(JSONObject judgeDetail) {
        this.judgeDetail = judgeDetail;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }
}