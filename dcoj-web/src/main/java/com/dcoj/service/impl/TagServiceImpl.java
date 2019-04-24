package com.dcoj.service.impl;

import com.dcoj.dao.TagMapper;
import com.dcoj.entity.TagEntity;
import com.dcoj.service.TagProblemService;
import com.dcoj.service.TagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签业务层实现
 *
 * @author WANGQING
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagProblemService tagProblemService;

    /**
     * 新增标签
     *
     * @param tagName 标签名
     * @return 返回标签的tid
     */
    @Override
    public int save(String tagName) {
        TagEntity tagEntity = getByName(tagName);
        WebUtil.assertNull(tagEntity, "已经存在此标签");
        boolean flag = tagMapper.save(tagName) == 1;
        WebUtil.assertIsSuccess(flag, "保存标签失败");
        return getByName(tagName).getTid();
    }

    /**
     * 通过标签名得到标签实体类对象
     *
     * @param tagName 标签名
     * @return  标签实体类对象
     */
    @Override
    public TagEntity getByName(String tagName) {
        TagEntity tagEntity = tagMapper.getByName(tagName);
//        WebUtil.assertNotNull(tagEntity, "不存在此标签");
        return tagEntity;
    }

    /**
     * 通过标签id得到标签实体类对象
     *
     * @param tid 标签id
     * @return 标签实体类
     */
    @Override
    public TagEntity getById(int tid) {
        TagEntity tagEntity = tagMapper.getById(tid);
        WebUtil.assertNotNull(tagEntity, "不存在此标签");
        return tagEntity;
    }
    /**
     * 通过标签的 tagName 删除标签
     *
     * @param tagName 标签名
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByTagName(String tagName) {
        TagEntity tagEntity = tagMapper.getByName(tagName);
        WebUtil.assertNotNull(tagEntity, "该标签不存在，无法删除");
        int usedTimes = tagEntity.getUsedTimes();
        WebUtil.assertIsSuccess(usedTimes == 0,"此标签已经被使用，无法删除");
        boolean flag = tagMapper.removeByTagName(tagName) == 1;
        WebUtil.assertIsSuccess(flag, "删除标签失败");
    }

    /**
     * 通过标签的id 删除标签
     *
     * @param tid 标签的id
     */
    @Override
    public void removeById(int tid) {
        TagEntity tagEntity = tagMapper.getById(tid);
        WebUtil.assertNotNull(tagEntity, "该标签不存在，无法删除");
        int usedTimes = tagMapper.getById(tid).getUsedTimes();
        WebUtil.assertIsSuccess(usedTimes == 0,"此标签已经被使用，无法删除");
        boolean flag = tagMapper.removeById(tid) == 1;
        WebUtil.assertIsSuccess(flag, "删除标签失败");
    }

    /**
     * 查询所有标签
     *
     * @return 包含所有标签的List集合
     */
    @Override
    public List<TagEntity> listAll() {
        return tagMapper.listAll();
    }

    /**
     * 更新标签名
     *
     * @param tid     标签id
     * @param newName 修改后的标签名
     */
    @Override
    public void updateByTid(int tid, String newName) {
        TagEntity newTagEntity = getByName(newName);
        WebUtil.assertNull(newTagEntity, "已经存在此标签，修改失败");
        boolean flag = tagMapper.updateByTid(tid,newName) == 1;
        WebUtil.assertIsSuccess(flag, "更新标签名称失败");
    }

    /**
     * 更新标签使用次数
     *
     * @param tid 标签的id
     * @param flag 若flag为true，则更新标签使用次数+1，若flag为false，则更新标签使用次数-1
     */
    @Override
    public void updateTagUsedTimes(int tid, boolean flag) {
        TagEntity tagEntity = tagMapper.getById(tid);
        WebUtil.assertNotNull(tagEntity, "该标签不存在，无法进行修改标签使用次数");
        //judge 为 true 则修改成功，为 false 则修改失败
        boolean judge = tagMapper.updateTagUsedTimes(tid, flag) == 1;
        WebUtil.assertIsSuccess(judge, "修改标签使用次数失败");
    }

    /**
     * 统计标签的总个数
     *
     * @return 标签总个数
     */
    @Override
    public int countTags() {
        return tagMapper.countTags();
    }
}
