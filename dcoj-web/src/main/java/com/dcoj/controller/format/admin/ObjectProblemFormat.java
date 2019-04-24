package com.dcoj.controller.format.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;


/**
 * 添加题目格式校验
 *
 * @author WANGQING
 */
@Getter
@Setter
public class ObjectProblemFormat {
    /** 题目类型 0-选择题 1-填空题 2-判断题*/
    @NotNull
    @Range(min = 0,max = 2)
    private Integer type;
    /** 题目描述 */
    @NotNull
    private JSONObject description;
    /** 题目标签 */
    private JSONArray objectTags;
    /** 题目答案 */
    @NotNull
    private String answer;
}


