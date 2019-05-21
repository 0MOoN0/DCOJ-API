package com.dcoj.controller;

import com.dcoj.controller.format.admin.AddTagFormat;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProgramTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 对单个Tag进行操作
 *
 * @author WANGQING
 */
@RestController
@Validated
@Api(tags = "单个编程题标签管理")
@RequestMapping(value = "/program-tag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProgramTagController {

    @Autowired
    private ProgramTagService programTagService;

    @ApiOperation("添加标签")
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid AddTagFormat format) {
        programTagService.save(format.getName());
        return new ResponseEntity("添加标签成功");
    }

    @ApiOperation("删除标签")
    @ApiImplicitParam(name = "program_tag_d", value = "标签id")
    @DeleteMapping("/{program_tag_d}")
    public ResponseEntity removeById(@PathVariable("program_tag_d") int programTagId) {
        programTagService.removeByPrimaryKey(programTagId);
        return new ResponseEntity("标签删除成功");
    }

    @ApiOperation("更新标签名")
    @ApiImplicitParam(name = "program_tag_d", value = "标签id")
    @PutMapping("/{program_tag_d}")
    public ResponseEntity updateTag(@PathVariable("program_tag_d") int programTagId,
                                    @RequestBody @Valid AddTagFormat format) {
        programTagService.updateByPrimaryKey(programTagId, format.getName());
        return new ResponseEntity("标签更新成功");
    }

//    @ApiOperation("查询一个标签")
//    @ApiImplicitParam(name = "programTagId", value = "标签id")
//    @GetMapping("/{programTagId}")
//    public ResponseEntity getById( @PathVariable int programTagId) {
//        programTagService.getByPrimaryKey(programTagId);
//        return new ResponseEntity("标签查询成功");
//    }
//    @ApiImplicitParam("这个是标签id")
}
