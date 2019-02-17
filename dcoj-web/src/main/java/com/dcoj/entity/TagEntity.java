package com.dcoj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author WANGQING
 * @description 标签实体类
 */
@Document(collection = "tag")
public class TagEntity {

    //标签id
    @Id
    private String tid;

    //标签名
    @Field("tag_name")
    private String tagName;

    //标签是否删除
    @Field("isDeleted")
    private Boolean isDeleted;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "TagEntity{" +
                "tid='" + tid + '\'' +
                ", tagName='" + tagName + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
