package com.dcoj.controller;

import com.dcoj.controller.format.admin.AddTagFormat;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("添加标签")
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid AddTagFormat format) {
        tagService.save(format.getName());
        return new ResponseEntity("添加标签成功");
    }

    @ApiOperation("删除标签")
    @DeleteMapping("/{tid}")
    public ResponseEntity removeById(@PathVariable int tid) {
        tagService.removeById(tid);
        return new ResponseEntity("标签删除成功");
    }

    @ApiOperation("更新标签名")
    @PutMapping("/{tid}")
    public ResponseEntity updateTag(@PathVariable int tid,
                                    @RequestBody @Valid AddTagFormat format) {
        return new ResponseEntity("标签更新成功");
    }

    @ApiOperation("查询一个标签")
    @GetMapping("/{tid}")
    public ResponseEntity getById(@PathVariable int tid) {
        tagService.getById(tid);
        return new ResponseEntity("标签查询成功");
    }

}
