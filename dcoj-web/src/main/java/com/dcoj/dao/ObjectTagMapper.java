package com.dcoj.dao;

import com.dcoj.entity.ObjectTagEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签 数据层
 *
 * @author WANGQING
 */
public interface ObjectTagMapper {

    /**
     * 通过标签的 tid 删除标签
     *
     * @param objectTagId 标签id
     * @return 返回值为1时，删除成功，为0则删除失败
     */
    int removeByPrimaryKey(Integer objectTagId);

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
     * @return 返回值为1时，修改成功，为0则修改失败
     */
    int updateByPrimaryKey(@Param("objectTagId") Integer objectTagId, @Param("newName") String newName);

    /**
     * 更新标签使用次数
     *
     * @param objectTagId 标签id
     * @param flag        若flag为true，则更新标签使用次数+1，若flag为false，则更新标签使用次数-1
     * @return 返回值为1时，修改成功，为0则修改失败
     */
    int updateTagUsedTimes(@Param("objectTagId") Integer objectTagId, @Param("flag") boolean flag);

    /**
     * 统计标签的总个数
     *
     * @return 返回标签的总数
     */
    int countTags();
}