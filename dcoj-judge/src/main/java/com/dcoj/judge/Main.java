package com.dcoj.judge;


import com.dcoj.judge.entity.RequestEntity;
import com.dcoj.judge.entity.ResponseEntity;
import com.dcoj.judge.entity.TestCaseRequestEntity;
import com.dcoj.judge.judger.Judger;
import com.dcoj.judge.judger.dcoj.DCOJJudger;

import java.util.ArrayList;
import java.util.List;

/**
 * 判卷工具测试类
 *
 * @author Leon
 **/
public class Main {
    public static void main(String[] args) {
        List<TestCaseRequestEntity> list = new ArrayList<>(2);
        TestCaseRequestEntity testCaseRequestEntity1 = new TestCaseRequestEntity(null, "hll\n");
//        TestCaseRequestEntity testCaseRequestEntity2 = new TestCaseRequestEntity(null, "hello");
        list.add(testCaseRequestEntity1);
//        list.add(testCaseRequestEntity2);
        /*RequestEntity requestEntity = new RequestEntity(LanguageEnum.PYTHON35, "print(\"hello\")", 3,public class Main {public static void main(String[] args) {System.out.println(\"hll\");}}
                128, list);*/
        RequestEntity requestEntity = new RequestEntity(LanguageEnum.JAVA8, "public class Main {public static void main(String[] args) {System.out.println(\"hll\");}}", 3,
                128, list);
        Judger judger = dcoj(requestEntity);
        ResponseEntity responseEntity = judger.judge();
        System.out.println(requestEntity);
    }

    // 工厂方法，创建一个Judger
    private static Judger dcoj(RequestEntity requestEntity) {
        return new Judger("http://167.179.92.37:5000", requestEntity, new DCOJJudger());
    }
}
