package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.controller.backstage.format.ProgramProblemWithTags;
import com.dcoj.dao.ProgramProblemMapper;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.ProgramTagEntity;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.service.ProgramProblemTagService;
import com.dcoj.service.TestCasesService;
import com.dcoj.util.WebUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<ProgramProblemWithTags> findAll(String query){
        List<ProgramProblemWithTags> programProblemWithTags = new ArrayList<>();
        List<ProgramProblemEntity> programProblemEntities = null;
        if(query!=null&&!"".equals(query) ){
            programProblemEntities = programProblemMapper.findAllByTitle(query);
        }else {
            programProblemEntities = programProblemMapper.findAll();
        }
        if(programProblemEntities!=null)
        {
            for(ProgramProblemEntity pg:programProblemEntities){
                ProgramProblemWithTags pgt = new ProgramProblemWithTags();
                pgt.setProgramProblemEntity(pg);
                pgt.setListTags(this.listProgramProblemTagsByPid(pg.getProgramProblemId()));
                programProblemWithTags.add(pgt);
            }
        }else{
            WebUtil.assertIsSuccess(false, "查询失败");
        }
        return  programProblemWithTags;
    }

    /**
     * 批量导入编程题目
     *
     * @param files 编程题模板文件
     * @return 结果
     */
    @Override
    public String importProgram(MultipartFile files) throws IOException {
        XSSFWorkbook workbook  = new XSSFWorkbook(files.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        //记录错误行数
        HashMap<String,String> errorMessage= new HashMap<String,String>();
        for(int i=2;i<sheet.getLastRowNum()+1;i++){
            XSSFRow row = sheet.getRow(i);
            int lastNum = row.getLastCellNum();
            if(lastNum <= 0) {
                break;
            }
            ProgramProblemEntity pg = new ProgramProblemEntity();
            if(row.getCell(1).getStringCellValue()!=null){
                System.out.println(row.getCell(1).getStringCellValue());
                pg.setDescription(JSONObject.parseObject(row.getCell(1).getStringCellValue()));
            }
            if(row.getCell(2).getNumericCellValue()>=0){
                pg.setDifficult((int)(row.getCell(2).getNumericCellValue()));
            }
            //根据输入题目的标题检查编程题是否存在，存在则跳过，不存在则插入
            boolean insertFlag = false;
            if(row.getCell(3).getStringCellValue()!=null){
                List<ProgramProblemEntity> listAll = programProblemMapper.findAll();
                for(ProgramProblemEntity pge : listAll){
                    System.out.println(pge.getTitle()+":"+row.getCell(3).getStringCellValue());
                    if(pge.getTitle()!=null){
                        if(pge.getTitle().replace(" ","").equals(row.getCell(3).getStringCellValue().replace(" ",""))){
                            errorMessage.put(row.getCell(0).getStringCellValue(),row.getCell(3).getStringCellValue());
                            insertFlag = true;
                        }
                    }
                }
            }
            if(insertFlag){
                continue;
            }else
            {
                pg.setTitle(row.getCell(3).getStringCellValue());
            }
            if(row.getCell(4).getStringCellValue()!=null){
                pg.setInputFormat(JSONObject.parseObject(row.getCell(4).getStringCellValue()));
            }
            if(row.getCell(5).getStringCellValue()!=null){
                pg.setOutputFormat(JSONObject.parseObject(row.getCell(5).getStringCellValue()));
            }
            if(row.getCell(6).getStringCellValue()!=null){
                pg.setSamples(JSONArray.parseArray(row.getCell(6).getStringCellValue()));
            }
            if(row.getCell(9).getNumericCellValue()>=0){
                pg.setRunTime((int)(row.getCell(9).getNumericCellValue()));
            }
            if(row.getCell(10).getNumericCellValue()>=0){
                pg.setMemory((int)(row.getCell(10).getNumericCellValue()));
            }
            boolean flag = programProblemMapper.save(pg) == 1;
            WebUtil.assertIsSuccess(flag, "题目添加失败");
            //检查题目标签是否已经存在，存在直接关联，否则新增后再进行关联
            if(row.getCell(7).getStringCellValue()!=null){
                JSONArray jsonArray = JSONArray.parseArray(row.getCell(7).getStringCellValue());
                for (int j=0;j<jsonArray.size();j++){
                    JSONObject jo = jsonArray.getJSONObject(j);
                    String tagName = jo.getString("name");
                    ProgramTagEntity programTagEntity = programTagService.getByTagName(tagName);
                    if(programTagEntity!=null){
                        programProblemTagService.save(pg.getProgramProblemId(),programTagEntity.getProgramTagId());
                    }else{
                        ProgramTagEntity newTag = new ProgramTagEntity();
                        newTag.setTagName(tagName);
                        programTagService.saveByEntity(newTag);
                        programProblemTagService.save(pg.getProgramProblemId(),newTag.getProgramTagId());
                    }
                }
            }
            //插入编程题目对应的测试用例
            if(row.getCell(8).getStringCellValue()!=null){
                JSONArray testCaseList = JSONArray.parseArray(row.getCell(8).getStringCellValue());
                for(int t=0;t<testCaseList.size();t++){
                    JSONObject tc = testCaseList.getJSONObject(t);
                    testCasesService.save(pg.getProgramProblemId(),tc.getString("input"),tc.getString("output"));
                }
            }
        }

        return "批量新增成功";
    }

}
