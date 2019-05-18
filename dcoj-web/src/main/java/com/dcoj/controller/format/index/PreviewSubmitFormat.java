package com.dcoj.controller.format.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.LanguageEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 预览提交的代码和语言规定
 *
 * @author Leon
 */
@Data
@ApiModel
public class PreviewSubmitFormat {

    @NotNull
    @ApiModelProperty(value = "编程语言类型", allowEmptyValue = false, name = "lang", example = "JAVA8")
    private LanguageEnum lang;

    @JSONField(name = "source_code")
    @ApiModelProperty(value = "源码", allowEmptyValue = false, name = "source_code")
    @NotBlank
    @NotNull
    private String sourceCode;

    @ApiModelProperty(value = "测试用例", allowEmptyValue = true, name = "test_case")
    @JSONField(name = "test_cases")
    private JSONArray testCases;

    @NotNull
    @ApiModelProperty(value = "题目ID", allowEmptyValue = false, name = "pid")
    private Integer pid;
}
