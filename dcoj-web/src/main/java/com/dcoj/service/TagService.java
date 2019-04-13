package com.dcoj.service;

import com.dcoj.entity.TagEntity;

import java.util.List;

/**
 * 标签业务层
 *
 * @author WANGQING
 */
public interface TagService {

    /**
     * 新增标签
     *
     * @param tagName 标签名
     * @return 返回标签的tid
     */
    int save(String tagName);

    /**
     * 通过标签名得到标签实体类对象
     *
     * @param tagName 标签名
     * @return 标签实体类对象
     */
    TagEntity getByName(String tagName);

    /**
     * 通过标签id得到标签实体类对象
     *
     * @param tid 标签id
     * @return 标签实体类
     */
    TagEntity getById(int tid);

    /**
     * 通过标签的 tagName 删除标签
     *
     * @param tagName 标签名
     */
    void removeByTagName(String tagName);

    /**
     * 通过标签的id 删除标签
     *
     * @param tid 标签的id
     */
    void removeById(int tid);

    /**
     * 查询所有标签
     *
     * @return 包含所有标签的List集合
     */
    List<TagEntity> listAll();

    /**
     * 更新标签名
     *
     * @param tid 标签id
     * @param newName 修改后的标签名
     */
    void updateByTid(int tid,String newName);

    /**
     * 更新标签使用次数
     *
     * @param tid 标签的id
     * @param flag 若flag为true，则更新标签使用次数+1，若flag为false，则更新标签使用次数-1
     */
    void updateTagUsedTimes(int tid, boolean flag);

    /**
     * 统计标签的总个数
     *
     * @return 标签总个数
     */
    int countTags();
}
