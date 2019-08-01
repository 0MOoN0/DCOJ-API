package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProgramProblemUserService;
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
 * @author Leon
 */
@RestController
@RequestMapping(value = "/program-problem-user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProgramProblemUserController {

    @Autowired
    private ProgramProblemUserService programProblemUserService;

    @ApiOperation("获取用户编程题的提交历史(ProgramProblemUser)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "页大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "authorization", value = "用户token", required = true, paramType = "header")
    })
    @GetMapping("/all")
    public ResponseEntity listProgramProblemUser(@RequestParam("page_num") int pageNum,
                                                @RequestParam("page_size") int pageSize,
                                                @RequestHeader("authorization") String authorization){
        int uid = JWTUtil.getUid(authorization);
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(page,programProblemUserService.listUserProblemHistory(uid)));
    }

}
