package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProgramTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对多个Tag进行操作
 *
 * @author WANGQING
 */
@RestController
@Validated
@Api(tags = "多个编程题标签管理")
@RequestMapping(value = "/programTags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProgramTagsController {
    @Autowired
    private ProgramTagService programTagService;

    @ApiOperation("显示所有标签")
    @GetMapping
    public ResponseEntity listAll() {
        return new ResponseEntity(programTagService.listAll());
    }


}
