package com.dcoj.controller;

import com.dcoj.entity.ExamJudgeEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ExamJudgeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zxw
 * @Desriiption:
 */
@RestController
@Validated
@Api(tags = "单个编程题管理")
@RequestMapping(value = "/sys/examjudge", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestExamController {

    @Autowired
    private ExamJudgeService examJudgeService;

    @PostMapping
    public ResponseEntity examJudge(@RequestBody ExamJudgeEntity examJudgeEntity){
        Integer examSubmissionId = examJudgeService.handleExamJudge(examJudgeEntity);
        if(examSubmissionId != null){
            return new ResponseEntity(examSubmissionId);
        }else {
            return new ResponseEntity("判卷失败~");
        }
    }
}
