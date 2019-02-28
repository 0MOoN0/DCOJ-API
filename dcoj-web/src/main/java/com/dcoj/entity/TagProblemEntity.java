package com.dcoj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author WANGQING
 */
@Document(collection = "tag_problem")
public class TagProblemEntity {

    @Id
    private String objectId;

    //标签id
    @Field("t_ids")
    private List<Long> tids;
    //题目id
    @Field("p_id")
    private Long pid;

    //标签是否删除
    @Field("is_deleted")
    private Boolean isDeleted;

    public List<Long> getTids() {
        return tids;
    }

    public void setTids(List<Long> tids) {
        this.tids = tids;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
