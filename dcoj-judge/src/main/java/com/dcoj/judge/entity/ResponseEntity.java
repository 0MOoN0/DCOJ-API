package com.dcoj.judge.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dcoj.judge.ResultEnum;

import java.util.List;

/**
 * 请求响应Entity
 * @author Leon
 **/
public class ResponseEntity {

    public ResponseEntity(double time, int memory, ResultEnum result, String filePath, List<TestCaseResponseEntity> testCases) {
        this.time = time;
        this.memory = memory;
        this.result = result;
        this.filePath = filePath;
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

    // 判卷文件路径(绝对路径，内容是判卷文件在系统存放路径，此路径包含：判卷源码，编译后文件，输入、输出等)
    @JSONField(name = "file_path")
    private String filePath;

    // 每一个测试用例的结果
    @JSONField(name = "test_cases")
    private List<TestCaseResponseEntity> testCases;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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
