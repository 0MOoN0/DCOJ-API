package com.dcoj.entity;

import com.dcoj.exam.ExamJudgeStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author Leon
 */
@Data
public class ExaminationSubmissionEntity {

    /** 考试提交ID */
    private Integer examSubmissionId;

    /** 试卷状态 */
    private ExamJudgeStatus examStatus;

    /** 考试所得分数 */
    private Integer score;

    /** 试卷ID */
    private Integer examId;

    /** 可查询时间 */
    private Date queryableTime;

    /** 用户ID */
    private Integer uid;

}