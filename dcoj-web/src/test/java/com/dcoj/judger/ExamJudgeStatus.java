package com.dcoj.judger;

/**
 * @author Leon
 */
public enum ExamJudgeStatus {
    Oponing("开放中"), Locking("锁定中"), InQueue("排队中"), Judging("正在判卷"), Saving("保存中"), Finished("完成"), Error("错误");

    private String message;

    private ExamJudgeStatus(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
