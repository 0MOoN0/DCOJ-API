package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ObjectProblemService;
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

/**
 * 对多个ObjectProblem进行操作
 *
 * @author WANGQING
 */
@RestController
@Validated
@Api(tags = "多个客观题管理")
@RequestMapping(value = "/objectProblems", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ObjectProblemsController {

    @Autowired
    private ObjectProblemService objectProblemService;

    @ApiOperation("根据题目类型或状态获取所有题目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query" ),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "status", value = "题目状态",  paramType = "query"),
            @ApiImplicitParam(name = "type", value = "题目类型", paramType = "query")
    })
    @GetMapping
    public ResponseEntity listAll(@RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize,
                                  @RequestParam(name = "status",required = false) Integer status,
                                  @RequestParam(name = "type",required = false) Integer type) {
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, objectProblemService.listAll(status,type)));
    }

//    @ApiOperation("根据题目类型或状态获取所有题目")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "int" ),
//            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "int"),
//            @ApiImplicitParam(name = "status", value = "题目状态", required = true, paramType = "int"),
//    })
//    @GetMapping
//    public ResponseEntity listByTypeStatus(@RequestParam(name = "page_num") int pageNum,
//                                     @RequestParam(name = "page_size") int pageSize,
//                                     @PathVariable(name = "status") int status) {
//        Page pager = PageHelper.startPage(pageNum, pageSize);
//        return new ResponseEntity(WebUtil.generatePageData(pager, objectProblemService.listByStatus(status)));
//    }

//    @ApiOperation("根据客观题状态获取所有题目")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "int" ),
//            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "int"),
//            @ApiImplicitParam(name = "status", value = "题目状态", required = true, paramType = "int"),
//    })
//    @GetMapping("/status")
//    public ResponseEntity countByStatus(@RequestParam(name = "page_num") int pageNum,
//                                     @RequestParam(name = "page_size") int pageSize,
//                                     @RequestParam(name = "status") int status) {
////        objectProblemService
////        return new ResponseEntity();
//return null;
//    }



}
