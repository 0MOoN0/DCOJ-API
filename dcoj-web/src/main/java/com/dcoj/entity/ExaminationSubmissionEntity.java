package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.exam.ExamJudgeStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author Leon
 */
@Data
public class ExaminationSubmissionEntity {

    /** 考试提交ID */
    @JSONField(name = "exam_submission_id")
    private Integer examSubmissionId;

    /** 试卷状态 */
    @JSONField(name = "exam_status")
    private ExamJudgeStatus examStatus;

    /** 考试所得分数 */
    @JSONField(name = "score")
    private Integer score;

    /** 试卷ID */
    @JSONField(name = "exam_id")
    private Integer examId;

    /** 可查询时间 */
    @JSONField(name = "queryable_time")
    private Date queryableTime;

    /** 用户ID */
    @JSONField(name = "uid")
    private Integer uid;

}