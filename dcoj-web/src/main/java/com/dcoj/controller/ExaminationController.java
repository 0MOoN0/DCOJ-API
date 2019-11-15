package com.dcoj.controller;

import com.dcoj.entity.ExaminationEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ExaminationService;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author zxw
 * @Desriiption: 试卷控制层
 */
@RestController
@RequestMapping(value = "/exam", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private ObjectProblemService objectProblemService;

    @GetMapping
    public ResponseEntity listAll(){
        return new ResponseEntity(examinationService.listAll());
    }

    @GetMapping("/page/{page_num}/{page_size}")
    public ResponseEntity listAllByPage(@PathVariable(name = "page_num") int pageNum,
                                        @PathVariable(name = "page_size") int pageSize){
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, examinationService.listAll()));
    }

    @GetMapping("/{exam_id}")
    public ResponseEntity listByExamId(@PathVariable("exam_id")Integer examId){
        List<Map<String, Object>> programProblemEntityList = programProblemService.listByExamIdAndType(examId);
        List<Map<String, Object>> objectProblemEntityList = objectProblemService.listByExamIdAndType(examId);
        ExaminationEntity ex = examinationService.listByExamId(examId);

        Preconditions.checkNotNull(ex, "查询失败，不存在此试卷。");

       ex.setProgram_problem(programProblemEntityList);
       ex.setSingle_problem(objectProblemEntityList);

        return new ResponseEntity(ex);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ExaminationEntity examinationEntity){
        int examId = examinationService.save(examinationEntity);
        return new ResponseEntity("新增试卷成功",examId);
    }

    @ApiOperation("删除指定试卷")
    @DeleteMapping("/{exam_id}")
    public ResponseEntity remove(@PathVariable("exam_id") Integer examId){
        return new ResponseEntity("删除成功",examinationService.removeByExamId(examId));
    }

    @PutMapping("/{exam_id}")
    public ResponseEntity update(@PathVariable("exam_id")Integer examId,@RequestBody ExaminationEntity examinationEntity){
        return new ResponseEntity("更新成功",examinationService.update(examId,examinationEntity));
    }
}
