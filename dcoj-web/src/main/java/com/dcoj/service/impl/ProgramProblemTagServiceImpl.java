package com.dcoj.service.impl;

import com.dcoj.dao.ProgramProblemTagMapper;
import com.dcoj.entity.ProgramProblemTagEntity;
import com.dcoj.service.ProgramTagService;
import com.dcoj.service.ProgramProblemTagService;
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
public class ProgramProblemTagServiceImpl implements ProgramProblemTagService {

    @Autowired
    private ProgramProblemTagMapper programProblemTagMapper;

    @Autowired
    private ProgramTagService programTagService;

    /**
     * 为某道题添加一个或者多个标签
     *
     * @param programProblemId 题目id
     * @param programTagId 标签id
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
     * 通过pid 获取 TagProblemEntity对象
     *
     * @param pid 题目id
     * @return 返回 ProgramProblemTagEntity 实体类对象
     */
//    @Override
//    public ProgramProblemTagEntity getByPid(int pid) {
//        ProgramProblemTagEntity programProblemTagEntity = programProblemTagMapper.getByPid(pid);
//        WebUtil.assertNotNull(programProblemTagEntity, "不存在此题目标签");
//        return programProblemTagEntity;
//    }

    /**
     * 通过 programTagId 删除TagProblemEntity对象
     *
     * @param programTagId 题目id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeProblemAllTags(int programTagId) {
        List<Integer> tagList = programProblemTagMapper.getTagsByProgramProblemId(programTagId);
//        WebUtil.assertIsSuccess(tagList.size()!=0,"该题目无标签，删除失败");
        for (int tid : tagList) {
            programTagService.updateTagUsedTimes(tid, false);
        }
        // programProblemTagMapper.removeProblemAllTags(pid)返回值不为0时，删除成功，为0则删除失败
        boolean flag = programProblemTagMapper.removeProblemAllTags(programTagId) != 0;
        WebUtil.assertIsSuccess(flag, "删除该题目的所有标签失败");
    }

    /**
     * 通过 pid 和 tid 删除一条记录
     *
     * @param pid 题目id
     * @param tid 标签id
     */
//    @Override
//    @Transactional(rollbackFor=Exception.class)
//    public void removeProblemTag(int pid, int tid) {
//        boolean flag = programProblemTagMapper.removeProblemTag(pid, tid) == 1;
//        WebUtil.assertIsSuccess(flag, "删除该题目的一个标签失败");
//        programTagService.updateTagUsedTimes(tid, false);
//    }
}
