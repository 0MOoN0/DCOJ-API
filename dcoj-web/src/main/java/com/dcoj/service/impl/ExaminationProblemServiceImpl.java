package com.dcoj.service.impl;

import com.dcoj.dao.ExaminationProblemMapper;
import com.dcoj.entity.ExaminationProblemEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ExaminationProblemService;
import com.dcoj.util.WebUtil;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Leon
 */
@Service
public class ExaminationProblemServiceImpl implements ExaminationProblemService {

    @Autowired
    private ExaminationProblemMapper examinationProblemMapper;

    /**
     * 根据试卷ID获取相关试卷问题信息
     *
     * @param examId 试卷ID
     * @return 返回Map类型，key是试卷问题的位置
     */
    @Override
    public Map<Integer, ExaminationProblemEntity> listByExamId(Integer examId) {
        return examinationProblemMapper.listByExamId(examId);
    }

    @Override
    public int saveAll(List<ExaminationProblemEntity> examinationProblemEntityList) {
        return examinationProblemMapper.saveAll(examinationProblemEntityList);
    }

    public int updateByLocateSelective(ExaminationProblemEntity examinationProblemEntity){
        ExaminationProblemEntity ex = examinationProblemMapper.listByExamProblemLocate(examinationProblemEntity.getExamProblemLocate());
        Preconditions.checkNotNull(ex, "待更新的关系不存在，无法删除");
        return examinationProblemMapper.updateByLocateSelective(examinationProblemEntity);
    }

    @Override
    public List<Map<String, Object>> listAll() {
        return examinationProblemMapper.listAll();
    }

    @Override
    public ExaminationProblemEntity listByExamProblemLocate(Integer examProblemLocate) {
        return examinationProblemMapper.listByExamProblemLocate(examProblemLocate);
    }

    @Override
    public void removeByExamProblemLocate(Integer examProblemLocate) {
        ExaminationProblemEntity ex = examinationProblemMapper.listByExamProblemLocate(examProblemLocate);
        Preconditions.checkNotNull(ex, "待删除的关系不存在，无法删除");
        examinationProblemMapper.removeByExamProblemLocate(examProblemLocate);
    }
}
