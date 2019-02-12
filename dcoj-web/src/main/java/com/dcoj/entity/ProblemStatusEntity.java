package com.dcoj.entity;

import java.util.Date;

/**
 * 题库状态实体类
 */
public class ProblemStatusEntity {
    //状态id
    private Integer psid;
    //用户id
    private Integer uid;
    //题目id
    private Integer pid;
    //题目类型 0 选择题 1 填空题 2 判断题 3编程题
    private Integer pType;
    //提交时间
    private Date submitTime;
}
