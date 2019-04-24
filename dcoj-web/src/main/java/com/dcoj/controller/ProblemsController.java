package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProblemService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对多个Problem进行操作
 *
 * @author WANGQING
 */
@RestController
public class ProblemsController {
    @Autowired
    private ProblemService problemService;

    @ApiOperation("获取所有题目")
    @GetMapping("/problems")
    public ResponseEntity listAll(@RequestParam(name = "pageNum") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize) {
        // pageNum  页码
        // pageSize 每页显示数量
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, problemService.listAll()));
    }

    @ApiOperation("根据类型获取所有题目")
    @GetMapping("/problems/{type}")
    public ResponseEntity listByType(@RequestParam(name = "page") int page,
                                     @RequestParam(name = "page_size") int pageSize,
                                     @PathVariable(name = "type") int type) {
        Page pager = PageHelper.startPage(page, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, problemService.listByType(type)));
    }

    //TODO:4.12 WANGQING 未完成导出题目、导入题目

}
