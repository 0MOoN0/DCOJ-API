package com.dcoj.controller.backstage;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.SysCate;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.service.SysCateService;
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
 * 类别管理 控制器
 * @author bin
 * */
@RestController
@Validated
@Api(tags = "测试用例管理")
@RequestMapping(value = "/backStageSysCate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageSysCateController {

    @Autowired
    private SysCateService sysCateService;

    @ApiOperation("获取所有类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "查询关键字(标题)", paramType = "query")
    })
    @GetMapping("/listAll")
    public ResponseEntity listAll(@RequestParam(name = "query", required = false) String query){
        List<SysCate> SysCateList = sysCateService.listAllByPage(query);
        if(SysCateList == null || SysCateList.size() == 0 ){
            return new ResponseEntity(400,"暂无数据","");
        }
        return new ResponseEntity(SysCateList);
    }


    @ApiOperation("分页获取所有类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字(标题)", paramType = "query")
    })
    @GetMapping("/listAllByPage")
    public ResponseEntity listAllByPage(@RequestParam(name = "page_num") int pageNum,
                                        @RequestParam(name = "page_size") int pageSize,
                                        @RequestParam(name = "query", required = false) String query){
        // pageNum  页码
        // pageSize 每页显示数量
        Page pager = PageHelper.startPage(pageNum, pageSize);
        List<SysCate> SysCateList = sysCateService.listAllByPage(query);
        if(SysCateList == null || SysCateList.size() == 0 ){
            return new ResponseEntity(400,"暂无数据","");
        }
        return new ResponseEntity(WebUtil.generatePageData(pager, SysCateList));
    }

    @ApiOperation("删除类别")
    @ApiImplicitParam(name = "sId", value = "类别id")
    @DeleteMapping("/{sId}")
    public ResponseEntity remove(@PathVariable("sId") int sId) {
        sysCateService.delete(sId);
        return new ResponseEntity("类别删除成功");
    }

    @ApiOperation("修改类别信息")
    @ApiImplicitParam(name = "sysCate", value = "类别信息")
    @PutMapping
    public ResponseEntity edit(@RequestBody SysCate sysCate) {
        sysCateService.updateSelective(sysCate);
        return new ResponseEntity("修改成功");
    }

    @ApiOperation("查询类别信息")
    @ApiImplicitParam(name = "sId", value = "类别id")
    @GetMapping("/{sId}")
    public ResponseEntity select(@PathVariable("sId") int sId) {
        SysCate sysCate = sysCateService.getById(sId);
        return new ResponseEntity(sysCate);
    }

    @ApiOperation("类别添加")
    @ApiImplicitParam(name = "sysCate", value = "类别信息")
    @PostMapping
    public ResponseEntity add(@RequestBody SysCate sysCate) {
        sysCateService.saveSelective(sysCate);
        return new ResponseEntity("添加成功");
    }
}
