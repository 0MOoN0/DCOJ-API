package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProgramProblemUserService;
import com.dcoj.service.ProgramSubmissionService;
import com.dcoj.util.JWTUtil;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 代码提交相关控制器
 *
 * @author Leon
 */
@RestController
@RequestMapping(value = "/program-submissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProgramSubmissionsController {

    @Autowired
    private ProgramSubmissionService submissionService;

    @Autowired
    private ProgramProblemUserService problemUserService;

    @ApiOperation("获取用户所有编程题提交(Submission)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "页大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "authorization", value = "用户token", required = true, paramType = "header")
    })
    @GetMapping
    public ResponseEntity listUserSubmissions(
            @RequestParam("page_num") int page,
            @RequestParam("page_size") int pageSize,
            @RequestHeader("authorization") String token) {
        int uid = JWTUtil.getUid(token);
        Page pager = PageHelper.startPage(page, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager,
                submissionService.listUserSubmission(uid)));
    }

    /**
     * 获取当前题目的相关提交
     *
     * @param uid      用户ID，如果为0则查询当前题目所有可查询的提交
     * @param pid      题目ID
     * @param page     页码
     * @param pageSize 每页大小
     * @return
     */
    @ApiOperation("获取当前题目的相关提交，需要传入用户token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pid", value = "题目ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_num", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "页大小", required = true, paramType = "query")
    })
    @GetMapping("/problem")
    public ResponseEntity listProblemSubmissions(@RequestParam("uid") int uid,
                                                 @RequestParam("pid") int pid,
                                                 @RequestParam("page_num") int page,
                                                 @RequestParam("page_size") int pageSize) {
        // 获取当前问题所有人的提交
        Page pager = PageHelper.startPage(page, pageSize);
        if (uid == 0) {
            return new ResponseEntity(WebUtil.generatePageData(pager, submissionService.listUserProblemSubmissions(0, pid)));
        }
        // 获取用户此题对应的Submission
        return new ResponseEntity(WebUtil.generatePageData(pager, submissionService.listUserProblemSubmissions(uid, pid)));
    }

    /**
     * 获取当前题目的排行榜情况
     *
     * @param pid         题目ID
     * @param sortKeyWord 排序关键字:submit_time：按提交时间排序;
     * @param groupBy
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("获取题目排行榜")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "题目ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sort_keyword", value = "排序关键字", required = true, paramType = "query"),
            @ApiImplicitParam(name = "group_by", value = "分组关键字", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_num", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "页大小", required = true, paramType = "query")
    })
    @GetMapping("/problem/leaderboard")
    public ResponseEntity listProblemLeaderboard(@RequestParam("pid") int pid,
                                                 @RequestParam("sort_keyword") String sortKeyWord,
                                                 @RequestParam("group_by") String groupBy,
                                                 @RequestParam("page_num") int pageNum,
                                                 @RequestParam("page_size") int pageSize) {
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, submissionService.listProblemLeaderboard(pid, sortKeyWord, groupBy)));
    }
}
