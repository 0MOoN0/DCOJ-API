package com.dcoj.controller.format.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.LanguageEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 预览提交的代码和语言规定
 * @author Leon
 */
@Data
public class PreviewSubmitFormat {

    @NotNull
    private LanguageEnum lang;

    @JSONField(name = "source_code")
    @NotBlank
    @NotNull
    private String sourceCode;

    @JSONField(name = "test_cases")
    private JSONArray testCases;

    @NotNull
    private Integer pid;
}
