package com.dcoj.service.impl;

import com.dcoj.dao.SubmissionMapper;
import com.dcoj.entity.SubmissionEntity;
import com.dcoj.entity.SubmissionEntityExample;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.SubmissionService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author Leon
 */
@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionMapper submissionMapper;

    /**
     * 保存提交
     *
     * @param uid        用户ID
     * @param pid        题目ID
     * @param sourceCode 提交的源码
     * @param lang       提交使用的语言
     * @param usingTime       此次判卷使用的平均时间
     * @param memory     此次判卷使用的平均内存
     * @param status     判卷结果
     */
    @Override
    public void save(int uid, int pid, int sourceCode, LanguageEnum lang, double usingTime, int memory, ResultEnum status) {
        SubmissionEntity entity = new SubmissionEntity();
        entity.setUid(uid);
        entity.setPid(pid);
        entity.setSourceCode(sourceCode);
        entity.setLang(lang);
        entity.setUsingTime(new BigDecimal(usingTime));
        entity.setMemory(memory);
        entity.setStatus(status);
        entity.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        boolean flag = submissionMapper.insert(entity)==1;
        WebUtil.assertIsSuccess(flag, "代码提交记录保存失败");
    }

    /**
     * 根据题目ID查询该题目的Submission数量
     *
     * @param pid 题目ID
     * @return 大于等于零的正整数，表示此题目ID的Sbumission数量
     */
    @Override
    public int countProblemSubmissions(int pid) {
        SubmissionEntityExample example = new SubmissionEntityExample();
        example.createCriteria().andPidEqualTo(pid);
        int count = (int) submissionMapper.countByExample(example);
        return count;
    }

    @Override
    public List<Map<String, Object>> listProblemSubmissions(Integer pid, Integer cid) {
        return null;
    }
}
