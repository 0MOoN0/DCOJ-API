package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ExaminationSubmissionService;
import com.dcoj.util.JWTUtil;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author zxw
 * @Desriiption: 试卷提交信息
 */
@RestController
@RequestMapping(value = "/exam-submissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExamSubmissionsController {

    @Autowired
    private ExaminationSubmissionService examinationSubmissionService;

    @ApiOperation("获取用户所有试卷提交(Submission)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "页大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "exam_id", value = "试卷id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "uid", value = "用户id", required = false, paramType = "query")
    })
    @GetMapping
    public ResponseEntity listExamSubmissions(
            @RequestParam("page_num") int page,
            @RequestParam("page_size") int pageSize,
            @RequestParam(value = "exam_id",required = false) Integer examId,
            @RequestParam(value = "uid",required = false) Integer uid) {
        Page pager = PageHelper.startPage(page, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager,
                examinationSubmissionService.listByID(examId,uid)));
    }

}
