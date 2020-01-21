package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author zxw
 * @Desriiption: 客观题类别
 */
@Data
public class ObjectProblemCateEntity {

    @JSONField(name = "id")
    private Integer id;

    @JSONField(name = "object_problem_id")
    private Integer objectProblemId;

    @JSONField(name = "cate_id")
    private Integer cateId;

    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;
}
