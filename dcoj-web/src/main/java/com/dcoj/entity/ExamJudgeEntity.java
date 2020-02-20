package com.dcoj.entity;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * @author zxw
 * @Desriiption: 试卷判卷实体
 */
@Data
public class ExamJudgeEntity {

    /**
     *  题目列表
     */
    private JSONArray problem_list;

    /**
     *  用户id
     */
    private int userId;

    /**
     *  试卷id
     */
    private int examId;
}
