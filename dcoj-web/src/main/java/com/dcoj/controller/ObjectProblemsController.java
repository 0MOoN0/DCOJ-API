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

import java.util.List;

/**
 * 对多个ObjectProblem进行操作
 *
 * @author WANGQING
 */
@RestController
@Validated
@Api(tags = "多个客观题管理")
@RequestMapping(value = "/object-problems", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ObjectProblemsController {

    @Autowired
    private ObjectProblemService objectProblemService;

    @ApiOperation("根据条件获取所有题目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tag_list", value = "标签列表", paramType = "query"),
            @ApiImplicitParam(name = "uid", value = "用户id", paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "题目类型", paramType = "query")
    })
    @GetMapping
    public ResponseEntity listAll(@RequestParam(name = "tag_list", required = false) List<Integer> list,
                                  @RequestParam(name = "uid", required = false) Integer uid,
                                  @RequestParam(name = "query", required = false) String query,
                                  @RequestParam(name = "type", required = false) Integer type,
                                  @RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize) {
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, objectProblemService.listAll(list, uid, query,type)));
    }

    @ApiOperation("获取所有编程题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "查询关键字(标题)", paramType = "query")
    })
    @GetMapping("/listAll")
    public ResponseEntity listAllNotByPage(@RequestParam(name = "query", required = false) String query) {
        return new ResponseEntity(objectProblemService.findAllNotByPage(query));
    }

}
