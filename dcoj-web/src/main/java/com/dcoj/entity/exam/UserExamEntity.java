package com.dcoj.entity.exam;

import com.dcoj.exam.ExamJudgeStatus;
import com.dcoj.judge.JudgeStatus;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Leon
 *
 */
// TODO: Leon 20190708 完善用户所做试卷内容
@Data
public class UserExamEntity {

    private Integer examId;

    private Integer userId;

    private List<AnswerEntity> answerSheet;

    private ExamJudgeStatus examJudgeStatus;

}
