package com.dcoj.controller.format.user;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

/**
 * @author Leon
 */
@Data
public class UserObjectJudgeFormat {

    @NotNull
    @Range(min = 1)
    private Integer pid;


    private String answer;


}
