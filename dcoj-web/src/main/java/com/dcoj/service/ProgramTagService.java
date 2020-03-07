package com.dcoj.service;

import com.dcoj.entity.ProgramTagEntity;

import java.util.List;

/**
 * 标签（编程题） 业务层
 *
 * @author WANGQING
 */
public interface ProgramTagService {

    /**
     * 新增标签
     *
     * @param tagName 标签名
     * @return 返回标签的tid
     */

    int save(String tagName);
    /**
     * 根据标签实体新增加
     *
     * @param tagEntity 标签实体
     * @return 返回标签的tidt
     */
    int saveByEntity(ProgramTagEntity tagEntity);

    /**
     * 通过标签名得到标签实体类对象
     *
     * @param tagName 标签名
     * @return 标签实体类对象
     */
    ProgramTagEntity getByTagName(String tagName);

    /**
     * 通过标签id得到标签实体类对象
     *
     * @param programTagId 标签id
     * @return 标签实体类
     */
    ProgramTagEntity getByPrimaryKey(int programTagId);

    /**
     * 通过标签的 tagName 删除标签
     *
     * @param tagName 标签名
     */
    // void removeByTagName(String tagName);

    /**
     * 通过标签的id 删除标签
     *
     * @param programTagId 标签的id
     */
    void removeByPrimaryKey(int programTagId);

    /**
     * 查询所有标签
     *
     * @return 包含所有标签的List集合
     */
    List<ProgramTagEntity> listAll();

    /**
     * 更新标签名
     *
     * @param programTagId 标签id
     * @param newName      修改后的标签名
     */
    void updateByPrimaryKey(int programTagId, String newName);

    /**
     * 更新标签使用次数
     *
     * @param programTagId 标签的id
     * @param flag         若flag为true，则更新标签使用次数+1，若flag为false，则更新标签使用次数-1
     */
    void updateTagUsedTimes(int programTagId, boolean flag);

    /**
     * 统计标签的总个数
     *
     * @return 标签总个数
     */
    int countTags();
}
