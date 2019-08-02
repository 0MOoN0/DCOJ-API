package com.dcoj.entity.exam;

import com.dcoj.judge.LanguageEnum;
import lombok.Data;

/**
 * @author Leon
 */
// TODO: Leon 20190708 完善answer内容
@Data
public class AnswerEntity {

    private Integer examProblemLocate;

    private Integer problemType;

    private Integer problemId;

    private StringBuffer answer;

    private LanguageEnum lang;

}
