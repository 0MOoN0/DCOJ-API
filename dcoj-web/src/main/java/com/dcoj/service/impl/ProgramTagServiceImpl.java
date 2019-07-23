package com.dcoj.service.impl;

import com.dcoj.dao.ProgramTagMapper;
import com.dcoj.entity.ProgramTagEntity;
import com.dcoj.service.ProgramTagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签（编程题） 业务层实现
 *
 * @author WANGQING
 */
@Service
public class ProgramTagServiceImpl implements ProgramTagService {

    @Autowired
    private ProgramTagMapper programTagMapper;

    /**
     * 新增标签
     *
     * @param tagName 标签名
     * @return 返回标签的tid
     */
    @Override
    public int save(String tagName) {
        ProgramTagEntity programTagEntity = getByTagName(tagName);
        WebUtil.assertNull(programTagEntity, "已经存在此标签");
        boolean flag = programTagMapper.save(tagName) == 1;
        WebUtil.assertIsSuccess(flag, "保存标签失败");
        return getByTagName(tagName).getProgramTagId();
    }

    /**
     * 通过标签名得到标签实体类对象
     *
     * @param tagName 标签名
     * @return 标签实体类对象
     */
    @Override
    public ProgramTagEntity getByTagName(String tagName) {
        ProgramTagEntity programTagEntity = programTagMapper.getByTagName(tagName);
//        WebUtil.assertNotNull(programTagEntity, "不存在此标签");
        return programTagEntity;
    }

    /**
     * 通过标签id得到标签实体类对象
     *
     * @param programTagId 标签id
     * @return 标签实体类
     */
    @Override
    public ProgramTagEntity getByPrimaryKey(int programTagId) {
        ProgramTagEntity programTagEntity = programTagMapper.getByPrimaryKey(programTagId);
        WebUtil.assertNotNull(programTagEntity, "不存在此标签");
        return programTagEntity;
    }
//    /**
//     * 通过标签的 tagName 删除标签
//     *
//     * @param tagName 标签名
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void removeByTagName(String tagName) {
//        ProgramTagEntity programTagEntity = programTagMapper.getByTagName(tagName);
//        WebUtil.assertNotNull(programTagEntity, "该标签不存在，无法删除");
//        int usedTimes = programTagEntity.getUsedTimes();
//        WebUtil.assertIsSuccess(usedTimes == 0,"此标签已经被使用，无法删除");
//        boolean flag = programTagMapper.removeByTagName(tagName) == 1;
//        WebUtil.assertIsSuccess(flag, "删除标签失败");
//    }

    /**
     * 通过标签的id 删除标签
     *
     * @param programTagId 标签的id
     */
    @Override
    public void removeByPrimaryKey(int programTagId) {
        ProgramTagEntity programTagEntity = programTagMapper.getByPrimaryKey(programTagId);
        WebUtil.assertNotNull(programTagEntity, "该标签不存在，无法删除");
        int usedTimes = programTagMapper.getByPrimaryKey(programTagId).getUsedTimes();
        WebUtil.assertIsSuccess(usedTimes == 0, "此标签已经被使用，无法删除");
        boolean flag = programTagMapper.removeByPrimaryKey(programTagId) == 1;
        WebUtil.assertIsSuccess(flag, "删除标签失败");
    }

    /**
     * 查询所有标签
     *
     * @return 包含所有标签的List集合
     */
    @Override
    public List<ProgramTagEntity> listAll() {
        return programTagMapper.listAll();
    }

    /**
     * 更新标签名
     *
     * @param programTagId 标签id
     * @param newName      修改后的标签名
     */
    @Override
    public void updateByPrimaryKey(int programTagId, String newName) {
        ProgramTagEntity newProgramTagEntity = getByTagName(newName);
        WebUtil.assertNull(newProgramTagEntity, "已经存在此标签，修改失败");
        boolean flag = programTagMapper.updateByPrimaryKey(programTagId, newName) == 1;
        WebUtil.assertIsSuccess(flag, "更新标签名称失败");
    }

    /**
     * 更新标签使用次数
     *
     * @param programTagId 标签的id
     * @param flag         若flag为true，则更新标签使用次数+1，若flag为false，则更新标签使用次数-1
     */
    @Override
    public void updateTagUsedTimes(int programTagId, boolean flag) {
        ProgramTagEntity programTagEntity = programTagMapper.getByPrimaryKey(programTagId);
        WebUtil.assertNotNull(programTagEntity, "该标签不存在，无法进行修改标签使用次数");
        //judge 为 true 则修改成功，为 false 则修改失败
        boolean judge = programTagMapper.updateTagUsedTimes(programTagId, flag) == 1;
        WebUtil.assertIsSuccess(judge, "修改标签使用次数失败");
    }

    /**
     * 统计标签的总个数
     *
     * @return 标签总个数
     */
    @Override
    public int countTags() {
        return programTagMapper.countTags();
    }
}
