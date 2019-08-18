package com.dcoj.entity.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamProblemResultEntity {

    private Integer examProblemLocate;

    private Integer submissionDetail;

    private Integer score;

}
