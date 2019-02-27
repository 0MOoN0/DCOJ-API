package com.dcoj.entity;

import com.dcoj.judge.ResultEnum;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户与所有题目的评判状态实体类
 */
@Document("problem_user")
public class ProblemUserEntity {
    //题目id
    private Integer pid;
    //用户id
    private Integer uid;
    //本次提交的答案
    private String answer;
    // 做题状态
    private ResultEnum status;



    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
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


