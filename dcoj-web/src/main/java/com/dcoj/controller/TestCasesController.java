package com.dcoj.controller;

import com.dcoj.entity.ExaminationEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.service.TestCasesService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zxw
 * @Desriiption: 测试用例控制器
 */
@RestController
@RequestMapping(value = "/testcases", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class TestCasesController {

    @Autowired
    public TestCasesService testCasesService;

    @ApiOperation("获取指定题目的测试用例")
    @GetMapping("/{pid}")
    @ResponseBody
    public ResponseEntity listAll(@PathVariable("pid") Integer pid){
        return new ResponseEntity(testCasesService.listAll(pid));
    }

    @ApiOperation("删除指定测试用例")
    @DeleteMapping("/{tid}")
    public ResponseEntity removeOne(@PathVariable("tid") Integer tid){
        return new ResponseEntity("删除成功",testCasesService.deleteTestCase(tid));
    }

    @ApiOperation("删除指定题目的所有测试用例")
    @DeleteMapping("/alldel/{pid}")
    public ResponseEntity removeAllByPid(@PathVariable("pid") Integer pid){
        return new ResponseEntity("删除成功",testCasesService.deleteProblemTestCases(pid));
    }

    @ApiOperation("新增测试用例")
    @PostMapping
    public ResponseEntity save(@RequestBody List<TestCaseEntity> testCaseEntity){
        return new ResponseEntity("添加成功",testCasesService.saveAll(testCaseEntity));
    }
}
