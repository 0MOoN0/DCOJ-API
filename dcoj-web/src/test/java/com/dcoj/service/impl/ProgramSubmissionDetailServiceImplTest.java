package com.dcoj.service.impl;

import com.dcoj.entity.ProgramSubmissionDetailEntity;
import com.dcoj.service.ProgramSubmissionDetailService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Optional;

/**
 * @author Leon
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramSubmissionDetailServiceImplTest {

    @Autowired
    private ProgramSubmissionDetailService submissionDetailService;

    @Test
    public void testHelloWorld() {
        System.out.println("helloworld!");
    }

    @Test
    public void testGet() throws JSONException {
        Map submissionDetailBySubId = submissionDetailService.getSubmissionDetailBySubId(166);
        JSONObject json = new JSONObject();
        json.put("judge_detail", submissionDetailBySubId.get("judge_detail"));
        System.out.println(json);
//        System.out.println("===============" + submissionDetailBySubId);
    }

}
