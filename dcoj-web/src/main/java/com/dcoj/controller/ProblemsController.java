package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProblemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 对多个Problem进行操作
 *
 * @author WANGQING
 */
public class ProblemsController {
    @Autowired
    private ProblemService problemService;

    @ApiOperation("获取所有题目")
    @GetMapping("/problems")
    public ResponseEntity getProblems(@RequestParam(name = "page") int page,
                                          @RequestParam(name = "page_size") int pageSize) {
//        int uid = SessionHelper.get().getUid();
//        Page pager = PageHelper.startPage(page, pageSize);
//        return new ResponseEntity(WebUtil.generatePageData(pager, problemService.listUserProblems(uid)));
//
        return null;
    }



}
