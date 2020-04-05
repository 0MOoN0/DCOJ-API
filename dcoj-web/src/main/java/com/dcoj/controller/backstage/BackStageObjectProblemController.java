package com.dcoj.controller.backstage;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ObjectProblemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *对客观题进行操作
 *
 * @author Jack Lin
 */
@RestController
@Validated
@Api(tags = "后台客观题管理")
@RequestMapping(value = "/backStageObjectProblem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageObjectProblemController {

    @Autowired
    private ObjectProblemService objectProblemService;

    @Autowired
    private ObjectProblemUserService objectProblemUserService;

    @ApiOperation("批量导入选择题")
    @ResponseBody
    @RequestMapping(value = "/importObjectProblem",method = RequestMethod.POST)
    public ResponseEntity uploadObj(@RequestParam("files") MultipartFile files) throws IOException {
        return new ResponseEntity(objectProblemService.importObjectProblem(files));
    }
}
