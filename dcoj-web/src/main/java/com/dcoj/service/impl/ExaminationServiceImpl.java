package com.dcoj.service.impl;

import com.dcoj.dao.ExaminationMapper;
import com.dcoj.dao.ExaminationProblemMapper;
import com.dcoj.entity.ExaminationEntity;
import com.dcoj.service.ExaminationService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int update(Integer examId,ExaminationEntity examinationEntity) {
        //先查询是否存在此试卷  若存在则更新，不存在则报异常
        examinationEntity.setExamId(examId);
        ExaminationEntity ex = examinationMapper.selectByPrimaryKey(examId);
        WebUtil.assertNotNull(ex,"更新失败，不存在此试卷");
        return examinationMapper.updateByPrimaryKeySelective(examinationEntity);
    }

    @Override
    @Transactional
    public int save(ExaminationEntity examinationEntity) {
        examinationMapper.insertSelective(examinationEntity);
        int examId = examinationEntity.getExamId();
        return examId;
    }

    @Override
    public ExaminationEntity listByExamId(Integer examId) {
        ExaminationEntity ex = examinationMapper.selectByPrimaryKey(examId);
        WebUtil.assertNotNull(ex,"查询失败，不存在此试卷");
        return ex;
    }
}
