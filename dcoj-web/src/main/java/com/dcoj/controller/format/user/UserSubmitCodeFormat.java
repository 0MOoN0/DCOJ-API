package com.dcoj.controller.format.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.LanguageEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户代码提交内容规范
 *
 * @author Leon
 **/
@Data
@ApiModel
public class UserSubmitCodeFormat {

    @NotNull
    @Range(min = 1)
    @JSONField(name = "problem_id")
    private Integer pid;

    @Range(min = 0)
    @NotNull
    @JSONField(name = "examination_id")
    private Integer examinationId;

    @Range(min = 0)
    @NotNull
    @JSONField(name = "group_id")
    private Integer groupId;

    @NotNull
    @JSONField(name = "lang")
    private LanguageEnum lang;

    @NotNull
    @NotBlank
    @JSONField(name = "source_code")
    private String SourceCode;
}
