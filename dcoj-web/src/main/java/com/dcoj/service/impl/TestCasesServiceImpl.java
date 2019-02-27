package com.dcoj.service.impl;

import com.dcoj.entity.TestCaseEntity;
import com.dcoj.service.TestCasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 测试用例Service
 * @author Leon
 */
public class TestCasesServiceImpl implements TestCasesService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public int save(int pid, String stdin, String stdout, int strength) {
        return 0;
    }

    @Override
    public int countProblemTestCases(int pid) {
        return 0;
    }

    /**
     * 获取根据pid获取所有TestCases
     * @param pid   题目的业务id
     * @return      此题目的所有测试用例
     */
    @Override
    public List<TestCaseEntity> listAll(int pid) {
        return mongoTemplate.find(new Query(Criteria.where("pid").is(pid)),TestCaseEntity.class);
    }

    @Override
    public void updateTestCaseByTidPid(int tid, int pid, String stdin, String stdout, int strength) {

    }

    @Override
    public void deleteTestCase(int tid, int pid) {

    }

    @Override
    public void deleteProblemTestCases(int pid) {

    }
}
