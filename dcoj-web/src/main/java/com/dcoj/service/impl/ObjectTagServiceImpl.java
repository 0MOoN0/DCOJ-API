package com.dcoj.service.impl;

import com.dcoj.dao.ObjectTagMapper;
import com.dcoj.entity.ObjectTagEntity;
import com.dcoj.service.ObjectTagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签 业务层实现
 *
 * @author WANGQING
 */
@Service
public class ObjectTagServiceImpl implements ObjectTagService {

    @Autowired
    private ObjectTagMapper objectTagMapper;

    /**
     * 通过标签的 tid 删除标签
     *
     * @param objectTagId 标签id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Integer objectTagId) {
        ObjectTagEntity objectTagEntity = objectTagMapper.getByPrimaryKey(objectTagId);
        WebUtil.assertNotNull(objectTagEntity, "该标签不存在，无法删除");
        int usedTimes = objectTagEntity.getUsedTimes();
        WebUtil.assertIsSuccess(usedTimes == 0, "此标签已经被使用，无法删除");
        boolean flag = objectTagMapper.removeByPrimaryKey(objectTagId) == 1;
        WebUtil.assertIsSuccess(flag, "删除标签失败");
    }

    /**
     * 新增标签
     *
     * @param tagName 标签名
     * @return 返回值为1时，保存成功，为0则保存失败
     */
    @Override
    public int save(String tagName) {
        ObjectTagEntity objectTagEntity = getByTagName(tagName);
        WebUtil.assertNull(objectTagEntity, "已经存在此标签");
        boolean flag = objectTagMapper.save(tagName) == 1;
        WebUtil.assertIsSuccess(flag, "保存标签失败");
        return getByTagName(tagName).getObjectTagId();
    }

    /**
     * 通过标签objectTagId得到标签实体类对象
     *
     * @param objectTagId 标签id
     * @return 标签实体类对象
     */
    @Override
    public ObjectTagEntity getByPrimaryKey(Integer objectTagId) {
        ObjectTagEntity objectTagEntity = objectTagMapper.getByPrimaryKey(objectTagId);
        WebUtil.assertNotNull(objectTagEntity, "不存在此标签");
        return objectTagEntity;
    }

    /**
     * 通过标签名得到标签实体类对象
     *
     * @param tagName 标签名
     * @return 标签实体类对象
     */
    @Override
    public ObjectTagEntity getByTagName(String tagName) {
        ObjectTagEntity objectTagEntity = objectTagMapper.getByTagName(tagName);
        return objectTagEntity;
    }

    /**
     * 查询所有标签
     *
     * @return 包含所有标签的List集合
     */
    @Override
    public List<ObjectTagEntity> listAll() {
        return objectTagMapper.listAll();
    }

    /**
     * 更新标签名
     *
     * @param objectTagId 标签id
     * @param newName     修改后的标签名
     */
    @Override
    public void updateByPrimaryKey(Integer objectTagId, String newName) {
        ObjectTagEntity objectTagEntity = getByTagName(newName);
        WebUtil.assertNull(objectTagEntity, "不存在此标签,修改失败");
        boolean flag = objectTagMapper.updateByPrimaryKey(objectTagId, newName) == 1;
        WebUtil.assertIsSuccess(flag, "更新标签名称失败");
    }

    /**
     * 更新标签使用次数
     *
     * @param objectTagId 标签id
     * @param flag        若flag为true，则更新标签使用次数+1，若flag为false，则更新标签使用次数-1
     */
    @Override
    public void updateTagUsedTimes(Integer objectTagId, boolean flag) {
        ObjectTagEntity objectTagEntity = objectTagMapper.getByPrimaryKey(objectTagId);
        WebUtil.assertNotNull(objectTagEntity, "该标签不存在，无法进行修改标签使用次数");
        //judge 为 true 则修改成功，为 false 则修改失败
        boolean judge = objectTagMapper.updateTagUsedTimes(objectTagId, flag) == 1;
        WebUtil.assertIsSuccess(judge, "修改标签使用次数失败");
    }

    /**
     * 统计标签的总个数
     *
     * @return 返回标签的总数
     */
    @Override
    public int countTags() {
        return objectTagMapper.countTags();
    }
}
