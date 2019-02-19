package com.dcoj.judge.judger.dcoj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dcoj.judge.ResultEnum;
import com.dcoj.judge.entity.RequestEntity;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.entity.TestCaseResponseEntity;
import com.dcoj.judge.judger.JudgerApi;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 判卷实现
 * @author Leon
 **/
public class DCOJJudger implements JudgerApi {

    private final OkHttpClient CLIENT = new OkHttpClient()
            .newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    private String REQUEST_URL;
    private RequestEntity REQUEST_ENTITY;

    @Override
    public ResponseEntity judge(String url, RequestEntity requestEntity) throws Exception {
        REQUEST_URL = url + "/judge";
        this.REQUEST_ENTITY = requestEntity;
        return send();
    }

    private ResponseEntity send() throws Exception {
        Request request = new Request.Builder()
                .url(REQUEST_URL)
                .header("Content-Type", "application/json")
                .post(formRequestBody())
                .build();
        Response response = CLIENT.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String json = response.body().string();
        return parseResponse(json);
    }

    // 封装请求体
    private RequestBody formRequestBody() {
        String json = JSON.toJSONString(REQUEST_ENTITY, SerializerFeature.WriteMapNullValue);
        return RequestBody
                .create(MediaType.parse("application/json;charset=utf-8"), json);
    }

    // 转换结果
    private ResponseEntity parseResponse(String json) throws Exception {
        JSONObject obj = JSON.parseObject(json);
        ResultEnum result = convertStringToResult(obj.getString("result"));
        //获取判卷结果
        if (result.equals(ResultEnum.SE)) {
            throw new Exception("Remote judge error:" + obj.getString("error_message"));
        }
        // 获取测试用例，对测试结果进行封装
        JSONArray testCases = obj.getJSONArray("test_cases");
        // 获取test_case
        List<TestCaseResponseEntity> testCaseList = new ArrayList<>(testCases.size());
        //封装响应测试用例
        for (int i = 0; i < testCases.size(); i++) {
            JSONObject tempObj = testCases.getJSONObject(i);
            String tempErrorMessage = tempObj.getString("error_message");
            ResultEnum tempResult = convertStringToResult(tempObj.getString("result"));
            Double time = tempObj.getDouble("time");
            Double memory = tempObj.getDouble("memory");
            TestCaseResponseEntity testCaseResponseEntity = new TestCaseResponseEntity(tempResult, tempErrorMessage, time, memory);
            testCaseList.add(testCaseResponseEntity);
        }

        ResponseEntity responseEntity = new ResponseEntity(obj.getDouble("time"), obj.getInteger("memory"),
                result, obj.getString("file_path"), testCaseList);
        return responseEntity;
    }

    private ResultEnum convertStringToResult(String result) {
        switch (result) {
            case "SE":
                return ResultEnum.SE;
            case "WA":
                return ResultEnum.WA;
            case "AC":
                return ResultEnum.AC;
            case "RTE":
                return ResultEnum.RTE;
            case "CE":
                return ResultEnum.CE;
            case "TLE":
                return ResultEnum.TLE;
            default:
                return ResultEnum.SE;
        }
    }

}
