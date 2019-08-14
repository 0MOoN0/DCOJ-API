package com.dcoj.entity.exam;

import com.dcoj.judge.LanguageEnum;
import lombok.Data;

/**
 *
 * @author Leon
 */
@Deprecated
@Data
public class ExamProblemEntity {

    private Integer examProblemLocate;

    private Integer submissionDetail;

    private Integer score;

    private Integer problemType;

    private LanguageEnum language;

}
