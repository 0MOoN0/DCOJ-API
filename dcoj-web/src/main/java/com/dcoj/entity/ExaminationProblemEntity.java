package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.LanguageEnum;
import lombok.Data;

/**
 * 试卷与题目对应类
 *
 * @author Leon
 */
@Data
public class ExaminationProblemEntity {

    @JSONField(name = "exam_id")
    private Integer examId;

    @JSONField(name = "problem_type")
    private Integer problemType;

    @JSONField(name = "problem_id")
    private Integer problemId;

    @JSONField(name = "score")
    private Integer score;

    @JSONField(name = "lang")
    private LanguageEnum lang;

    @JSONField(name = "exam_problem_locate")
    private Integer examProblemLocate;

}
