package com.dcoj.service.impl;

import com.dcoj.dao.ObjectProblemTagMapper;
import com.dcoj.service.ObjectProblemTagService;
import com.dcoj.service.ObjectTagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客观题、标签关联 业务层实现
 *
 * @author WANGQING
 */
@Service
public class ObjectProblemTagServiceImpl implements ObjectProblemTagService {
    @Autowired
    private ObjectProblemTagMapper objectProblemTagMapper;

    @Autowired
    private ObjectTagService objectTagService;

    /**
     * 为某道题添加一个或者多个标签
     *
     * @param objectProblemId 题目id
     * @param objectTagId     标签id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(int objectProblemId, int objectTagId) {
        boolean flag = objectProblemTagMapper.save(objectProblemId, objectTagId) == 1;
        WebUtil.assertIsSuccess(flag, "题目标签添加失败");
        objectTagService.updateTagUsedTimes(objectTagId, true);
    }

    /**
     * 统计某道题标签的个数
     *
     * @param objectProblemId 题目id
     * @return 返回该题目的标签个数
     */
    @Override
    public int countTagsByObjectProblemId(int objectProblemId) {
        return objectProblemTagMapper.countTagsByObjectProblemId(objectProblemId);
    }

    /**
     * 得到某道题的所有标签
     *
     * @param objectProblemId 题目id
     * @return 包含所有标签id的List集合
     */
    @Override
    public List<Integer> getTagsByObjectProblemId(int objectProblemId) {
        return objectProblemTagMapper.getTagsByObjectProblemId(objectProblemId);
    }

    /**
     * 通过 objectProblemId 删除TagProblemEntity对象
     *
     * @param objectProblemId 题目id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeProblemAllTags(int objectProblemId) {

        List<Integer> tagList = objectProblemTagMapper.getTagsByObjectProblemId(objectProblemId);

        if(tagList != null || tagList.size() > 0){
            for (int tid : tagList) {
                objectTagService.updateTagUsedTimes(tid, false);
            }
            // tagProblemMapper.removeProblemAllTags(pid)返回值不为0时，删除成功，为0则删除失败
            boolean flag = objectProblemTagMapper.removeProblemAllTags(objectProblemId) != 0;
            WebUtil.assertIsSuccess(flag, "删除该题目的所有标签失败");
        }


    }

}
