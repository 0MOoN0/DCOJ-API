package com.dcoj.controller.backstage;

import com.dcoj.entity.ClassEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.service.TestCasesService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试用例管理 控制器
 * @author bin
 * */
@RestController
@Validated
@Api(tags = "测试用例管理")
@RequestMapping(value = "/backStageTestCase", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageTestCaseController {

    @Autowired
    private TestCasesService testCasesService;

    @ApiOperation("分页获取所有测试用例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字(标题)", paramType = "query")
    })
    @GetMapping("/listAllByPage")
    public ResponseEntity listAllByPage(@RequestParam(name = "page_num") int pageNum,
                                        @RequestParam(name = "page_size") int pageSize,
                                        @RequestParam(name = "pid", required = false) Integer pid){
        // pageNum  页码
        // pageSize 每页显示数量
        Map<String, Object> paramMap = new HashMap<>();
        if(pid != null && StringUtils.isNotBlank(pid.toString())){
            paramMap.put("programId",pid);
        }
        Page pager = PageHelper.startPage(pageNum, pageSize);
        List<TestCaseEntity> testCaseEntityList = testCasesService.listAllByPage(paramMap);
        if(testCaseEntityList == null || testCaseEntityList.size() == 0 ){
            return new ResponseEntity(400,"暂无数据","");
        }
        return new ResponseEntity(WebUtil.generatePageData(pager, testCaseEntityList));
    }


    @ApiOperation("删除测试用例")
    @ApiImplicitParam(name = "tId", value = "测试用例id")
    @DeleteMapping("/{tId}")
    public ResponseEntity remove(@PathVariable("tId") int tId) {
        testCasesService.deleteTestCase(tId);
        return new ResponseEntity("测试用例删除成功");
    }

    @ApiOperation("修改测试用例信息")
    @ApiImplicitParam(name = "classEntity", value = "测试用例信息")
    @PutMapping
    public ResponseEntity edit(@RequestBody TestCaseEntity testCaseEntity) {
        testCasesService.updateTestCaseSelective(testCaseEntity);
        return new ResponseEntity("修改成功");
    }

    @ApiOperation("查询测试用例信息")
    @ApiImplicitParam(name = "tId", value = "测试用例id")
    @GetMapping("/{tId}")
    public ResponseEntity select(@PathVariable("tId") int tId) {
        TestCaseEntity testCaseEntity = testCasesService.getById(tId);
        return new ResponseEntity(testCaseEntity);
    }

    @ApiOperation("测试用例添加")
    @ApiImplicitParam(name = "testCaseEntity", value = "测试用例信息")
    @PostMapping
    public ResponseEntity add(@RequestBody TestCaseEntity testCaseEntity) {
        testCasesService.save(testCaseEntity);
        return new ResponseEntity("添加成功");
    }
}
