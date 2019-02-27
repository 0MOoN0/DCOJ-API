package com.dcoj.judge.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.ResultEnum;

import java.util.List;

/**
 * 请求响应Entity
 * @author Leon
 **/
public class ResponseEntity {

    public ResponseEntity(double time, int memory, ResultEnum result,  List<TestCaseResponseEntity> testCases) {
        this.time = time;
        this.memory = memory;
        this.result = result;
        this.testCases = testCases;
    }

    public ResponseEntity() {
    }

    // 判卷所用平均时间
    private Double time;

    // 判卷所用内存
    private int memory;

    // 判卷整体结果
    private ResultEnum result;

    // 每一个测试用例的结果
    @JSONField(name = "test_cases")
    private List<TestCaseResponseEntity> testCases;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public ResultEnum getResult() {
        return result;
    }

    public void setResult(ResultEnum result) {
        this.result = result;
    }

    public List<TestCaseResponseEntity> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseResponseEntity> testCases) {
        this.testCases = testCases;
    }
}
