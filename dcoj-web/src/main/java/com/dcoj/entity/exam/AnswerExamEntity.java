package com.dcoj.entity.exam;

import com.dcoj.entity.ExaminationProblemEntity;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 答题卡与题目Entity，用于用户考试判卷
 *
 * @author Leon
 */
@Data
public class AnswerExamEntity {

    private List<AnswerEntity> answerSheet;

    private Map<Integer, ExaminationProblemEntity> examProblemSheet;

}
