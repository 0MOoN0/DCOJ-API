package com.dcoj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.controller.format.admin.ProgramProblemFormat;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.util.WebUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "单个编程题管理")
@RequestMapping(value = "/program-problem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProgramProblemController {

    @Autowired
    private ProgramProblemService programProblemService;

    //TODO: 4.13 WNAGQING 与测试用例相关的Controller未写

    @ApiOperation("获取该题的所有标签")
    @ApiImplicitParam(name = "program_problem_id", value = "编程题id")
    @GetMapping("/tags/{program_problem_id}")
    public ResponseEntity listProblemTagsByPid(@PathVariable("program_problem_id") int programProblemId) {
        return new ResponseEntity(programProblemService.listProgramProblemTagsByPid(programProblemId));
    }

    @ApiOperation("删除指定题目")
    @ApiImplicitParam(name = "program_problem_id", value = "编程题id")
    @DeleteMapping("/{program_problem_id}")
    public ResponseEntity removeByPid(@PathVariable("program_problem_id") int programProblemId) {
        programProblemService.removeByPrimaryKey(programProblemId);
        return new ResponseEntity("题目删除成功");
    }

    @ApiOperation("获取指定一道题目的信息")
    @ApiImplicitParam(name = "program_problem_id", value = "编程题id")
    @GetMapping("/{program_problem_id}")
    public ResponseEntity getByPrimaryKey(@PathVariable("program_problem_id") int programProblemId) {
        ProgramProblemEntity programProblemEntity = programProblemService.getByPrimaryKey(programProblemId);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("program_problem", programProblemEntity);
        return new ResponseEntity(dataMap);
    }

    @ApiOperation("创建题目")
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProgramProblemFormat format) {
        // 将format格式的数据保存到ProblemEntity对象里
        ProgramProblemEntity programProblemEntity = new ProgramProblemEntity();
        programProblemEntity.setDescription(format.getDescription());
        programProblemEntity.setDifficult(format.getDifficult());
        // 检查格式规范
        checkProgramFormat(format);
        checkProgramSamples(format.getSamples());
        programProblemEntity.setTitle(format.getTitle());
        programProblemEntity.setInputFormat(format.getInputFormat());
        programProblemEntity.setOutputFormat(format.getOutputFormat());
        programProblemEntity.setSamples(format.getSamples());
        programProblemEntity.setRunTime(format.getRunTime());
        programProblemEntity.setMemory(format.getMemory());
        int pid = programProblemService.save(format.getTags(), programProblemEntity);
        return new ResponseEntity("题目添加成功", pid);
    }


    @ApiOperation("更新编程题目")
    @ApiImplicitParam(name = "program_problem_id", value = "编程题id")
    @PutMapping("/{program_problem_id}")
    public ResponseEntity updateProgram(@PathVariable("program_problem_id") int programProblemId,
                                        @RequestBody @Valid ProgramProblemFormat format) {
        ProgramProblemEntity newProgramProblemEntity = new ProgramProblemEntity();
        newProgramProblemEntity.setTitle(format.getTitle());
        newProgramProblemEntity.setDescription(format.getDescription());
        newProgramProblemEntity.setInputFormat(format.getInputFormat());
        newProgramProblemEntity.setOutputFormat(format.getOutputFormat());
        newProgramProblemEntity.setDifficult(format.getDifficult());
        // 检查格式规范
        checkProgramSamples(format.getSamples());
        newProgramProblemEntity.setSamples(format.getSamples());
        // checkProblemTags(format.getTags());
        WebUtil.assertIsSuccess(format.getRunTime() > 0, "运行时间不能小于0");
        newProgramProblemEntity.setRunTime(format.getRunTime());
        newProgramProblemEntity.setMemory(format.getMemory());
        programProblemService.updateProblemAndTags(programProblemId, format.getTags(), newProgramProblemEntity);
        return new ResponseEntity("题目更新成功");
    }

    /**
     * 检查编程题目样例
     *
     * @param samples 样例
     */
    private void checkProgramSamples(JSONArray samples) {
        WebUtil.assertIsSuccess(samples.size() != 0, "样本不得为空");
        for (Object obj : samples) {
            boolean input = ((JSONObject) obj).containsKey("input");
            boolean output = ((JSONObject) obj).containsKey("output");
            WebUtil.assertIsSuccess(input && output, "样本格式不符");
        }
    }

    /**
     * 检查编程题格式
     *
     * @param format 题目格式对象
     */
    private void checkProgramFormat(ProgramProblemFormat format) {
        WebUtil.assertIsSuccess((format.getTitle() != null && format.getTitle().trim().length() != 0),
                "标题不得为空");
        WebUtil.assertIsSuccess(format.getDescription() != null,
                "题目描述不能为空");
        WebUtil.assertIsSuccess(format.getInputFormat() != null,
                "输入规范不得为空");
        WebUtil.assertIsSuccess(format.getOutputFormat() != null,
                "输出规范不得为空");
        WebUtil.assertIsSuccess(format.getRunTime() > 0,
                "运行时间不能小于0");
        WebUtil.assertIsSuccess(format.getMemory() > 0 && format.getMemory() <= 256,
                "运行内存不能为0且不能超过256");
    }

}
