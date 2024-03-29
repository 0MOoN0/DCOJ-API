package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.controller.backstage.format.ProgramProblemInsertEntity;
import com.dcoj.dao.ProgramProblemMapper;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.ProgramTagEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.service.ProgramProblemTagService;
import com.dcoj.service.TestCasesService;
import com.dcoj.util.POIUtil;
import com.dcoj.util.WebUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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

    @Autowired
    private ProgramTagServiceImpl programTagService;

    @Autowired
    private TestCasesService testCasesService;



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
     * 统计今天新增题目数量
     *
     * @return 返回今天新增题目数量
     */
    @Override
    public int countProgramProblemsToday() {
        return programProblemMapper.countProgramProblemsToday();
    }
    /**
     * 统计昨天新增题目数量
     *
     * @return 返回昨天新增题目数量
     */
    @Override
    public int countProgramProblemsYesterday() {
        return programProblemMapper.countProgramProblemsYesterday();
    }
    /**
     * 统计当前月份新增题目数量
     *
     * @return 返回当前月份新增题目数量
     */
    @Override
    public int countProgramProblemsMonth() {
        return programProblemMapper.countProgramProblemsMonth();
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
        if(flag){
            // 删除题目的测试用例
           testCasesService.deleteProblemTestCases(programProblemId);
        }
    }

    /**
     * 更新一道题目信息
     *
     * @param programProblemId     要修改的题目id
     * @param newTags              更新后题目的标签
     * @param programProblemEntity 题目实体类对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProblemAndTags(Integer programProblemId, JSONArray newTags, ProgramProblemEntity programProblemEntity) {
        WebUtil.assertNotNull(programProblemMapper.getByPrimaryKey(programProblemId), "该题目不存在，无法更新");
        // 删除题目原本的所有旧标签
        programProblemTagService.removeProblemAllTags(programProblemId);
        // 判断题目添加的时候是否带有标签,有则添加
        if (!newTags.isEmpty()) {
            // 存放新修改的标签id集合
            Integer[] tagIdList = new Integer[newTags.size()];
            tagIdList = newTags.toArray(tagIdList);
            //判断新修改的标签id集合是否为空
            WebUtil.assertIsSuccess(tagIdList.length != 0, "标签个数为0，无法获取");
            // 批量添加题目与标签关联信息
            int isSuccess = programProblemTagService.batchInsert(programProblemId,tagIdList);
            WebUtil.assertIsSuccess(isSuccess > 0, "标签批量新增失败");
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
        if (!tags.isEmpty()) {
            // 存放新修改的标签id集合
            Integer[] tagIdList = new Integer[tags.size()];
            tagIdList = tags.toArray(tagIdList);
            //判断新修改的标签id集合是否为空
            WebUtil.assertIsSuccess(tagIdList.length != 0, "标签个数为0，无法获取");
            // 批量添加题目与标签关联信息
            int isSuccess = programProblemTagService.batchInsert(programProblemId,tagIdList);
            WebUtil.assertIsSuccess(isSuccess > 0, "标签批量新增失败");
        }
        return programProblemId;
    }

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

    public List<ProgramProblemEntity> findAll(String query){
        List<ProgramProblemEntity> programProblemEntities = null;
        if(StringUtils.isNotBlank(query)){
            programProblemEntities = programProblemMapper.findAllByTitle(query);
        }else {
            programProblemEntities = programProblemMapper.findAll();
        }
        if(programProblemEntities!=null)
        {
            for(ProgramProblemEntity pg:programProblemEntities){
                List<Map<String, Object>> tags = this.listProgramProblemTagsByPid(pg.getProgramProblemId());
                if(tags != null && tags.size() > 0){
                    pg.setListTags(tags);
                }
            }
        }else{
            WebUtil.assertIsSuccess(false, "查询失败");
        }
        return  programProblemEntities;
    }

    /**
     * 批量导入编程题目
     *
     * @param files 编程题模板文件
     * @return 结果
     */
    @Override
    public String importProgram(MultipartFile files) throws IOException {
        //json 转换工具
        Gson gson = new Gson();
        //解析表格数据
        List<String[]> data = POIUtil.readExcel(files.getInputStream(), files.getOriginalFilename());
        //取出重复的题目
        List<ProgramProblemEntity> problemEntityList = programProblemMapper.findAll();
        //需要导入的题目
        List<String[]> insertData = new ArrayList<>();
        for(int i = 1; i < data.size(); i++){
            if(data.get(i).length < 3){
                continue;
            }
            boolean isExist = false;
            for (ProgramProblemEntity programProblemEntity: problemEntityList){
                if(programProblemEntity.getTitle().equals(data.get(i)[3])){
                    isExist = true;
                    break;
                }
            }
            //将不重复的题目加入集合
            if(!isExist){
                insertData.add(data.get(i));
            }
        }

        List<ProgramProblemInsertEntity> tagDataList = new ArrayList<>();
        List<ProgramProblemInsertEntity> testCaseDataList = new ArrayList<>();
/*        Map<Integer,String> tagDataList = new HashMap<>();
        Map<Integer,String> testCaseDataList = new HashMap<>();*/
        //正常内容从下标 1 开始
        if(insertData.size() > 0){
            for(int i = 0; i < insertData.size(); i++){
                int orderNumber =  Double.valueOf(insertData.get(i)[0]).intValue();
                //定义题目实体
                ProgramProblemEntity problemEntity = new ProgramProblemEntity();
                //题目描述
                JSONObject description = new JSONObject();
                try{
                    description = JSONObject.parseObject(insertData.get(i)[1]);
                }catch (Exception e){
                    boolean flag = false;
                    WebUtil.assertIsSuccess(flag, "第"+orderNumber+"行描述json格式有误！");
                }
                problemEntity.setDescription(description);
                //题目难度
                problemEntity.setDifficult(Double.valueOf(insertData.get(i)[2]).intValue());
                //题目标题
                problemEntity.setTitle(insertData.get(i)[3]);
                //输入规范
                JSONObject inputFormat = new JSONObject();
                try{
                    inputFormat = JSONObject.parseObject(insertData.get(i)[4]);
                }catch (Exception e){
                    boolean flag = false;
                    WebUtil.assertIsSuccess(flag, "第"+orderNumber+"行输入规范json格式有误！");
                }
                problemEntity.setInputFormat(inputFormat);
                //输出规范
                JSONObject outputFormat = new JSONObject();
                try{
                    outputFormat = JSONObject.parseObject(insertData.get(i)[5]);
                }catch (Exception e){
                    boolean flag = false;
                    WebUtil.assertIsSuccess(flag, "第"+orderNumber+"行输出规范json格式有误！");
                }
                problemEntity.setOutputFormat(outputFormat);
                //样例
                JSONArray samples = new JSONArray();
                try{
                    samples = JSONArray.parseArray(insertData.get(i)[6]);
                }catch (Exception e){
                    boolean flag = false;
                    WebUtil.assertIsSuccess(flag, "第"+orderNumber+"行样例json格式有误！");
                }
                problemEntity.setSamples(samples);
                //运行时间
                problemEntity.setRunTime(Double.valueOf(insertData.get(i)[9]).intValue());
                //运行内存
                problemEntity.setMemory(Double.valueOf(insertData.get(i)[10]).intValue());
                //插入题目
                programProblemMapper.save(problemEntity);

                if(problemEntity.getProgramProblemId() != null){
                    //题目标签  将需要插入的题目ID与相应的标签存入集合
                    ProgramProblemInsertEntity tagEntity = new ProgramProblemInsertEntity();
                    tagEntity.setPid(problemEntity.getProgramProblemId());
                    tagEntity.setOrderId(orderNumber);
                    tagEntity.setContent(insertData.get(i)[7]);
                    tagDataList.add(tagEntity);
                    //测试用例  将需要插入的题目ID与相应的测试用例存入集合
                    ProgramProblemInsertEntity testCaseEntity = new ProgramProblemInsertEntity();
                    testCaseEntity.setPid(problemEntity.getProgramProblemId());
                    testCaseEntity.setOrderId(orderNumber);
                    testCaseEntity.setContent(insertData.get(i)[8]);
                    testCaseDataList.add(testCaseEntity);
                }

            }
        }

        //插入题目标签
        if(tagDataList.size() > 0) {
            //Iterator<Map.Entry<Integer,String>> tagEntry = tagDataList.entrySet().iterator();
            for (ProgramProblemInsertEntity tags:tagDataList){
                //题目ID
                Integer problemId = tags.getPid();
                //题目所属标签ID集合
                List<Integer> tagIdList = new ArrayList<>();

                //处理JSON标签字符串
                if(tags.getContent() != null){
                    JSONArray tagArray = gson.fromJson(tags.getContent(),JSONArray.class);
                    for(int i = 0; i < tagArray.size(); i++){
                        JSONObject tagObject = new JSONObject();
                        try{
                            tagObject = gson.fromJson(tagArray.get(i).toString(),JSONObject.class);
                        }catch (Exception e){
                            boolean flag = false;
                            WebUtil.assertIsSuccess(flag, "第"+tags.getOrderId()+"行标签格式有误！");
                        }
                        if(tagObject.get("name") != null){
                            //判断该标签是否存在，存在则加入ID集合，否则新增该标签，获取新增后的ID，加入集合
                            ProgramTagEntity programTagEntity = programTagService.getByTagName(tagObject.get("name").toString());
                            if(programTagEntity == null){
                                //标签不存在，新增标签，加入集合
                                programTagEntity = new ProgramTagEntity();
                                programTagEntity.setTagName(tagObject.get("name").toString());
                                programTagService.saveByEntity(programTagEntity);
                                System.out.println("********************* : " + programTagEntity.getProgramTagId());
                            }
                            if(programTagEntity.getProgramTagId() != null){
                                tagIdList.add(programTagEntity.getProgramTagId());
                            }
                        }
                    }
                }
                //批量插入题目标签
                if(tagIdList.size() > 0){
                    Integer[] tagIdArray = tagIdList.toArray(new Integer[tagIdList.size()]);
                    programProblemTagService.batchInsert(problemId,tagIdArray);
                }
            }
        }


        //插入题目测试用例
        if (testCaseDataList.size() > 0) {
            for(ProgramProblemInsertEntity testCase:testCaseDataList) {
                //题目ID
                Integer problemId = testCase.getPid();
                //处理JSON标签字符串
                if(testCase.getContent() != null){
                    JsonParser parser = new JsonParser();
                    JsonArray testCaseArray = new JsonArray();
                    try {
                        testCaseArray = parser.parse(testCase.getContent()).getAsJsonArray();
                    }catch (Exception e){
                        boolean flag = false;
                        WebUtil.assertIsSuccess(flag, "第"+testCase.getOrderId()+"行测试用例格式有误！");
                    }
                    for(int i = 0; i < testCaseArray.size(); i++){
                        JSONObject testCaseObject = new JSONObject();// JSONObject.parseObject(testCaseArray.get(i).toString().replaceAll("=",":"));
                        try {
                            testCaseObject = gson.fromJson(testCaseArray.get(i),JSONObject.class);
                        }catch (Exception e){
                            boolean flag = false;
                            WebUtil.assertIsSuccess(flag, "第"+testCase.getOrderId()+"行,第"+(i+1)+"个测试用例格式有误！");
                        }
                        //如果测试用例内容不完整，则不插入，继续循环
                        if(testCaseObject.get("input") == null || testCaseObject.get("output") == null){
                            System.out.println("1");
                            continue;
                        }
                        //新增测试用例
                        String inputString = testCaseObject.get("input").toString().replaceAll(".0","");
                        String outputString = testCaseObject.get("output").toString().replaceAll(".0","");
                        testCasesService.save(problemId,inputString,outputString);
                    }
                }

            }
        }

        return "批量新增成功";
    }

}
