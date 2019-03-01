package com.dcoj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 标签实体类
 * @author WANGQING
 */
@Document(collection = "tag")
public class TagEntity {

    @Id
    private String objectId;

    //标签id
    @Field("t_id")
    private Long tid;

    //标签名
    @Field("tag_name")
    private String tagName;

    @Field("used_times")
    private Integer usedTimes;

    //标签是否删除
    @Field("is_deleted")
    private Boolean isDeleted;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(Integer usedTimes) {
        this.usedTimes = usedTimes;
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
                "objectId='" + objectId + '\'' +
                ", tid=" + tid +
                ", tagName='" + tagName + '\'' +
                ", usedTimes=" + usedTimes +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
