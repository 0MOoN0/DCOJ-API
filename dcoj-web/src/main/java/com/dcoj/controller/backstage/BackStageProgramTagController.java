package com.dcoj.controller.backstage;

import com.dcoj.controller.format.admin.AddTagFormat;
import com.dcoj.entity.ObjectTagEntity;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.ProgramTagEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProgramTagService;
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
 * 编程题标签管理 控制器
 *
 * @author Jack Lin
 *
 */
@RestController
@Validated
@Api(tags = "编程题标签管理")
@RequestMapping(value = "/backStageProgramTag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageProgramTagController {

    @Autowired
    private ProgramTagService programTagService;

    @ApiOperation("分页获取所有编程题标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字(标题)", paramType = "query")
    })
    @GetMapping
    public ResponseEntity listAll(@RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize,
                                  @RequestParam(name = "query", required = false) String query) {
        // pageNum  页码
        // pageSize 每页显示数量
        Page pager = PageHelper.startPage(pageNum, pageSize);
        List<ProgramTagEntity> programTagEntities = programTagService.listAll();
        if(programTagEntities == null || programTagEntities.size() < 0){
            return new ResponseEntity(400,"暂无数据","");
        }
        return new ResponseEntity(WebUtil.generatePageData(pager, programTagEntities));
    }

    @ApiOperation("添加标签")
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid AddTagFormat format) {
        programTagService.save(format.getName());
        return new ResponseEntity("添加标签成功");
    }

    @ApiOperation("删除标签")
    @ApiImplicitParam(name = "program_tag_d", value = "标签id")
    @DeleteMapping("/{program_tag_d}")
    public ResponseEntity removeById(@PathVariable("program_tag_d") int programTagId) {
        programTagService.removeByPrimaryKey(programTagId);
        return new ResponseEntity("标签删除成功");
    }

    @ApiOperation("更新标签名")
    @ApiImplicitParam(name = "program_tag_d", value = "标签id")
    @PutMapping("/{program_tag_d}")
    public ResponseEntity updateTag(@PathVariable("program_tag_d") int programTagId,
                                    @RequestBody @Valid AddTagFormat format) {
        programTagService.updateByPrimaryKey(programTagId, format.getName());
        return new ResponseEntity("标签更新成功");
    }
}
