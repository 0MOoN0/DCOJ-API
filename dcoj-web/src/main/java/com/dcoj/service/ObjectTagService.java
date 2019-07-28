package com.dcoj.service;

import com.dcoj.entity.ObjectTagEntity;

import java.util.List;

/**
 * 标签（客观题） 业务层
 *
 * @author WANGQING
 */
public interface ObjectTagService {

    /**
     * 通过标签的 tid 删除标签
     *
     * @param objectTagId 标签id
     */
    void removeByPrimaryKey(Integer objectTagId);

    /**
     * 新增标签
     *
     * @param tagName 标签名
     * @return 返回值为1时，保存成功，为0则保存失败
     */
    int save(String tagName);

    /**
     * 通过标签objectTagId得到标签实体类对象
     *
     * @param objectTagId 标签id
     * @return 标签实体类对象
     */
    ObjectTagEntity getByPrimaryKey(Integer objectTagId);

    /**
     * 通过标签名得到标签实体类对象
     *
     * @param tagName 标签名
     * @return 标签实体类对象
     */
    ObjectTagEntity getByTagName(String tagName);

    /**
     * 查询所有标签
     *
     * @return 包含所有标签的List集合
     */
    List<ObjectTagEntity> listAll();

    /**
     * 更新标签名
     *
     * @param objectTagId 标签id
     * @param newName     修改后的标签名
     */
    void updateByPrimaryKey(Integer objectTagId, String newName);

    /**
     * 更新标签使用次数
     *
     * @param objectTagId 标签id
     * @param flag        若flag为true，则更新标签使用次数+1，若flag为false，则更新标签使用次数-1
     */
    void updateTagUsedTimes(Integer objectTagId, boolean flag);

    /**
     * 统计标签的总个数
     *
     * @return 返回标签的总数
     */
    int countTags();

}
