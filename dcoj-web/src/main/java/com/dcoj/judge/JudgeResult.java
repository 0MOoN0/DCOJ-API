package com.dcoj.judge;


import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.task.JudgeTask;

/**
 * @author Leon
 **/
public class JudgeResult {
    private String id;

    // 判卷状态
    private JudgeStatus status;

    // 判卷结果
    private ResponseEntity response;

    private JudgeTask judgeTask;

    public JudgeResult(String id, JudgeStatus status, ResponseEntity response, JudgeTask judgeTask) {
        this.id = id;
        this.status = status;
        this.response = response;
        this.judgeTask = judgeTask;
    }

    public JudgeTask getJudgeTask() {
        return judgeTask;
    }

    public void setJudgeTask(JudgeTask judgeTask) {
        this.judgeTask = judgeTask;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JudgeStatus getStatus() {
        return status;
    }

    public void setStatus(JudgeStatus status) {
        this.status = status;
    }

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }
}
