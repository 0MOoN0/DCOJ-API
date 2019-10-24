package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.dao.ProgramProblemMapper;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.service.ProgramProblemTagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//TODO:04.26 WANGQING 编程题、客观题题目模块全部未加修改status方法

/**
 * 编程题 业务层实现
 *
 * @author WANGQING, Leon
 */
@Service
public class ProgramProblemServiceImpl implements ProgramProblemService {

    @Autowired
    private ProgramProblemMapper programProblemMapper;

    @Autowired
    private ProgramProblemTagService programProblemTagService;

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    @Override
    public int countProgramProblems() {
        return programProblemMapper.countProgramProblems();
    }

    /**
     * 删除一道题目
     *
     * @param programProblemId 题目id
     */
    @Override
    //TODO : 03.28 WANGQING 题目删除必须和submissions等其他表关联，部分功能未完善，已写的功能已测试
    public void removeByPrimaryKey(int programProblemId) {
        WebUtil.assertNotNull(programProblemMapper.getByPrimaryKey(programProblemId), "题目不存在，删除失败");

        List<Integer> tagList = programProblemTagService.getTagsByProgramProblemId(programProblemId);
        // 判断题目是否带有标签
        if (tagList != null && tagList.size() != 0) {
            // 删除该题目的所有标签
            programProblemTagService.removeProblemAllTags(programProblemId);
        }
        // 删除problem
        boolean flag = programProblemMapper.removeByPrimaryKey(programProblemId) == 1;
        WebUtil.assertIsSuccess(flag, "删除题目失败");
    }

    /**
     * 更新一道题目信息
     *
     * @param programProblemId     要修改的题目id
     * @param newTags              更新后题目的标签
     * @param programProblemEntity 题目实体类对象
     */
    @Override
    //TODO:03.30 WANGQING 该方法能实现功能，但是方法不是很好，期待写出更好的方法优化
    @Transactional(rollbackFor = Exception.class)
    public void updateProblemAndTags(Integer programProblemId, JSONArray newTags, ProgramProblemEntity programProblemEntity) {
        WebUtil.assertNotNull(programProblemMapper.getByPrimaryKey(programProblemId), "该题目不存在，无法更新");
        // 删除题目原本的所有旧标签
        programProblemTagService.removeProblemAllTags(programProblemId);
        // 判断题目添加的时候是否带有标签,有则添加
        if (newTags != null && newTags.size() != 0 && !newTags.getJSONObject(0).isEmpty()) {
            // 存放新修改的标签id集合
            List<Integer> finalTags = new ArrayList<>(newTags.size());
            //将JSONArray里的元素取出并存到List<Integer>
            for (int i = 0; i < newTags.size(); i++) {
                // 从JSONArray取出tid
                int tid = newTags.getJSONObject(i).getInteger("programTagId");
                finalTags.add(tid);
            }
            //判断新修改的标签id集合是否为空
            WebUtil.assertIsSuccess(finalTags.size() != 0, "非法标签");
            // 添加programProblem和objectTag之间的关联
            for (Integer programTagId : finalTags) {
                programProblemTagService.save(programProblemId, programTagId);
            }
        }
        programProblemEntity.setProgramProblemId(programProblemId);
        boolean flag = programProblemMapper.updateProgramProblem(programProblemEntity) == 1;
        WebUtil.assertIsSuccess(flag, "题目更新失败");
    }


    /**
     * 通过编号查询题目
     *
     * @param programProblemId 题目id
     * @return 题目实体类对象
     */
    @Override
    public ProgramProblemEntity getByPrimaryKey(int programProblemId) {
        ProgramProblemEntity programProblemEntity = programProblemMapper.getByPrimaryKey(programProblemId);
        WebUtil.assertNotNull(programProblemEntity, "不存在此题目");
        return programProblemEntity;
    }

    /**
     * 添加一道题目
     *
     * @param tags                 题目标签
     * @param programProblemEntity 题目实体类对象
     * @return 题目id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(JSONArray tags, ProgramProblemEntity programProblemEntity) {
        boolean flag = programProblemMapper.save(programProblemEntity) == 1;
        WebUtil.assertIsSuccess(flag, "题目添加失败");
        int programProblemId = programProblemEntity.getProgramProblemId();

        // 判断题目添加的时候是否带有标签
        if (tags != null && tags.size() != 0 && !tags.getJSONObject(0).isEmpty()) {
            // 保存tag标签并且添加tag标签使用次数
            List<Integer> tagList = new ArrayList<>(tags.size());
            for (int i = 0; i < tags.size(); i++) {
                int tid = tags.getJSONObject(i).getInteger("programTagId");
                tagList.add(tid);
            }
            // 判断新修改的标签id集合是否为空
            WebUtil.assertIsSuccess(tagList.size() != 0, "标签非法");
            // 添加pid和tag之间的关联
            for (Integer programTagId : tagList) {
                programProblemTagService.save(programProblemId, programTagId);
            }
        }
        return programProblemId;
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
        programProblemMapper.updateProblemTimes(pid, result.toString());
    }

    /**
     * 通过 pid 查询该题所有tag
     *
     * @param programProblemId 题目id
     * @return 结果
     */
    @Override
    public List<Map<String, Object>> listProgramProblemTagsByPid(int programProblemId) {
        WebUtil.assertNotNull(programProblemMapper.getByPrimaryKey(programProblemId), "该题目不存在");
        return programProblemMapper.listProgramProblemTagsByPrimaryKey(programProblemId);
    }

    /**
     * 查询所有编程题目
     *
     * @param list      标签列表
     * @param uid       用户id
     * @param difficult 难度
     * @param query     查询关键字
     * @return 结果
     */
    @Override
    public List<Map<String, Object>> listAll(List<Integer> list, Integer uid, Integer difficult, String query) {
        System.out.println("list:" + list + " uid:" + uid + " difficult:" + difficult + " query:" + query);
        return programProblemMapper.listAll(list, uid, difficult, query);
    }

    @Override
    public List<Map<String, Object>> listByExamIdAndType(int examId) {
        return programProblemMapper.listByExamIdAndType(examId);
    }

}
