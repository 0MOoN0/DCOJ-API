package com.dcoj.controller;

import com.dcoj.entity.ProblemEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.TagEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.security.SessionHelper;
import com.dcoj.service.ProblemService;
import com.dcoj.service.TagProblemService;
import com.dcoj.service.TagService;
import com.sun.deploy.config.DefaultConfig;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 对单个Problem进行操作
 *
 * @author WANGQING
 */
@RestController
@Validated
@RequestMapping(value = "/problem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private TagProblemService tagProblemService;

    @Autowired
    private TagService tagService;

    @ApiOperation("获取该题的所有标签")
    @GetMapping("/{pid}/tags")
    public ResponseEntity listProblemTagsByPid(@PathVariable("pid") int pid) {
       return new ResponseEntity(problemService.listProblemTagsByPid(pid));
    }

    @ApiOperation("删除指定题目")
    @DeleteMapping("/{pid}")
    public ResponseEntity removeByPid(@PathVariable int pid) {
        problemService.removeByPid(pid);
        return new ResponseEntity("题目删除成功");
    }

    @ApiOperation("获取指定一道题目的信息")
    @GetMapping("/{pid}")
    public ResponseEntity getById(@PathVariable int pid) {
        ProblemEntity problemEntity = problemService.getById(pid);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("problem", problemEntity);
        return new ResponseEntity(dataMap);
    }

    @ApiOperation("创建题目")
    @PostMapping
    public ResponseEntity addProblem(@RequestBody @Valid AddProblemFormat format) {

        checkProblemFormat(format);

//        int pid = problemService.save(format.getTags(), owner, format.getTitle(), format.getDescription(), format.getInputFormat(),
//                format.getOutputFormat(), format.getDifficult(), format.getSamples(),
//                DefaultConfig.PROGRAM_USED_TIME, DefaultConfig.PROGRAM_USED_MEMORY, ProblemStatus.EDITING);

        return new ResponseEntity("题目添加成功", pid);
    }


    private void checkProblemFormat(AddProblemFormat format) {
        // valid sample
        //checkProblemSamples(format.getSamples());

        // valid tags
        //checkProblemTags(format.getTags());
    }
}
