package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 试卷与题目对应类
 *
 * @author Leon
 */
@Data
public class ExaminationProblemEntity {

    @JSONField(name = "exam_problem_id")
    private Integer examProblemId;

    @JSONField(name = "exam_id")
    private Integer examId;

    @JSONField(name = "problem_type")
    private Integer problemType;

    @JSONField(name = "problem_id")
    private Integer problemId;

    @JSONField(name = "score")
    private Integer score;

    /**
     * 允许使用的语言
     * 格式举例:{"lang":["JAVA8","PYTHON32"]}
     */
    @JSONField(name = "lang")
    private JSONObject lang;

    @JSONField(name = "exam_problem_locate")
    private Integer examProblemLocate;

}
