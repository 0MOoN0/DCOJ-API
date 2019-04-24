package com.dcoj.entity;

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
    private Integer programTagId;
    /** 标签名 */
    private String tagName;
    /** 标签使用次数 */
    private Integer usedTimes;
    /** 标签创建时间 */
    private Timestamp gmtCreate;
    /** 标签最后修改时间 */
    private Timestamp gmtModified;
    /** 标签是否删除 */
    private Integer deleted;
}
