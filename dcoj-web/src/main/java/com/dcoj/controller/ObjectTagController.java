package com.dcoj.controller;

import com.dcoj.controller.format.admin.AddTagFormat;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ObjectTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 对单个ObjectTag进行操作
 *
 * @author WANGQING
 */
@RestController
@Validated
@Api(tags = "单个客观题标签管理")
@RequestMapping(value = "/objectTag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ObjectTagController {
    @Autowired
    private ObjectTagService objectTagService;

    @ApiOperation("添加标签")
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid AddTagFormat format) {
        objectTagService.save(format.getName());
        return new ResponseEntity("添加标签成功");
    }

    @ApiOperation("删除标签")
    @ApiImplicitParam(name = "objectTagId", value = "标签id", required = true)
    @DeleteMapping("/{objectTagId}")
    public ResponseEntity removeByPrimaryKey(@PathVariable int objectTagId) {
        objectTagService.removeByPrimaryKey(objectTagId);
        return new ResponseEntity("标签删除成功");
    }

    @ApiOperation("更新标签名")
    @ApiImplicitParam(name = "objectTagId", value = "标签id", required = true)
    @PutMapping("/{objectTagId}")
    public ResponseEntity updateTag(@PathVariable int objectTagId,
                                    @RequestBody @Valid AddTagFormat format) {
        objectTagService.updateByPrimaryKey(objectTagId, format.getName());
        return new ResponseEntity("标签更新成功");
    }

    @ApiOperation("查询一个标签")
    @ApiImplicitParam(name = "objectTagId", value = "标签id", required = true)
    @GetMapping("/{objectTagId}")
    public ResponseEntity getByPrimaryKey(@PathVariable int objectTagId) {
        objectTagService.getByPrimaryKey(objectTagId);
        return new ResponseEntity("标签查询成功");
    }

}
