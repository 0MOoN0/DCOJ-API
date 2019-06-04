package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ObjectSubmissionService;
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
 * 客观题提交控制器
 *
 * @author Leon
 */
@RestController()
@RequestMapping(value = "object-submissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ObjectSubmissionController {

    @Autowired
    private ObjectSubmissionService objectSubmissionService;

    @ApiOperation("获取客观题提交")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "页大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "authorization", value = "用户token", required = true, paramType = "header")
    }
    )
    @GetMapping
    public ResponseEntity listObjectSubmissionByUid(@RequestParam("page_num")int pageNum,
                                                    @RequestParam("page_size") int pageSize,
                                                    @RequestHeader("authorization") String token){
        Page page = PageHelper.startPage(pageNum, pageSize);
        int uid = JWTUtil.getUid(token);
        return new ResponseEntity(WebUtil.generatePageData(page,
                objectSubmissionService.listObjectSubmissionByUid(uid)));
    }

}
