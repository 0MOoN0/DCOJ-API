package com.dcoj.judge.entity;

/**
 * 测试用例Entity(请求部分);
 *
 * @author Leon
 **/
public class TestCaseRequestEntity {
    private String stdin;

    private String stdout;

    public TestCaseRequestEntity(String stdin, String stdout) {
        this.stdin = stdin;
        this.stdout = stdout;
    }

    public TestCaseRequestEntity() {
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }
}
