package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 对多个ProgramProblem进行操作
 *
 * @author WANGQING
 */
@RestController
@Validated
@Api(tags = "多个编程题管理")
@RequestMapping(value = "/programProblems", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProgramProblemsController {
    @Autowired
    private ProgramProblemService programProblemService;

    @ApiOperation("获取所有题目")
    @GetMapping
    public ResponseEntity listAll(@RequestParam(name = "pageNum") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize) {
        // pageNum  页码
        // pageSize 每页显示数量
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, programProblemService.listAll()));
    }

//    @ApiOperation("根据类型获取所有题目")
//    @GetMapping("/problems/{type}")
//    public ResponseEntity listByType(@RequestParam(name = "page") int page,
//                                     @RequestParam(name = "page_size") int pageSize,
//                                     @PathVariable(name = "type") int type) {
//        Page pager = PageHelper.startPage(page, pageSize);
//        return new ResponseEntity(WebUtil.generatePageData(pager, programProblemService.listByType(type)));
//    }

    //TODO:4.12 WANGQING 未完成导出题目、导入题目

}
