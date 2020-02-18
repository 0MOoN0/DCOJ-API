package com.dcoj.controller;

import com.dcoj.controller.format.admin.ObjectProblemFormat;
import com.dcoj.entity.ObjectProblemEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.SysCate;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对单个ObjectProblem进行操作
 *
 * @author WANGQING
 */
@RestController
@Validated
@Api(tags = "单个客观题管理")
@RequestMapping(value = "/object-problem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ObjectProblemController {

    @Autowired
    private ObjectProblemService objectProblemService;

    @ApiOperation("获取该题的所有标签")
    @ApiImplicitParam(name = "object_problem_id", value = "客观题id", required = true, paramType = "path")
    @GetMapping("/tags/{object_problem_id}")
    public ResponseEntity listObjectProblemTagsByPrimaryKey(@PathVariable("object_problem_id") Integer objectProblemId) {
        return new ResponseEntity(objectProblemService.listObjectProblemTagsByPrimaryKey(objectProblemId));
    }

    @ApiOperation("删除指定题目")
    @ApiImplicitParam(name = "object_problem_id", value = "客观题id", required = true, paramType = "path")
    @DeleteMapping("/{object_problem_id}")
    public ResponseEntity removeByPrimaryKey(@PathVariable("object_problem_id") Integer objectProblemId) {
        objectProblemService.removeByPrimaryKey(objectProblemId);
        return new ResponseEntity("题目删除成功");
    }

    @ApiOperation("获取指定一道题目的信息")
    @ApiImplicitParam(name = "object_problem_id", value = "客观题id", required = true, paramType = "path")
    @GetMapping("/{object_problem_id}")
    public ResponseEntity getByPrimaryKey(@PathVariable("object_problem_id") Integer objectProblemId) {
        ObjectProblemEntity objectProblemEntity = objectProblemService.getByPrimaryKey(objectProblemId);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("object_problem", objectProblemEntity);
        return new ResponseEntity(dataMap);
    }

    /**
     *  根据类别id查询关联题目列表
     * @return
     */
    @GetMapping("/cateId/{cate_id}")
    public ResponseEntity listAllByCateId(@PathVariable("cate_id")Integer cateId,
                                          @RequestParam(name = "page_num") int pageNum,
                                          @RequestParam(name = "page_size") int pageSize){
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, objectProblemService.listAllByCateId(cateId)));
    }

    @ApiOperation("创建题目")
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ObjectProblemFormat format) {
        // 将format格式的数据保存到ObjectProblemEntity对象里
        ObjectProblemEntity objectProblemEntity = new ObjectProblemEntity();
        objectProblemEntity.setType(format.getType());
        objectProblemEntity.setDescription(format.getDescription());
        objectProblemEntity.setAnswer(format.getAnswer());
        objectProblemEntity.setCateId(format.getCate());
        int objectProblemId = objectProblemService.insertSelective(format.getObjectTags(), objectProblemEntity);
        return new ResponseEntity("题目添加成功", objectProblemId);
    }

    @ApiOperation("更新题目")
    @PutMapping("/{object_problem_id}")
    public ResponseEntity updateProblemAndTags(@PathVariable("object_problem_id") Integer objectProblemId,
                                               @RequestBody @Valid ObjectProblemFormat format) {
        ObjectProblemEntity objectProblemEntity = new ObjectProblemEntity();
        objectProblemEntity.setType(format.getType());
        objectProblemEntity.setDescription(format.getDescription());
        objectProblemEntity.setAnswer(format.getAnswer());
        objectProblemEntity.setCateId(format.getCate());
        objectProblemService.updateProblemAndTags(objectProblemId, format.getObjectTags(), objectProblemEntity);
        return new ResponseEntity("题目更新成功");
    }
}

