package com.dcoj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.controller.format.admin.AddProblemFormat;
import com.dcoj.controller.format.admin.UpdateObjectiveProblemFormat;
import com.dcoj.controller.format.admin.UpdatePragramFormat;
import com.dcoj.entity.ProblemEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ProblemService;
import com.dcoj.util.WebUtil;
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
@RequestMapping(value = "/problem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    //TODO: 4.13 WNAGQING 与测试用例相关的Controller未写

    @ApiOperation("获取该题的所有标签")
    @GetMapping("/{pid}/tags")
    public ResponseEntity listProblemTagsByPid(@PathVariable("pid") int pid) {
       return new ResponseEntity(problemService.listProblemTagsByPid(pid));
    }

    @ApiOperation("删除指定题目")
    @DeleteMapping("/{pid}")
    public ResponseEntity removeByPid(@PathVariable int pid) {
        problemService.removeByPid(pid);
        return new ResponseEntity("题目删除成功");
    }

    @ApiOperation("获取指定一道题目的信息")
    @GetMapping("/{pid}")
    public ResponseEntity getById(@PathVariable int pid) {
        ProblemEntity problemEntity = problemService.getById(pid);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("problem", problemEntity);
        return new ResponseEntity(dataMap);
    }

    @ApiOperation("创建题目")
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid AddProblemFormat format) {
        // 检查标签是否为空
        checkProblemTags(format.getTags());
        // 将format格式的数据保存到ProblemEntity对象里
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setType(format.getType());
        problemEntity.setDescription(format.getDescription());
        problemEntity.setDifficult(format.getDifficult());
        // 如果是编程题，则执行编程题的校验,否则执行客观题的校验。并保存到ProblemEntity对象里
        if (format.getType() == 3){
            checkProgramFormat(format);
            checkProgramSamples(format.getSamples());
            problemEntity.setTitle(format.getTitle());
            problemEntity.setInputFormat(format.getInputFormat());
            problemEntity.setOutputFormat(format.getOutputFormat());
            problemEntity.setSamples(format.getSamples());
            problemEntity.setRunTime(format.getRunTime());
            problemEntity.setMemory(format.getMemory());
        } else{
            checkObjectiveProblemAnswerFormat(format.getAnswer());
            problemEntity.setAnswer(format.getAnswer());
        }
        int pid = problemService.save(format.getTags(), problemEntity);
        return new ResponseEntity("题目添加成功", pid);
    }

    @ApiOperation("更新编程题目")
    @PutMapping("/Program/{pid}")
    public ResponseEntity updateProgram(@PathVariable("pid") int pid,
                                        @RequestBody @Valid UpdatePragramFormat format) {
        ProblemEntity newProblemEntity = new ProblemEntity();
        newProblemEntity.setTitle(format.getTitle());
        newProblemEntity.setDescription(format.getDescription());
        newProblemEntity.setInputFormat(format.getInputFormat());
        newProblemEntity.setOutputFormat(format.getOutputFormat());
        newProblemEntity.setDifficult(format.getDifficult());
        checkProgramSamples(format.getSamples());
        newProblemEntity.setSamples(format.getSamples());
        checkProblemTags(format.getTags());
        WebUtil.assertIsSuccess(format.getRunTime()>0,"运行时间不能小于0");
        newProblemEntity.setRunTime(format.getRunTime());
        newProblemEntity.setMemory(format.getMemory());
        problemService.updateProblem(pid, format.getTags(), newProblemEntity);
        return new ResponseEntity("题目更新成功");
    }

    @ApiOperation("更新客观题目")
    @PutMapping("/ObjectiveProblem/{pid}")
    public ResponseEntity updateObjectiveProblem(@PathVariable("pid") int pid,
                                        @RequestBody @Valid UpdateObjectiveProblemFormat format) {
        ProblemEntity newProblemEntity = new ProblemEntity();
        newProblemEntity.setDescription(format.getDescription());
        newProblemEntity.setDifficult(format.getDifficult());
        checkProblemTags(format.getTags());
        checkObjectiveProblemAnswerFormat(format.getAnswer());
        newProblemEntity.setAnswer(format.getAnswer());
        problemService.updateProblem(pid, format.getTags(), newProblemEntity);
        return new ResponseEntity("题目更新成功");
    }

    /**
     * 检查编程题目样例
     *
     * @param samples 样例
     */
    private void checkProgramSamples(JSONArray samples) {
        WebUtil.assertIsSuccess(samples.size()!=0,"样本不得为空");
        for (Object obj: samples) {
            boolean input = ((JSONObject) obj).containsKey("input");
            boolean output = ((JSONObject) obj).containsKey("output");
            WebUtil.assertIsSuccess(input && output,"样本格式不符");
        }
    }

    /**
     * 检查编程题格式
     *
     * @param format 题目格式对象
     */
    private void checkProgramFormat(AddProblemFormat format){
        WebUtil.assertIsSuccess((format.getTitle() != null&& format.getTitle().trim().length() != 0),
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

    /**
     * 检查客观题答案是否为空
     *
     * @param answer 客观题题目答案
     */
    private void checkObjectiveProblemAnswerFormat(String answer){
        // 判断客观题答案是否为空
        WebUtil.assertIsSuccess((answer != null && answer.trim().length() != 0),
                "客观题答案不能为空");
    }

    /**
     * 检查题目标签是否为空
     *
     * @param tags 标签
     */
    private void checkProblemTags(JSONArray tags) {
            WebUtil.assertIsSuccess(tags.size()!=0,"标签不得为空");
    }
}
