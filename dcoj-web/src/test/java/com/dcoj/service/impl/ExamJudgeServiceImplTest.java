package com.dcoj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ExaminationProblemEntity;
import com.dcoj.entity.exam.AnswerEntity;
import com.dcoj.entity.exam.AnswerExamEntity;
import com.dcoj.exam.ExamAutoTaskExtends;
import com.dcoj.service.ExamJudgeService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dcoj.judge.LanguageEnum.JAVA8;
import static com.dcoj.judge.LanguageEnum.PYTHON35;

/**
 * @author zxw
 * @Desriiption:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamJudgeServiceImplTest {

    @Autowired
    private ExamJudgeService examJudgeService;

    @Test
    public void testExamJudge(){
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
        AnswerExamEntity answerExamEntity = new AnswerExamEntity();
        List<AnswerEntity> answerSheet = new ArrayList<>();

        AnswerEntity answerEntity1 = new AnswerEntity();

        answerEntity1.setExamProblemId(18);//
        answerEntity1.setLang(JAVA8);//用户选择
        answerEntity1.setProblemId(21);//
        answerEntity1.setAnswer(stringBuffer);//用户输入
        answerEntity1.setProblemType(1);//

        answerSheet.add(answerEntity1);

        JSONObject lang = new JSONObject();
        JSONArray langs = new JSONArray();
        langs.add(JAVA8);
        langs.add(PYTHON35);
        lang.put("lang", langs);

        ExaminationProblemEntity examinationProblemEntity1 = new ExaminationProblemEntity();
        examinationProblemEntity1.setProblemType(1);//
        examinationProblemEntity1.setExamProblemLocate(4);//
        examinationProblemEntity1.setScore(15);//
        examinationProblemEntity1.setProblemId(21);//
        examinationProblemEntity1.setExamId(20);//
        examinationProblemEntity1.setLang(lang);//
        examinationProblemEntity1.setExamProblemId(18);//

        Map<Integer, ExaminationProblemEntity> examProblemSheet = new HashMap<>();
        examProblemSheet.put(examinationProblemEntity1.getExamProblemId(),examinationProblemEntity1);

        answerExamEntity.setAnswerSheet(answerSheet);
        answerExamEntity.setExamProblemSheet(examProblemSheet);

        ExamAutoTaskExtends examAutoTaskExtends = new ExamAutoTaskExtends();
        examAutoTaskExtends.setExamId(20);
        examAutoTaskExtends.setUid(1);

        examJudgeService.examJudge(answerExamEntity,examAutoTaskExtends);
    }
}
