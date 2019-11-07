package com.dcoj.controller.backstage;

import com.dcoj.controller.format.admin.AddTagFormat;
import com.dcoj.entity.ObjectTagEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ObjectTagService;
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

import javax.validation.Valid;
import java.util.List;

/**
 * 客观题标签管理 控制器
 *
 * @author Jack Lin
 *
 */
@RestController
@Validated
@Api(tags = "客观题标签管理")
@RequestMapping(value = "/backStageObjectTag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageObjectTagController {

    @Autowired
    private ObjectTagService objectTagService;

    @ApiOperation("分页获取所有客观题标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字", paramType = "query")
    })
    @GetMapping
    public ResponseEntity listAll(@RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize,
                                  @RequestParam(name = "query", required = false) String query) {
        // pageNum  页码
        // pageSize 每页显示数量
        Page pager = PageHelper.startPage(pageNum, pageSize);
        List<ObjectTagEntity> objectTagEntities = objectTagService.listAll();
        if(objectTagEntities == null || objectTagEntities.size() < 0){
            return new ResponseEntity(400,"暂无数据","");
        }
        return new ResponseEntity(WebUtil.generatePageData(pager, objectTagEntities));
    }

    @ApiOperation("添加标签")
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid AddTagFormat format) {
        objectTagService.save(format.getName());
        return new ResponseEntity("添加标签成功");
    }

    @ApiOperation("删除标签")
    @ApiImplicitParam(name = "object_tag_id", value = "标签id", required = true)
    @DeleteMapping("/{object_tag_id}")
    public ResponseEntity removeByPrimaryKey(@PathVariable("object_tag_id") int objectTagId) {
        objectTagService.removeByPrimaryKey(objectTagId);
        return new ResponseEntity("标签删除成功");
    }

    @ApiOperation("更新标签名")
    @ApiImplicitParam(name = "object_tag_id", value = "标签id", required = true)
    @PutMapping("/{object_tag_id}")
    public ResponseEntity updateTag(@PathVariable("object_tag_id") int objectTagId,
                                    @RequestBody @Valid AddTagFormat format) {
        objectTagService.updateByPrimaryKey(objectTagId, format.getName());
        return new ResponseEntity("标签更新成功");
    }

    @ApiOperation("查询一个标签")
    @ApiImplicitParam(name = "object_tag_id", value = "标签id", required = true)
    @GetMapping("/{object_tag_id}")
    public ResponseEntity getByPrimaryKey(@PathVariable("object_tag_id") int objectTagId) {
        objectTagService.getByPrimaryKey(objectTagId);
        return new ResponseEntity("标签查询成功");
    }
}
