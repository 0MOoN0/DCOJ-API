package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dcoj.dao.ExaminationMapper;
import com.dcoj.dao.ExaminationProblemMapper;
import com.dcoj.entity.ExaminationEntity;
import com.dcoj.entity.ExaminationProblemEntity;
import com.dcoj.service.ExaminationService;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zxw
 * @Desriiption: 试卷业务层实现类
 */
@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationMapper examinationMapper;

    @Autowired
    private ExaminationProblemMapper examinationProblemMapper;

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private ObjectProblemService objectProblemService;

    @Override
    public List<Map<String, Object>> listAll() {
        return examinationMapper.listAll();
    }

    @Override
    @Transactional
    public int removeByExamId(Integer examId) {
        ExaminationEntity ex = examinationMapper.selectByPrimaryKey(examId);
        WebUtil.assertNotNull(ex,"删除失败，不存在此试卷");
        int flag = examinationMapper.deleteByPrimaryKey(examId);
        //同时删除试卷----题目之间的关系
        examinationProblemMapper.removeByExamId(examId);
        return flag;
    }

    @Override
    @Transactional
    public int update(ExaminationEntity examinationEntity) {
        //先查询是否存在此试卷  若存在则更新，不存在则报异常
        Integer examId = examinationEntity.getExamId();
        examinationEntity.setExamId(examId);
        ExaminationEntity ex = examinationMapper.selectByPrimaryKey(examinationEntity.getExamId());
        WebUtil.assertNotNull(ex,"更新失败，不存在此试卷");

        //更新试卷所关联的试题
        //移除之前所关联的所有试题
        examinationProblemMapper.removeByExamId(examId);
        //关联客观题
        //添加试卷的时候同时添加题目
        List<ExaminationProblemEntity> examinationProblemEntityList = new ArrayList<>();
        //获取单选题和编程题数组
        List<Integer> singleArray = examinationEntity.getSingleProblemIdList();
        List<Integer> programArray = examinationEntity.getProgramProblemIdList();
        //单选题
        for(int i = 0 ; i < singleArray.size() ; i++){
            ExaminationProblemEntity examinationProblemEntity = new ExaminationProblemEntity();
            Integer pid = singleArray.get(i);
            examinationProblemEntity.setExamId(examId);
            examinationProblemEntity.setProblemId(pid);
            examinationProblemEntity.setProblemType(2);
            examinationProblemEntityList.add(examinationProblemEntity);
        }
        //编程题
        for(int i = 0 ; i < programArray.size() ; i++){
            ExaminationProblemEntity examinationProblemEntity = new ExaminationProblemEntity();
            Integer pid = programArray.get(i);
            examinationProblemEntity.setExamId(examId);
            examinationProblemEntity.setProblemId(pid);
            examinationProblemEntity.setProblemType(1);
            examinationProblemEntityList.add(examinationProblemEntity);
        }
        //批量插入数据到考试题目表
        examinationProblemMapper.saveAll(examinationProblemEntityList);

        return examinationMapper.updateByPrimaryKeySelective(examinationEntity);
    }

    @Override
    @Transactional
    public int save(ExaminationEntity examinationEntity) {
        //插入试卷类
        examinationMapper.insertSelective(examinationEntity);
        //获取新增成功的试卷ID
        Integer examId = examinationEntity.getExamId();
        //添加试卷的时候同时添加题目
        List<ExaminationProblemEntity> examinationProblemEntityList = new ArrayList<>();
        //获取单选题和编程题数组
        List<Integer> singleArray = examinationEntity.getSingleProblemIdList();
        List<Integer> programArray = examinationEntity.getProgramProblemIdList();
        //单选题
        for(int i = 0 ; i < singleArray.size() ; i++){
            ExaminationProblemEntity examinationProblemEntity = new ExaminationProblemEntity();
            Integer pid = singleArray.get(i);
            examinationProblemEntity.setExamId(examId);
            examinationProblemEntity.setProblemId(pid);
            examinationProblemEntity.setProblemType(2);
            examinationProblemEntityList.add(examinationProblemEntity);
        }
        //编程题
        for(int i = 0 ; i < programArray.size() ; i++){
            ExaminationProblemEntity examinationProblemEntity = new ExaminationProblemEntity();
            Integer pid = programArray.get(i);
            examinationProblemEntity.setExamId(examId);
            examinationProblemEntity.setProblemId(pid);
            examinationProblemEntity.setProblemType(1);
            examinationProblemEntityList.add(examinationProblemEntity);
        }


        //批量插入数据到考试题目表
        examinationProblemMapper.saveAll(examinationProblemEntityList);

        return examId;
    }

    @Override
    public ExaminationEntity listByExamId(Integer examId) {
        ExaminationEntity ex = examinationMapper.selectByPrimaryKey(examId);
        WebUtil.assertNotNull(ex,"查询失败，不存在此试卷");
        return ex;
    }
}
