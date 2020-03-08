package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ExaminationSubmissionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @Desriiption: 试卷提交详情
 */
@RestController
@RequestMapping(value = "/exam-sub-detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExamSubmissionDetailController {

    @Autowired
    private ExaminationSubmissionDetailService examinationSubmissionDetailService;

    @GetMapping
    public ResponseEntity getSubDetail(@RequestParam("exam_sub_id") Integer examSubmissionId){
        return new ResponseEntity(examinationSubmissionDetailService.getExaminationSubmissionDetailByIdCheckWithTime(examSubmissionId));
    }
}
