package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.SubmissionDetailEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.judge.ResultEnum;
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



    /**
     * 根据提交ID获取提交详情
     * @param subUserId         当前Submission的用户ID
     * @param pid               当前Problem的ID
     * @param subId             当前Submission的ID
     * @param attachmentId      附件ID
     * @param token             用户token
     * @return
     */
    @ApiOperation("获取提交详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sub_user_id", value = "当前Submission的用户ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pid", value = "当前Problem的ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sub_id", value = "当前Submission的ID", required = true, paramType = "query" ),
            @ApiImplicitParam(name = "attachmentId", value = "附件ID", required = true, paramType = "query" ),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, paramType = "query")
        }
    )
    @GetMapping
    public ResponseEntity getSubDetail(@RequestParam int subUserId,
                                       @RequestParam int pid,
                                       @RequestParam int subId,
                                       @RequestParam int attachmentId,
                                       @RequestHeader String token){
        int uid = JWTUtil.getUid(token);
        HashMap resultMap = new HashMap<>();
        // 判断要查看的提交详情是不是当前用户提交的
        if(subUserId == uid ||  //是当前用户提交的
                problemUserService.getByPidUid(pid, uid).getStatus()==ResultEnum.AC ){       //不是当前用户提交的，但当前用户AC过此题
            SubmissionDetailEntity submissionDetailBySubId = submissionDetailService.getSubmissionDetailBySubId(subId);
            resultMap.put("sub_detail", submissionDetailBySubId);
            // 获取当前题目测试用例
            List<TestCaseEntity> testCaseEntities = testCasesService.listAll(pid);
            resultMap.put("test_cases", testCaseEntities);
            return new ResponseEntity(resultMap);
        }
        return new ResponseEntity("只有你解出这道题或者是管理才能查看他人解答");
    }

}
