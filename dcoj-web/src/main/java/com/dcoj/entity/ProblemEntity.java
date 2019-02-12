package com.dcoj.entity;


import com.alibaba.fastjson.JSONObject;

/**
 * 选择题和填空题、判断题实体类
 */
public class ProblemEntity {
    //题目id
    private Integer pid;
    //题目标题
    private String title;
    //题目类型 0 选择题 1 填空题 2 判断题
    private Integer pType;
    //题目描述
    private JSONObject description;
    //题目难度（简单0 中等1 困难2）
    private Integer difficult;
    //提交次数
    private Integer submitTimes;
    //通过次数
    private Integer ACTimes;
    //题目创建时间
    private Long createTime;
    //是否删除题目（题目是否存在）
    private Boolean isDeleted;
    //是否显示题目
    private Boolean isShowed;
    //题目答案
    private String answer;
}
