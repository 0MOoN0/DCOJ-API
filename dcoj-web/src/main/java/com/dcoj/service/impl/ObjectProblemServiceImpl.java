package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.dao.ObjectProblemCateMapper;
import com.dcoj.dao.ObjectProblemMapper;
import com.dcoj.dao.ObjectSubmissionMapper;
import com.dcoj.entity.ObjectProblemCateEntity;
import com.dcoj.entity.ObjectProblemEntity;
import com.dcoj.entity.ObjectTagEntity;
import com.dcoj.entity.SysCate;
import com.dcoj.handler.ParamException;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ObjectProblemTagService;
import com.dcoj.service.ObjectTagService;
import com.dcoj.service.SysCateService;
import com.dcoj.util.POIUtil;
import com.dcoj.util.WebUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ObjectProblemCateMapper objectProblemCateMapper;

    @Autowired
    private ObjectSubmissionMapper objectSubmissionMapper;

    @Autowired
    private SysCateService sysCateService;

    @Autowired
    private ObjectTagService objectTagService;

    /**
     * 删除一道题目
     *
     * @param objectProblemId 题目id
     */
    @Override
    //TODO : 04.23 WANGQING 题目删除必须和submissions等其他表关联，部分功能未完善，已写的功能已测试
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Integer objectProblemId) {
        WebUtil.assertNotNull(objectProblemMapper.getByPrimaryKey(objectProblemId), "题目不存在，删除失败");

        List<Integer> tagList = objectProblemTagService.getTagsByObjectProblemId(objectProblemId);
        // 判断题目是否带有标签
        if (tagList != null && tagList.size() != 0) {
            // 删除该题目的所有标签
            objectProblemTagService.removeProblemAllTags(objectProblemId);
        }

        //关联删除提交详情与类别信息
        objectSubmissionMapper.deleteByObjectProblemId(objectProblemId);
        objectProblemCateMapper.deleteByObjectProblemId(objectProblemId);

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

        ObjectProblemCateEntity objectProblemCateEntity = new ObjectProblemCateEntity();
        boolean flag = objectProblemMapper.insertSelective(record) == 1;
        WebUtil.assertIsSuccess(flag, "题目添加失败");
        int objectProblemId = record.getObjectProblemId();

        // 判断题目添加的时候是否带有标签
        if (tags != null && tags.size() != 0 && !tags.getJSONObject(0).isEmpty()) {
            // 保存tag标签并且添加tag标签使用次数
            List<Integer> tagList = new ArrayList<>(tags.size());
            for (int i = 0; i < tags.size(); i++) {
                int tid = tags.getJSONObject(i).getInteger("objectTagId");
                tagList.add(tid);
            }
            // 判断新修改的标签id集合是否为空
            WebUtil.assertIsSuccess(tagList.size() != 0, "标签非法");

            // 添加pid和tag之间的关联
            for (Integer objectTagId : tagList) {
                objectProblemTagService.save(objectProblemId, objectTagId);
            }
        }

        //添加类别
        if(record.getCateId() != null){
            objectProblemCateEntity.setObjectProblemId(record.getObjectProblemId());
            objectProblemCateEntity.setCateId(record.getCateId());
            objectProblemCateMapper.insertSelective(objectProblemCateEntity);
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
    public void updateProblemAndTags(Integer objectProblemId, JSONArray newTags, ObjectProblemEntity record) {

        ObjectProblemCateEntity objectProblemCateEntity = new ObjectProblemCateEntity();
        WebUtil.assertNotNull(objectProblemMapper.getByPrimaryKey(objectProblemId), "该题目不存在，无法更新");
        // 删除题目原本的所有旧标签
        objectProblemTagService.removeProblemAllTags(objectProblemId);
        // 判断题目添加的时候是否带有标签,有则添加
        if (newTags != null && newTags.size() != 0 && !newTags.getJSONObject(0).isEmpty()) {
            // 存放新修改的标签id集合
            List<Integer> finalTags = new ArrayList<>(newTags.size());
            //将JSONArray里的元素取出并存到List<Integer>
            for (int i = 0; i < newTags.size(); i++) {
                // 从JSONArray取出tid
                int tid = newTags.getJSONObject(i).getInteger("objectTagId");
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
        //添加类别
        if(record.getCateId() != null){
            //删除前类别
            objectProblemCateMapper.deleteByObjectProblemId(objectProblemId);
            //更新类别
            objectProblemCateEntity.setObjectProblemId(record.getObjectProblemId());
            objectProblemCateEntity.setCateId(record.getCateId());
            objectProblemCateMapper.insertSelective(objectProblemCateEntity);
        }
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
     * 统计今天新增题目数量
     *
     * @return 返回今天新增题目数量
     */
    @Override
    public int countObjectProblemsToday() {
        return  objectProblemMapper.countObjectProblemsToday();
    }
    /**
     * 统计昨天新增题目数量
     *
     * @return 返回昨天新增题目数量
     */
    @Override
    public int countObjectProblemsYesterday() {
        return objectProblemMapper.countObjectProblemsYesterday();
    }
    /**
     * 统计当前月份新增题目数量
     *
     * @return 返回当前月份新增题目数量
     */
    @Override
    public int countObjectProblemsMonth() {
        return objectProblemMapper.countObjectProblemsMonth();
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
        WebUtil.assertNotNull(objectProblemMapper.getByPrimaryKey(objectProblemId), "该题目不存在");
        return objectProblemMapper.listObjectProblemTagsByPrimaryKey(objectProblemId);
    }

    /**
     * 判断用户提交的答案是否正确
     *
     * @param objectProblemId 客观题id
     * @param userAnswer      用户提交的答案
     * @return 返回值为1时，答案正确，为0则答案错误
     */
    @Override
    public Integer judgeObjectProblem(Integer objectProblemId, String userAnswer) {
        ObjectProblemEntity objectProblemEntity = objectProblemMapper.getByPrimaryKey(objectProblemId);
        WebUtil.assertNotNull(objectProblemEntity, "该题目不存在");
        // 判断答案，答案正确，则返回1，否则返回0
        if (objectProblemEntity.getAnswer().equals(userAnswer)) {
            return 1;
        }
        return 0;
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
     * 查询所有编程题目
     *
     * @param list  标签列表
     * @param uid   用户id
     * @param query 查询关键字
     * @param type 题目类型
     * @return 结果
     */
    @Override
    public List<Map<String, Object>> listAll(List<Integer> list, Integer uid, String query,Integer type) {
        List<Map<String, Object>> objectProlemlist = null;
        if(null == type || 0 == type || 1 == type || 2 == type){
            objectProlemlist = objectProblemMapper.listAll(list,uid,query,type);
            if(objectProlemlist != null){
                for(Map<String, Object> objectProblem : objectProlemlist){
                    Map<String, Object> problemwithTags = objectProblem;
                    int proId = Integer.valueOf(objectProblem.get("object_problem_id").toString());
                    List<Integer> tagsId = objectProblemTagService.getTagsByObjectProblemId(proId);
                    if(tagsId.size() > 0){
                        List<ObjectTagEntity> objectTagEntities = new ArrayList<>();
                        for(int tagId : tagsId){
                            ObjectTagEntity objectTagEntity =objectTagService.getByPrimaryKey(tagId);
                            objectTagEntities.add(objectTagEntity);
                        }
                        problemwithTags.put("listTags",objectTagEntities);
                    }
                }
            }
        }else{
            throw new ParamException("题目类型不存在！");
        }
        return objectProlemlist;

    }

    /**
     * 更新题目次数内容
     * @param objectProblemId       题目ID
     * @param status                做题状态
     * @return
     */
    @Override
    public int updateProblemTimesByUidPid(Integer objectProblemId, Byte status) {
        return objectProblemMapper.updateProblemTimes(objectProblemId, status);
    }

    @Override
    public List<Map<String, Object>> listByExamIdAndType(int examId) {
        return objectProblemMapper.listByExamIdAndType(examId);
    }

    @Override
    public List<Map<String, Object>> listAllByCateId(int cateId) {

        SysCate sysCate = sysCateService.getById(cateId);
        WebUtil.assertNotNull(sysCate, "该类别不存在");
        return objectProblemMapper.listAllByCateId(cateId);
    }

    @Override
    public List<Map<String, Object>> findAllNotByPage(String query) {
        return objectProblemMapper.listAllByQuery(query);
    }

    @Override
    public String importObjectProblem(MultipartFile files) throws IOException {
        //json 转换工具
        Gson gson = new Gson();
        //解析表格数据
        List<String[]> data = POIUtil.readExcel(files.getInputStream(), files.getOriginalFilename());
        //取出重复的题目
        List<Map<String, Object>> lists = objectProblemMapper.listAll(null,null,null,null);
        //需要导入的题目
        List<String[]> insertData = new ArrayList<>();

        Map<Integer,String[]> tagLists = new HashMap<>();
        for(int i = 1; i < data.size(); i++) {
            if (data.get(i).length < 3) {
                continue;
            }
            boolean isExist = false;
            for (Map<String, Object> objectMap : lists) {
                if (objectMap.get("description").toString().replaceAll(" ","").equals(data.get(i)[1].replaceAll(" ",""))) {
                    isExist = true;
                    break;
                }
            }
            //将不重复的题目加入集合
            if (!isExist) {
                insertData.add(data.get(i));
            }
        }
        //正常内容从下标 1 开始
        if(insertData.size() > 0){
            for(int i = 0; i < insertData.size(); i++){
                int orderNumber =  Double.valueOf(insertData.get(i)[0]).intValue();
                ObjectProblemEntity objectProblemEntity = new ObjectProblemEntity();
                //题目描述
                JSONObject description = new JSONObject();
                try{
                    description = JSONObject.parseObject(insertData.get(i)[1]);
                }catch (Exception e){
                    boolean flag = false;
                    WebUtil.assertIsSuccess(flag, "第"+orderNumber+"行描述json格式有误！");
                }
                objectProblemEntity.setDescription(description);
                //题目答案
                objectProblemEntity.setAnswer(insertData.get(i)[2]);
                //题目类型
                double proTypedouble = Double.parseDouble(insertData.get(i)[3]);
                int proType = (int)proTypedouble;
                objectProblemEntity.setType(proType);
                objectProblemMapper.insertSelective(objectProblemEntity);
                //题目标签
                String objectTag = insertData.get(i)[4];
                String[] tagList = objectTag.split(",");
                if(objectProblemEntity.getObjectProblemId() != null ){
                    tagLists.put(objectProblemEntity.getObjectProblemId(),tagList);
                }
            }
        }
        //为题目和标签建立关联
        if(tagLists.size()>0){
            for(Map.Entry<Integer, String[]> entry:tagLists.entrySet()){
                Integer proId = entry.getKey();
                String[] tags = entry.getValue();
                for(int j = 0;j<tags.length;j++){
                    System.out.println(tags[j]);
                    ObjectTagEntity objectTagEntity = objectTagService.getByTagName(tags[j]);
                    if(objectTagEntity!=null){
                        objectProblemTagService.save(proId,objectTagEntity.getObjectTagId());
                    }else{
                        int objectTagId = objectTagService.save(tags[j]);
                        objectProblemTagService.save(proId,objectTagId);
                    }
                }
            }
        }
        return "批量新增成功";
    }

}

