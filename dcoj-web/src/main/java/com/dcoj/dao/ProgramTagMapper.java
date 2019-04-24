package com.dcoj.dao;

import com.dcoj.entity.ProgramTagEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目标签持久层
 *
 * @author WANGQING
 */
public interface ProgramTagMapper {

        /**
         * 新增标签
         *
         * @param tagName 标签名
         * @return 返回值为1时，保存成功，为0则保存失败
         */
        int save(String tagName);

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
         * @return 标签实体类对象
         */
        ProgramTagEntity getByPrimaryKey(int programTagId);

        /**
         * 通过标签的 tagName 删除标签
         *
         * @param tagName 标签名
         * @return 返回值为1时，删除成功，为0则删除失败
         */
        int removeByTagName(String tagName);

        /**
         * 通过标签的 tid 删除标签
         *
         * @param programTagId 标签id
         * @return 返回值为1时，删除成功，为0则删除失败
         */
        int removeByPrimaryKey(int programTagId);

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
         * @param newName 修改后的标签名
         * @return 返回值为1时，修改成功，为0则修改失败
         */
        int updateByPrimaryKey(@Param("programTagId") int programTagId,@Param("newName") String newName);

        /**
         * 更新标签使用次数
         *
         * @param programTagId 标签id
         * @param flag 若flag为true，则更新标签使用次数+1，若flag为false，则更新标签使用次数-1
         * @return 返回值为1时，修改成功，为0则修改失败
         */
        int updateTagUsedTimes(@Param("programTagId") int programTagId, @Param("flag") boolean flag);

        /**
         * 统计标签的总个数
         *
         * @return 返回标签的总数
         */
        int countTags();
}
