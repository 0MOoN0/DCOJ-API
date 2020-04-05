package com.dcoj.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 客观题实体类
 *
 * @author WANGQING
 */
@Data
public class ObjectProblemEntity {
    /**
     * 题目id
     */
    @JSONField(name = "object_problem_id")
    private Integer objectProblemId;
    /**
     * 题目描述
     */
    private JSONObject description;
    /**
     * 题目类型 0-选择题 1-填空题 2-判断题
     */
    private Integer type;
    /**
     * 题目答案
     */
    private String answer;
    /**
     * 提交次数
     */
    @JSONField(name = "submit_times")
    private Integer submitTimes;
    /**
     * 通过次数
     */
    @JSONField(name = "ac_times")
    private Integer ACTimes;
    /**
     * 题目状态
     */
    private Integer status;
    /**
     * 题目最后更新时间
     */
    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;
    /**
     * 题目创建时间
     */
    @JSONField(name = "gmt_create")
    private Timestamp gmtCreate;
    /**
     * 是否删除题目（题目是否存在）
     */
    private Integer deleted;

    /**
     *  类别id
     */
    private Integer cateId;
}
