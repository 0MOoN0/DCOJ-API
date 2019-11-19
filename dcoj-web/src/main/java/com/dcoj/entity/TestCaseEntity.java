package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 测试用例类
 *
 * @author Leon
 **/
@Data
public class TestCaseEntity {

    /**
     * 测试用例 id
     */
    @JSONField(name = "tc_id")
    private Integer tcId;

    /**
     * 测试用例对应的题目id
     */
    private Integer pid;

    /**
     * 输入
     */
    private String stdin;

    /**
     * 输出
     */
    private String stdout;

    /**
     * 对应的编程题
     */
    private ProgramProblemEntity programProblemEntity;

    public TestCaseEntity(Integer tcId, Integer pid, String stdin, String stdout) {
        this.tcId = tcId;
        this.pid = pid;
        this.stdin = stdin;
        this.stdout = stdout;
    }


    public TestCaseEntity() {
    }


}
