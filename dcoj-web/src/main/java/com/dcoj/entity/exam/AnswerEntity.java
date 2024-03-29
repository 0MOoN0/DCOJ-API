package com.dcoj.entity.exam;

import com.dcoj.judge.LanguageEnum;
import lombok.Data;

/**
 * 用户答题内容，一个Entity对应一道题目
 * @author Leon
 */
// TODO: Leon 20190708 完善answer内容
@Data
public class AnswerEntity {

    private Integer examProblemId;

    private Integer problemType;

    private Integer problemId;

    private StringBuffer answer;

    private LanguageEnum lang;

    private Integer score;

}
