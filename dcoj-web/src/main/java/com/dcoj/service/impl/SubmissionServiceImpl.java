package com.dcoj.service.impl;

import com.dcoj.dao.ProblemUserMapper;
import com.dcoj.dao.SubmissionMapper;
import com.dcoj.entity.ProblemUserEntityExample;
import com.dcoj.entity.SubmissionEntity;
import com.dcoj.entity.SubmissionEntityExample;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.ProblemUserService;
import com.dcoj.service.SubmissionService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.PageHelper;
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

    @Autowired
    private ProblemUserMapper problemUserMapper;

    /**
     * 保存用户提交，包括试卷提交、单题提交
     *
     * @param uid      用户ID
     * @param pid        题目ID
     * @param eid        试卷ID
     * @param gid        用户组ID
     * @param sourceCode 判卷源码
     * @param lang       判卷语言
     * @param usingTime       使用的时间
     * @param memory     使用的内存
     * @param status     判卷结果
     */
    @Override
    public void save(int uid, int pid, int eid, int gid, int sourceCode, LanguageEnum lang, double usingTime, int memory, ResultEnum status) {
        SubmissionEntity entity = new SubmissionEntity();
        entity.setUid(uid);
        entity.setPid(pid);
        entity.setEid(eid);
        entity.setGid(gid);
        entity.setSourceCode(sourceCode);
        entity.setLang(lang);
        entity.setUsingTime(new BigDecimal(usingTime));
        entity.setMemory(memory);
        entity.setStatus(status);
        entity.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        boolean flag = submissionMapper.insertSelective(entity)==1;
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

    /**
     * 查询用户当前题目的单题提交
     * 或者所有用户当前题目的单题提交
     *
     * @param uid      用户ID，如果为零则查询所有用户当前题目的单题提交
     * @param pid      题目ID
     * @return List<SubmissionEntity>   返回值
     */
    @Override
    public List<SubmissionEntity> listUserProblemSubmissions(int uid, int pid) {
        SubmissionEntityExample example = new SubmissionEntityExample();
        //如果用户id不为零，表示查询某题某用户对应的提交
        if(uid!=0){
            example.createCriteria()
                    .andUidEqualTo(uid)
                    .andPidEqualTo(pid)
                    .andQueryableTimeLessThan(new Timestamp(System.currentTimeMillis()));
            return submissionMapper.selectByExample(example);
        }
        // 如果用户id为零，表示查询某题所有提交
        example.createCriteria()
                .andPidEqualTo(pid)
//                .andEidEqualTo(0)
                .andQueryableTimeLessThan(new Timestamp(System.currentTimeMillis()));
        return submissionMapper.selectByExample(example);
    }

    /**
     * 查询题目的提交排行，默认根据时间排行
     * 可根据语言分组，按照AC时间、运行效率排行
     *
     * @param pid         题目ID
     * @param sortKeyWord 按照关键字排行
     * @param grouyBy     根据语言分组
     * @return
     */
    @Override
    public List<SubmissionEntity> listProblemLeaderboard(int pid, String sortKeyWord, String grouyBy) {

        SubmissionEntityExample submissionEntityExample = new SubmissionEntityExample();
        SubmissionEntityExample.Criteria criteria = submissionEntityExample.createCriteria();
        submissionEntityExample.setDistinct(true);
        // 默认按照时间排序
        submissionEntityExample.setOrderByClause("using_time ASC");
        if((grouyBy != null) && sortKeyWord.length()>0){
            //按照语言查询
            criteria.andLangEqualTo(LanguageEnum.valueOf(grouyBy));
        }
        // 按照关键字排序
        if((sortKeyWord != null) && sortKeyWord.length()>0 && !sortKeyWord.equals("using_time")){
            submissionEntityExample.setOrderByClause(sortKeyWord+" ASC");
        }
        System.out.println(submissionEntityExample.getOrderByClause());
        criteria.andStatusEqualTo(ResultEnum.AC);
        return submissionMapper.listProblemLeaderboard(submissionEntityExample);
    }

    /**
     * 根据用户ID获取用户所有提交，如果提交未到可查询时间，则不显示
     *
     * @param uid 用户ID
     * @return
     */
    @Override
    public List<SubmissionEntity> listUserSubmission(int uid) {
        SubmissionEntityExample submissionEntityExample = new SubmissionEntityExample();
        submissionEntityExample.setOrderByClause("submit_time DESC");
        submissionEntityExample.createCriteria()
                .andUidEqualTo(uid)
                .andQueryableTimeLessThan(new Timestamp(System.currentTimeMillis()));
        return submissionMapper.selectByExample(submissionEntityExample);
    }

}
