package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 客观题实体类
 *
 * @author WANGQING
 */
@Data
public class ObjectProblemEntity {
    /** 题目id */
    private int objectProblemId;
    /** 题目描述 */
    private JSONObject description;
    /** 题目类型 0-选择题 1-填空题 2-判断题 */
    private Integer type;
    /** 题目答案 */
    private String answer;
    /** 提交次数 */
    private Integer submitTimes;
    /** 通过次数 */
    private Integer ACTimes;
    /** 题目状态 */
    private Integer status;
    /** 题目最后更新时间 */
    private Timestamp gmtModified;
    /** 题目创建时间 */
    private Timestamp gmtCreate;
    /** 是否删除题目（题目是否存在） */
    private Integer deleted;
}
