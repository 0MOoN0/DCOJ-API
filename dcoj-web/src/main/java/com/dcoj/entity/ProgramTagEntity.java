package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 标签实体类
 *
 * @author WANGQING
 */
@Data
public class ProgramTagEntity {
    /** 标签id */
    @JSONField(name = "program_tag_id")
    private Integer programTagId;
    /** 标签名 */
    @JSONField(name = "tag_name")
    private String tagName;
    /** 标签使用次数 */
    @JSONField(name = "used_times")
    private Integer usedTimes;
    /** 标签创建时间 */
    @JSONField(name = "gmt_create")
    private Timestamp gmtCreate;
    /** 标签最后修改时间 */
    @JSONField(name = "gmt_modified")
    private Timestamp gmtModified;
    /** 标签是否删除 */
    private Integer deleted;
}
