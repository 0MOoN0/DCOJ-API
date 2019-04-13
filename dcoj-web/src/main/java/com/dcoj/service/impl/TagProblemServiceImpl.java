package com.dcoj.service.impl;

import com.dcoj.dao.TagProblemMapper;
import com.dcoj.entity.TagProblemEntity;
import com.dcoj.service.TagProblemService;
import com.dcoj.service.TagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 题目标签关联业务层
 *
 * @author WANGQING
 */
@Service
public class TagProblemServiceImpl implements TagProblemService {

    @Autowired
    private TagProblemMapper tagProblemMapper;

    @Autowired
    private TagService tagService;

    /**
     * 为某道题添加一个或者多个标签
     *
     * @param pid 题目id
     * @param tid 标签id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(int pid, int tid) {
        boolean flag = tagProblemMapper.save(pid, tid) == 1;
        WebUtil.assertIsSuccess(flag, "题目标签添加失败");
        tagService.updateTagUsedTimes(tid, true);
    }

    /**
     * 统计某道题标签的个数
     *
     * @param pid 题目id
     * @return 返回该题目的标签个数
     */
    @Override
    public int countTagsByPid(int pid) {
        return tagProblemMapper.countTagsByPid(pid);
    }

    /**
     * 得到某道题的所有标签
     *
     * @param pid 题目id
     * @return 包含所有标签id的List集合
     */
    @Override
    public List<Integer> getTagsByPid(int pid) {
        return tagProblemMapper.getTagsByPid(pid);
    }

    /**
     * 通过pid 获取 TagProblemEntity对象
     *
     * @param pid 题目id
     * @return 返回 TagProblemEntity 实体类对象
     */
    @Override
    public TagProblemEntity getByPid(int pid) {
        TagProblemEntity tagProblemEntity = tagProblemMapper.getByPid(pid);
        WebUtil.assertNotNull(tagProblemEntity, "不存在此题目标签");
        return tagProblemEntity;
    }

    /**
     * 通过 pid 删除TagProblemEntity对象
     *
     * @param pid 题目id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeProblemAllTags(int pid) {
        List<Integer> tagList = tagProblemMapper.getTagsByPid(pid);
//        WebUtil.assertIsSuccess(tagList.size()!=0,"该题目无标签，删除失败");
        for (int tid : tagList) {
            tagService.updateTagUsedTimes(tid, false);
        }
        // tagProblemMapper.removeProblemAllTags(pid)返回值不为0时，删除成功，为0则删除失败
        boolean flag = tagProblemMapper.removeProblemAllTags(pid) != 0;
        WebUtil.assertIsSuccess(flag, "删除该题目的所有标签失败");
    }

    /**
     * 通过 pid 和 tid 删除一条记录
     *
     * @param pid 题目id
     * @param tid 标签id
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void removeProblemTag(int pid, int tid) {
        boolean flag = tagProblemMapper.removeProblemTag(pid, tid) == 1;
        WebUtil.assertIsSuccess(flag, "删除该题目的一个标签失败");
        tagService.updateTagUsedTimes(tid, false);
    }
}
