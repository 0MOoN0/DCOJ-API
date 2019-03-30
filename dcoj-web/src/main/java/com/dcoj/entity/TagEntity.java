package com.dcoj.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 标签实体类
 *
 * @author WANGQING
 */
@Data
public class TagEntity {
    /** 标签id */
    private Integer tid;
    /** 标签名 */
    private String tagName;
    /** 标签使用次数 */
    private Integer usedTimes;
    /** 标签创建时间 */
    private Timestamp createTime;
    /** 标签最后修改时间 */
    private Timestamp modifiedTime;
    /** 标签是否删除 */
    private Integer deleted;
}
