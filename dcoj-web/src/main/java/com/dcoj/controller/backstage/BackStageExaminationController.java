package com.dcoj.controller.backstage;

import com.dcoj.entity.ExaminationEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ExaminationService;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 试卷管理 控制器
 * @author bin
 * */
@RestController
@Validated
@Api(tags = "试卷管理")
@RequestMapping(value = "/backStageExamination", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private ObjectProblemService objectProblemService;

    @ApiOperation("分页获取所有类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字(标题)", paramType = "query")
    })
    @GetMapping("/listAllByPage")
    public ResponseEntity listAllByPage(@RequestParam(name = "page_num") int pageNum,
                                        @RequestParam(name = "page_size") int pageSize,
                                        @RequestParam(name = "query", required = false) String query){
        // pageNum  页码
        // pageSize 每页显示数量
        Page pager = PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> examinationEntities = examinationService.listAll();
        if(examinationEntities == null || examinationEntities.size() == 0 ){
            return new ResponseEntity(400,"暂无数据","");
        }
        return new ResponseEntity(WebUtil.generatePageData(pager, examinationEntities));
    }

    @ApiOperation("根据ID查询试卷信息")
    @GetMapping("/{exam_id}")
    public ResponseEntity listByExamId(@PathVariable("exam_id")Integer examId){
        List<Map<String, Object>> programProblemEntityList = programProblemService.listByExamIdAndType(examId);
        List<Map<String, Object>> objectProblemEntityList = objectProblemService.listByExamIdAndType(examId);
        ExaminationEntity ex = examinationService.listByExamId(examId);

        Preconditions.checkNotNull(ex, "查询失败，不存在此试卷。");

        ex.setProgram_problem(programProblemEntityList);
        ex.setObject_problem(objectProblemEntityList);

        return new ResponseEntity(ex);
    }

    @ApiOperation("新增试卷信息")
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

    @ApiOperation("更新试卷")
    @PutMapping
    public ResponseEntity update(@RequestBody ExaminationEntity examinationEntity){
        return new ResponseEntity("更新成功",examinationService.update(examinationEntity));
    }
}
