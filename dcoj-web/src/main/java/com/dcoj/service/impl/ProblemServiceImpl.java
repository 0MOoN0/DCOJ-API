package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.dao.ProblemMapper;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProblemService;
import com.dcoj.service.TagProblemService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WANGQING
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private TagProblemService tagProblemService;

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    @Override
    public int countProblems() {
        return problemMapper.countProblems();
    }

    /**
     * 根据题目类型统计题目数量
     *
     * @param type 所选类型
     * @return 根据题目类型返回该类型的题目数量
     */
    @Override
    public int countProblemsByType(int type) {
        return problemMapper.countProblemsByType(type);
    }

    /**
     * 删除一道题目
     *
     * @param pid 题目id
     */
    @Override
    //TODO : 03.28 WANGQING 题目删除必须和submissions等其他表关联，部分功能未完善，已写的功能已测试
    public void removeByPid(int pid) {
        WebUtil.assertNotNull(problemMapper.getById(pid),"题目不存在，删除失败");
//        // 检查题目是否有提交记录
//        int submissions = submissionService.countProblemSubmissions(pid);
//        if (submissions > 0) {
//            throw new WebErrorException("该题目已有人提交，无法删除");
//        }
//
//        // 检查题目是否有被比赛使用
//        int contestUsed = contestProblemService.countContestProblems(pid);
//        if (contestUsed > 0) {
//            throw new WebErrorException("该题目已被比赛调用，无法删除");
//        }
//
        List<Integer> tagList = tagProblemService.getTagsByPid(pid);
        WebUtil.assertIsSuccess(tagList.size()!=0,"该题目无标签，删除失败");
        // 删除该题目的所有标签
       tagProblemService.removeProblemAllTags(pid);
//        // 删除problem-moderator
//        if (problemModeratorService.countProblemModerators(pid) > 0) {
//            problemModeratorService.deleteModerators(pid);
//        }
//        // 删除problem-testCases
//        if (testCasesService.countProblemTestCases(pid) > 0) {
//            testCasesService.deleteProblemTestCases(pid);
//        }
        // 删除problem
        boolean flag = problemMapper.removeByPid(pid) == 1;
        WebUtil.assertIsSuccess(flag, "删除题目失败");
    }

    /**
     * 更新一道题目信息
     *
     * @param pid           要修改的题目id
     * @param newTags       更新后题目的标签
     * @param problemEntity 题目实体类对象
     */
    @Override
    //TODO:03.30 WANGQING 该方法能实现功能，但是方法不是很好，期待写出更好的方法优化
    @Transactional(rollbackFor = Exception.class)
    public void updateProblem(int pid, JSONArray newTags, ProblemEntity problemEntity) {
        WebUtil.assertNotNull(problemMapper.getById(pid),"该题目不存在，无法更新");
        WebUtil.assertNotNull(newTags, "标签集合为空，无法更新");
        // 存放新修改的标签id集合
        List<Integer> finalTags = new ArrayList<>(newTags.size());
        //将JSONArray里的元素取出并存到List<Integer>
        for (int i = 0; i < newTags.size(); i++) {
            // 从JSONArray取出tid
            int tid = newTags.getInteger(i);
            finalTags.add(tid);
        }
        //判断新修改的标签id集合是否为空
        WebUtil.assertIsSuccess(finalTags.size() != 0, "非法标签");
        // 删除题目原本的所有旧标签
        tagProblemService.removeProblemAllTags(pid);
        // 添加pid和tag之间的关联
        for (Integer tid : finalTags) {
            tagProblemService.save(pid, tid);
        }
        problemEntity.setPid(pid);
        boolean flag = problemMapper.updateProblem(problemEntity) == 1;
        WebUtil.assertIsSuccess(flag, "题目更新失败");
    }

    /**
     * 查询所有题目
     *
     * @return 包含所有题目的List集合
     */
    @Override
    public List<ProblemEntity> listAll() {
        return problemMapper.listAll();
    }

    /**
     * 根据题目类型查询题目
     *
     * @param type 所选题目类型
     * @return 包含该类型所有题目的List集合
     */
    @Override
    public List<ProblemEntity> listByType(int type) {
        return problemMapper.listByType(type);
    }

    /**
     * 通过编号查询题目
     *
     * @param pid 题目id
     * @return 题目实体类对象
     */
    @Override
    public ProblemEntity getById(int pid) {
        ProblemEntity problemEntity = problemMapper.getById(pid);
        WebUtil.assertNotNull(problemEntity, "不存在此题目");
        return problemEntity;
    }

    /**
     * 添加一道题目
     *
     * @param tags 题目标签
     * @param problemEntity 题目实体类对象
     * @return 题目id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(JSONArray tags, ProblemEntity problemEntity) {
        // 保存tag标签并且添加tag标签使用次数
        List<Integer> tagList = new ArrayList<>(tags.size());
        for (int i = 0; i < tags.size(); i++) {
            int tid = tags.getInteger(i);
            tagList.add(tid);
        }
        // 判断新修改的标签id集合是否为空
        WebUtil.assertIsSuccess(tagList.size() != 0, "标签非法");

        boolean flag = problemMapper.save(problemEntity) == 1;
        WebUtil.assertIsSuccess(flag, "题目添加失败");
        int pid = problemEntity.getPid();

        // 添加pid和tag之间的关联
        for (Integer tid : tagList) {
            tagProblemService.save(pid, tid);
        }
        return pid;
    }

    //TODO：03.30 WANGQING 跟判卷有关系的方法，未写

    /**
     * 根据判卷状态更新Problem
     *
     * @param pid    题目业务id
     * @param result 判卷结果
     */
    @Override
    public void updateProblemTimes(int pid, ResultEnum result) {

    }

    /**
     * 通过 pid 查询该题所有tag
     *
     * @param pid 题目id
     * @return 结果
     */
    @Override
    public List<Map<String, Object>> listProblemTagsByPid(int pid) {
        WebUtil.assertNotNull(problemMapper.getById(pid),"该题目不存在");
        return problemMapper.listProblemTagsByPid(pid);
    }
}
