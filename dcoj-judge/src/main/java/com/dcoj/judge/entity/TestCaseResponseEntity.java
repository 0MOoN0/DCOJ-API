package com.dcoj.judge.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.ResultEnum;

/**
 * 测试用例Entity(响应部分);
 *
 * @author Leon
 **/
public class TestCaseResponseEntity {

    public TestCaseResponseEntity(ResultEnum result, String errorMessage, Double time, Double memory) {
        this.result = result;
        this.errorMessage = errorMessage;
        this.time = time;
        this.memory = memory;
    }

    public TestCaseResponseEntity() {
    }

    private ResultEnum result;

    @JSONField(name = "error_message")
    private String errorMessage;

    private Double time;

    private Double memory;

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public ResultEnum getResult() {
        return result;
    }

    public void setResult(ResultEnum result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
