package com.dcoj.service;

import com.dcoj.entity.TagEntity;

import java.util.List;

/**
 * @author WANGQING
 * @description 题目标签业务层
 */
public interface TagService {

    /**
     * 新增标签
     * @param name
     * @return
     */
    void save(String name);

    /**
     * 通过标签名得到标签实体类对象
     * @param tagName
     * @return
     */
    TagEntity getByName(String tagName);

    /**
     * 通过标签id得到标签实体类对象
     * @param tid
     * @return
     */
    TagEntity getById(long tid);

    /**
     * 通过标签的 tagName 删除标签
     * @param tagName
     */
    void removeTag(String tagName);

    /**
     * 查询所有标签
     * @return
     */
    List<TagEntity> listAll();

    /**
     * 更新标签名
     * @param oldName
     * @param newName
     * @return
     */
    void updateTagName(String oldName,String newName);

    /**
     * 更新标签使用次数+1
     * @param tid
     */
    void updateTagUsedTimes(long tid);


    /**
     * 统计标签的总个数
     * @return
     */
    long countTags();
}
