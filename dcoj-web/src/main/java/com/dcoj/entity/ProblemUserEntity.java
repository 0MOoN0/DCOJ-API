package com.dcoj.entity;

import com.dcoj.judge.ResultEnum;

/**
 * 用户与编程题目的评判状态实体类
 */
public class ProblemUserEntity {
    //题目id
    private Integer pid;
    //用户id
    private String uid;
    //本次提交的答案
    private String answer;
    // 题目状态
    ResultEnum status;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ResultEnum getStatus() {
        return status;
    }

    public void setStatus(ResultEnum status) {
        this.status = status;
    }
}
