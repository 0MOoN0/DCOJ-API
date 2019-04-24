package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.dao.ObjectProblemMapper;
import com.dcoj.entity.ObjectProblemEntity;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ObjectProblemTagService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 客观题 业务层实现
 *
 * @author WANGQING
 */
@Service
public class ObjectProblemServiceImpl implements ObjectProblemService {

    @Autowired
    private ObjectProblemMapper objectProblemMapper;

    @Autowired
    private ObjectProblemTagService objectProblemTagService;

    /**
     * 删除一道题目
     *
     * @param objectProblemId 题目id
     */
    @Override
    //TODO : 04.23 WANGQING 题目删除必须和submissions等其他表关联，部分功能未完善，已写的功能已测试
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Integer objectProblemId) {
        WebUtil.assertNotNull(objectProblemMapper.getByPrimaryKey(objectProblemId),"题目不存在，删除失败");

//        // 检查题目是否有提交记录
//        int submissions = submissionService.countProblemSubmissions(pid);
//        if (submissions > 0) {
//            throw new WebErrorException("该题目已有人提交，无法删除");
//        }
        List<Integer> tagList = objectProblemTagService.getTagsByObjectProblemId(objectProblemId);
        // 判断题目是否带有标签
        if (tagList != null && tagList.size() != 0){
            // 删除该题目的所有标签
            objectProblemTagService.removeProblemAllTags(objectProblemId);
        }
        // 删除problem
        boolean flag = objectProblemMapper.removeByPrimaryKey(objectProblemId) == 1;
        WebUtil.assertIsSuccess(flag, "删除题目失败");
    }

    /**
     * 插入一道题目
     *
     * @param tags   题目id集合
     * @param record 客观题实体类对象
     * @return 返回题目id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(JSONArray tags, ObjectProblemEntity record) {
        boolean flag = objectProblemMapper.insertSelective(record) == 1;
        WebUtil.assertIsSuccess(flag, "题目添加失败");
        int objectProblemId = record.getObjectProblemId();

        // 判断题目添加的时候是否带有标签
        if (tags != null && tags.size() != 0 ){
            // 保存tag标签并且添加tag标签使用次数
            List<Integer> tagList = new ArrayList<>(tags.size());
            for (int i = 0; i < tags.size(); i++) {
                int tid = tags.getInteger(i);
                tagList.add(tid);
            }
            // 判断新修改的标签id集合是否为空
            WebUtil.assertIsSuccess(tagList.size() != 0, "标签非法");

            // 添加pid和tag之间的关联
            for (Integer objectTagId : tagList) {
                objectProblemTagService.save(objectProblemId, objectTagId);
            }
        }
        return objectProblemId;
    }

    /**
     * 通过题目objectProblemId查找题目
     *
     * @param objectProblemId 题目id
     * @return 客观题实体类对象
     */
    @Override
    public ObjectProblemEntity getByPrimaryKey(Integer objectProblemId) {
        ObjectProblemEntity objectProblemEntity = objectProblemMapper.getByPrimaryKey(objectProblemId);
        WebUtil.assertNotNull(objectProblemEntity, "不存在此题目");
        return objectProblemEntity;
    }

    /**
     * 更新一道题目和题目关联的标签
     *
     * @param newTags 更新的标签id集合
     * @param record  客观题实体类对象
     */
    @Override
    //TODO:04.23 WANGQING 该方法能实现功能，但是方法不是很好，期待写出更好的方法优化
    @Transactional(rollbackFor = Exception.class)
    public void updateProblemAndTags(Integer objectProblemId,JSONArray newTags, ObjectProblemEntity record) {
        WebUtil.assertNotNull(objectProblemMapper.getByPrimaryKey(objectProblemId),"该题目不存在，无法更新");
        // 删除题目原本的所有旧标签
        objectProblemTagService.removeProblemAllTags(objectProblemId);
        // 判断题目添加的时候是否带有标签,有则添加
        if (newTags != null && newTags.size() != 0 && !newTags.getJSONObject(0).isEmpty()){
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
            // 添加pid和tag之间的关联
            for (Integer objectTagId : finalTags) {
                objectProblemTagService.save(objectProblemId, objectTagId);
            }
        }
        record.setObjectProblemId(objectProblemId);
        boolean flag = objectProblemMapper.updateByPrimaryKeySelective(record) == 1;
        WebUtil.assertIsSuccess(flag, "题目更新失败");
    }

    /**
     * 统计题目数量
     *
     * @return 返回题目总数量
     */
    @Override
    public int countObjectProblems() {
        return objectProblemMapper.countObjectProblems();
    }

    /**
     * 根据题目类型统计题目数量
     *
     * @param type 题目类型
     * @return 根据题目类型返回该类型的题目数量
     */
    @Override
    public int countObjectProblemsByType(Integer type) {
        return objectProblemMapper.countObjectProblemsByType(type);
    }

    /**
     * 通过 objectProblemId 查询该题所有tag
     *
     * @param objectProblemId 题目id
     * @return 结果
     */
    @Override
    public List<Map<String, Object>> listObjectProblemTagsByPrimaryKey(Integer objectProblemId) {
        WebUtil.assertNotNull(objectProblemMapper.getByPrimaryKey(objectProblemId),"该题目不存在");
        return objectProblemMapper.listObjectProblemTagsByPrimaryKey(objectProblemId);
    }

//    /**
//     * 查询所有题目
//     *
//     * @return 包含所有题目的List集合
//     */
//    @Override
//    public List<ObjectProblemEntity> listAll() {
//        return objectProblemMapper.listAll();
//    }

    /**
     * 判断用户提交的答案是否正确
     *
     * @param objectProblemId 客观题id
     * @param userAnswer      用户提交的答案
     * @return 返回值为1时，答案正确，为0则答案错误
     */
    @Override
    public int judgeObjectProblem(Integer objectProblemId, String userAnswer) {
        ObjectProblemEntity objectProblemEntity = objectProblemMapper.getByPrimaryKey(objectProblemId);
        WebUtil.assertNotNull(objectProblemEntity,"该题目不存在");
        // 判断答案，答案正确，则返回1，否则返回1
        if (objectProblemEntity.getAnswer().equals(userAnswer)){
            return 1;
        }
        return 0;
    }

    /**
     * 根据题目状态查询所有题目
     *
     * @param status 题目状态
     * @return 包含该状态下的所有题目的List集合
     */
    @Override
    public List<ObjectProblemEntity> listByStatus(Integer status) {
        return objectProblemMapper.listByStatus(status);
    }

    /**
     * 根据题目状态统计题目数量
     *
     * @param status
     * @return 根据题目状态返回该类型的题目数量
     */
    @Override
    public int countObjectProblemsByStatus(Integer status) {
        return objectProblemMapper.countObjectProblemsByStatus(status);
    }

    /**
     * 根据状态、类型统计客观题数量(不加参数则查询全部)
     *
     * @param status 题目状态
     * @param type   题目类型
     * @return 根据题目状态、题目类型返回该类型的题目数量
     */
    @Override
    public List<ObjectProblemEntity> listAll(Integer status, Integer type) {
        return objectProblemMapper.listAll(status,type);
    }
}
