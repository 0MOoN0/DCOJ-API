package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.TagService;
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
@RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TagsController {
    @Autowired
    private TagService tagService;

    @ApiOperation("显示所有标签")
    @GetMapping
    public ResponseEntity listTags() {
        return new ResponseEntity(tagService.listAll());
    }

}
