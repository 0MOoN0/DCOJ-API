package com.dcoj.judge.judger;

import com.dcoj.judge.ResultEnum;
import com.dcoj.judge.entity.RequestEntity;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.entity.TestCaseResponseEntity;
import com.dcoj.judge.judger.AbstractJudger;
import com.dcoj.judge.judger.JudgerApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 **/
public class Judger extends AbstractJudger {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private RequestEntity requestEntity;

    private String url;

    public Judger(String url, RequestEntity requestEntity, JudgerApi judgerApi) {
        super(judgerApi);
        this.url = url;
        this.requestEntity = requestEntity;
    }

    @Override
    public ResponseEntity judge() {
        try {
            return judgerApi.judge(url, requestEntity);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return getSEResponseEntity(requestEntity);
        }
    }

    // 封装SE响应
    private ResponseEntity getSEResponseEntity(RequestEntity requestEntity) {
        List<TestCaseResponseEntity> testCases = new ArrayList<>(requestEntity.getTestCases().size());
        // 根据测试用例个数封装响应测试用例
        for (int i = 0; i < requestEntity.getTestCases().size(); i++) {
            TestCaseResponseEntity responseEntity = new TestCaseResponseEntity(ResultEnum.SE, ResultEnum.SE.getName(), 0d, 0d);
            testCases.add(responseEntity);
        }
        return new ResponseEntity(0, 0, ResultEnum.SE, testCases);
    }
}
