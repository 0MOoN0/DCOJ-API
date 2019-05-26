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
//@ApiModel
public class PreviewSubmitFormat {

//    @ApiModelProperty(value = "编程语言类型", name = "lang", example = "JAVA8")
    @NotNull
    private LanguageEnum lang;

//    @ApiModelProperty(value = "源码", name = "source_code",example = "public class Main {public static void main(String[] args) {System.out.println(\"hll\");}}")
    @JSONField(name = "source_code")
    @NotBlank
    @NotNull
    private String sourceCode;

//    @ApiModelProperty(value = "测试用例", name = "test_case")
    @JSONField(name = "test_cases")
    private JSONArray testCases;

//    @ApiModelProperty(value = "题目ID", name = "pid")
    @NotNull
    private Integer pid;
}
