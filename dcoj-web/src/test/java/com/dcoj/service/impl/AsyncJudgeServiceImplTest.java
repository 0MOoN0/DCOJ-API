package com.dcoj.service.impl;

import com.dcoj.entity.TestCaseEntity;
import com.dcoj.judge.JudgeResult;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.service.AsyncJudgeService;
import com.dcoj.service.JudgeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Leon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncJudgeServiceImplTest {

    @Autowired
    AsyncJudgeService asyncJudgeService;

    @Autowired
    JudgeService judgeService;

    @Test
    public void addTestJudgeTest() throws InterruptedException {
        String sourceCode = "public class Main {public static void main(String[] args) {System.out.println(\"hll\");}}";
        int time = 3;
        int memory = 128;
        TestCaseEntity testCaseEntity = new TestCaseEntity();
        testCaseEntity.setStdout("hll");
        ArrayList<TestCaseEntity> testCaseEntities = new ArrayList<>();
        testCaseEntities.add(testCaseEntity);
        String resultId = asyncJudgeService.addTestJudge(sourceCode, LanguageEnum.JAVA8, time, memory, testCaseEntities);//进行判卷
        Thread.sleep(4000);
        JudgeResult judgeResult = judgeService.getJudgeResult(resultId);//根据判卷结果id获取判卷结果
        //打印结果
        Optional.ofNullable(judgeResult).ifPresent(result -> System.out.println(result.getResponse().getResult()));
    }

    @Test
    public void addTestJudgeTest2() throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("import java.util.Scanner;\n" +
                "\n" +
                "public class Main {\n" +
                "\tpublic static void main(String args[]) {\n" +
                "\t\tScanner in = new Scanner(System.in);\n" +
                "\t\tint n = in.nextInt();\n" +
                "\t\tint F[] = new int[n + 2];\n" +
                "\t\tF[1] = 1;\n" +
                "\t\tF[2] = 1;\n" +
                "\t\tif (n > 2) {\n" +
                "\t\t\tfor (int i = 3; i <= n; i++) {\n" +
                "\t\t\t\tF[i] = (F[i - 1] + F[i - 2]) % 10007;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t\tSystem.out.println(F[n]);\n" +
                "\t}\n" +
                "\n" +
                "}");
        String sourceCode = stringBuffer.toString();
        String resultId = asyncJudgeService.addProblemJudge(sourceCode,LanguageEnum.JAVA8,1,21);
        Thread.sleep(4000);
        JudgeResult judgeResult = judgeService.getJudgeResult(resultId);//根据判卷结果id获取判卷结果
        System.out.println("zxw");
    }


}
