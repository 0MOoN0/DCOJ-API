package com.dcoj.service.impl;

import com.dcoj.dao.ProgramProblemTagMapper;
import com.dcoj.entity.ProgramProblemTagEntity;
import com.dcoj.service.ProgramProblemTagService;
import com.dcoj.service.ProgramTagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 编程题、标签关联 业务层实现
 *
 * @author WANGQING
 */
@Service
public class ProgramProblemTagServiceImpl implements ProgramProblemTagService {

    @Autowired
    private ProgramProblemTagMapper programProblemTagMapper;

    @Autowired
    private ProgramTagService programTagService;

    /**
     * 为某道题添加一个或者多个标签
     *
     * @param programProblemId 题目id
     * @param programTagId     标签id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(int programProblemId, int programTagId) {
        boolean flag = programProblemTagMapper.save(programProblemId, programTagId) == 1;
        WebUtil.assertIsSuccess(flag, "题目标签添加失败");
        programTagService.updateTagUsedTimes(programTagId, true);
    }

    /**
     * 统计某道题标签的个数
     *
     * @param programTagId 题目id
     * @return 返回该题目的标签个数
     */
    @Override
    public int countTagsByProgramProblemId(int programTagId) {
        return programProblemTagMapper.countTagsByProgramProblemId(programTagId);
    }

    /**
     * 得到某道题的所有标签
     *
     * @param programTagId 题目id
     * @return 包含所有标签id的List集合
     */
    @Override
    public List<Integer> getTagsByProgramProblemId(int programTagId) {
        return programProblemTagMapper.getTagsByProgramProblemId(programTagId);
    }

    /**
     * 通过 programTagId 删除TagProblemEntity对象
     *
     * @param programProblemId 题目id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeProblemAllTags(int programProblemId) {
        //获取所有标签ID集合
        List<Integer> isExist  = programProblemTagMapper.getTagsByProgramProblemId(programProblemId);
        //判断是否存在记录,如果存在，则进行批量删除操作
        if(isExist == null || isExist.size() == 0){
            return ;
        }
        //更新标签使用次数
        Integer[] tagIdList = isExist.toArray(new Integer[isExist.size()]);
        programTagService.batchUpdateTagUsedTimes(tagIdList,false);
        boolean flag = programProblemTagMapper.removeProblemAllTags(programProblemId) != 0;
        WebUtil.assertIsSuccess(flag, "删除该题目的所有标签失败");
    }

    /**
     *  批量新增记录
     *
     * @param programProblemId 题目id
     * @param tagIdList 标签数组id
     */
    @Override
    public int batchInsert(int programProblemId, Integer[] tagIdList) {
        programProblemTagMapper.batchInsert(programProblemId,tagIdList);
        //更新标签使用次数
        return programTagService.batchUpdateTagUsedTimes(tagIdList,true);
    }

}
