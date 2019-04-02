package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 测试用例类
 * @author Leon
 **/
@Data
public class TestCaseEntity {
    /** 测试用例 id */
    private Integer tid;

    /** 测试用例对应的题目id */
    private Integer pid;

    /** 输入 */
    private String stdin;

    /** 输出 */
    private String stdout;


//    private Integer strength;

    /** 创建时间 */
    @JSONField(name = "create_time")
    private Long createTime;


    public TestCaseEntity() {
    }

    public TestCaseEntity(Integer tid, Integer pid, String stdin, String stdout, Long createTime) {
        this.tid = tid;
        this.pid = pid;
        this.stdin = stdin;
        this.stdout = stdout;
        this.createTime = createTime;
    }

/*    public TestCaseEntity(Integer pid, String stdin, String stdout, Integer strength, Long createTime) {
        this.pid = pid;
        this.stdin = stdin;
        this.stdout = stdout;
        this.strength = strength;
        this.createTime = createTime;
    }*/

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

/*    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }*/

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
