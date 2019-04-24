package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ObjectTagService;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.service.ProgramTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理 控制器
 *
 * @author WANGQING
 **/
@RestController
@Validated
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminController {

    @Autowired
    private ProgramTagService programTagService;

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private ObjectProblemService objectProblemService;

    @Autowired
    private ObjectTagService objectTagService;

    @GetMapping("/overview")
    public ResponseEntity getOverview() {
        // HashMap初始化必须指定容器容量，2的n次幂
        Map<String, Integer> data = new HashMap<>();
        data.put("tags", programTagService.countTags());
        data.put("programProblems", programProblemService.countProgramProblems());
        data.put("objectTags", objectTagService.countTags());
        data.put("objectProblems", objectProblemService.countObjectProblems());

        return new ResponseEntity(data);
    }
}
