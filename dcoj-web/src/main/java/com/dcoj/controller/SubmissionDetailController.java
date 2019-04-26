package com.dcoj.controller;

import com.dcoj.entity.*;
import com.dcoj.judge.ResultEnum;
import com.dcoj.service.AttachmentService;
import com.dcoj.service.ProblemUserService;
import com.dcoj.service.SubmissionDetailService;
import com.dcoj.service.TestCasesService;
import com.dcoj.util.JWTUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Leon
 */
@RestController
@RequestMapping(value = "/sub-detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SubmissionDetailController {

    @Autowired
    private SubmissionDetailService submissionDetailService;

    @Autowired
    private TestCasesService testCasesService;

    @Autowired
    private ProblemUserService problemUserService;

    @Autowired
    private AttachmentService attachmentService;



    /**
     * 根据提交ID获取提交详情
     * @param subUserId         当前Submission的用户ID
     * @param pid               当前Problem的ID
     * @param subId             当前Submission的ID
     * @param sourceCode      附件ID
     * @param token             用户token
     * @return
     */
    @ApiOperation("获取提交详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sub_user_id", value = "当前Submission的用户ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pid", value = "当前Problem的ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sub_id", value = "当前Submission的ID", required = true, paramType = "query" ),
            @ApiImplicitParam(name = "source_code", value = "源码", required = true, paramType = "query" ),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, paramType = "header")
        }
    )
    @GetMapping
    public ResponseEntity getSubDetail(@RequestParam("sub_user_id") int subUserId,
                                       @RequestParam("pid") int pid,
                                       @RequestParam("sub_id") int subId,
                                       @RequestParam("source_code") int sourceCode,
                                       @RequestHeader("token") String token){
        int uid = JWTUtil.getUid(token);
        HashMap resultMap = new HashMap<>();
        ProblemUserEntity problemUserEntity = problemUserService.getByPidUid(pid, uid);
        // 判断要查看的提交详情是不是当前用户提交的
        if(subUserId == uid ||  //是当前用户提交的
                (problemUserEntity != null&&
                problemUserEntity.getStatus()==ResultEnum.AC )
                ){       //不是当前用户提交的，但当前用户AC过此题
            SubmissionDetailEntity submissionDetailBySubId = submissionDetailService.getSubmissionDetailBySubId(subId);
            resultMap.put("sub_detail", submissionDetailBySubId);
            // 获取当前题目测试用例
            List<TestCaseEntity> testCaseEntities = testCasesService.listAll(pid);
            resultMap.put("test_cases", testCaseEntities);
            // 获取附件URL
            AttachmentEntity attachmentEntity = attachmentService.getByAid(sourceCode);
            if(attachmentEntity==null){
                resultMap.put("attachment_url", null);
                return new ResponseEntity(resultMap);
            }
            resultMap.put("attachment_url", attachmentEntity.getUrl());
            return new ResponseEntity(resultMap);
        }
        return new ResponseEntity("只有你解出这道题或者是管理才能查看他人解答");
    }

}
