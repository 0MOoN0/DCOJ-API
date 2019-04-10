package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.SubmissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leon
 */
@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public ResponseEntity listUserSubmissions(@RequestParam("pid") int pid,
                                              @RequestParam("page") int page,
                                              @RequestParam("page_size") int pageSize){
        //TODO 2019.04.10 Leon 完成SubmissionController
        return null;
    }


}
