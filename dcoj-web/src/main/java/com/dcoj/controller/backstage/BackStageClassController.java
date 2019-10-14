package com.dcoj.controller.backstage;


import com.dcoj.entity.ClassEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ClassService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 班级管理 控制器
 *
 * @author Jack Lin
 *
 */
@RestController
@Validated
@Api(tags = "班级管理")
@RequestMapping(value = "/backStageClass", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageClassController {
    @Autowired
    private ClassService classService;

    @ApiOperation("分页获取所有班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
    })
    @GetMapping("listAllByPage")
    public ResponseEntity listAllByPage(@RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize ){
        if(classService.listAll().size()<=0){
            return new ResponseEntity(400,"数据获取异常","");
        }else
        {   // pageNum  页码
            // pageSize 每页显示数量
            Page pager = PageHelper.startPage(pageNum, pageSize);
            return new ResponseEntity(WebUtil.generatePageData(pager, classService.listAll()));
        }
    }

    @ApiOperation("获取所有班级")
    @GetMapping("listAll")
    public ResponseEntity listAll() {
        if(classService.listAll().size()<=0){
            return new ResponseEntity(400,"数据获取异常","");
        }else {
            return new ResponseEntity(classService.listAll());
        }

    }

    @ApiOperation("删除班级")
    @ApiImplicitParam(name = "class_id", value = "班级id")
    @DeleteMapping("deleted/{class_id}")
    public ResponseEntity remove(@PathVariable("class_id") int class_id) {
        classService.removeByPrimaryKey(class_id);
        return new ResponseEntity("班级删除成功");
    }

    @ApiOperation("修改单个班级信息")
    @ApiImplicitParam(name = "classEntity", value = "班级信息")
    @PostMapping("/edit")
    public ResponseEntity edit(@RequestBody ClassEntity classEntity) {
        classService.updateClass(classEntity.getClassId(),classEntity);
        return new ResponseEntity("修改成功");
    }
    @ApiOperation("查询单个班级信息")
    @ApiImplicitParam(name = "class_id", value = "班级id")
    @GetMapping("/select/{class_id}")
    public ResponseEntity select(@PathVariable("class_id") int class_id) {
        ClassEntity classEntity = classService.getByPrimaryKey(class_id);
        return new ResponseEntity(classEntity);
    }

    @ApiOperation("根据班级信息添加")
    @ApiImplicitParam(name = "classEntity", value = "班级信息")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ClassEntity classEntity) {
        classService.insertClass(classEntity);
        return new ResponseEntity("添加成功");
    }


}
