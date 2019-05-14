package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query" ),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "tagList", value = "标签列表",  paramType = "query"),
            @ApiImplicitParam(name = "uid", value = "用户id",  paramType = "query"),
            @ApiImplicitParam(name = "difficult", value = "题目难度",  paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字",  paramType = "query")
    })
    @GetMapping
    public ResponseEntity listAll(@RequestParam(name="tagList",required = false) List<Integer> list,
                                      @RequestParam(name = "uid",required = false) Integer uid,
                                      @RequestParam(name="difficult",required = false) Integer difficult,
                                      @RequestParam(name = "query",required = false) String query,
                                      @RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize) {
        // pageNum  页码
        // pageSize 每页显示数量
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, programProblemService.listAll(list,uid,difficult,query)));
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
