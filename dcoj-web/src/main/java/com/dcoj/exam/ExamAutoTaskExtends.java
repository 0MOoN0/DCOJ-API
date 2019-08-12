package com.dcoj.exam;

import lombok.Data;

/**
 * @author Leon
 */
@Data
public class ExamAutoTaskExtends extends ExamAutoTask {

    private Integer type;

    private String answer;

    // 用户考卷ID，根据此ID从缓存中获取试卷相关提交信息
    private Integer examUserId;

    private Integer uid;

    // 试卷ID
    private Integer examId;
}
