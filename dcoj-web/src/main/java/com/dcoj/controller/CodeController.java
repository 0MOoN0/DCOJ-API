package com.dcoj.controller;

import com.alibaba.fastjson.JSONObject;
import com.dcoj.config.DefaultConfig;
import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.controller.format.index.PreviewSubmitFormat;
import com.dcoj.controller.format.user.UserSubmitCodeFormat;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.TestCaseEntity;
import com.dcoj.judge.JudgeResult;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.security.SessionHelper;
import com.dcoj.service.AsyncJudgeService;
import com.dcoj.service.JudgeService;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.service.TestCasesService;
import com.dcoj.util.JWTUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提交代码相关Controller
 *
 * @author Leon
 */
@RestController
@Valid
@RequestMapping(value = "/code", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CodeController {

    @Autowired
    private TestCasesService testCasesService;

    @Autowired
    private AsyncJudgeService asyncJudgeService;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private ProgramProblemService programProblemService;

    @ApiOperation("测试代码提交")
    @PostMapping
    public ResponseEntity previewSubmit(@RequestBody @Valid PreviewSubmitFormat format) {
        int size = format.getTestCases().size();
        List<TestCaseEntity> testCases = new ArrayList<>(size);
        Integer pid = format.getPid();
        //  获取题目内容
        ProgramProblemEntity programProblemEntity = programProblemService.getByPrimaryKey(pid);
        if (size == 0) { //没有自定义测试用例
            TestCaseEntity testCaseEntity = testCasesService.getOneByPid(pid);
            testCases.add(testCaseEntity);
        } else {
            for (int i = 0; i < size; i++) {
                //从Format中获取获取TestCase，并添加到测试代码测试用例中
                JSONObject obj = format.getTestCases().getJSONObject(i);
                String stdin = obj.getString("stdin");
                String stdout = obj.getString("stdout");
                if (stdout.length() == 0) {
                    throw new WebErrorException("输出字符串不得为空");
                }
                TestCaseEntity testCaseEntity = new TestCaseEntity(0, 0, stdin, stdout);
                testCases.add(testCaseEntity);
            }
        }
        //添加判卷任务
        String judgeId = asyncJudgeService.addTestJudge(format.getSourceCode(), format.getLang(), programProblemEntity.getRunTime(), programProblemEntity.getMemory(), testCases);
        return new ResponseEntity(null, judgeId);
    }

    @ApiOperation("用户判卷提交")
//    @RequiresAuthentication
    @PostMapping("/user")
    public ResponseEntity submitCode(@RequestBody @Valid UserSubmitCodeFormat format, @RequestHeader("token") String token) {
        int pid = format.getPid();
        int examId = format.getExaminationId();
        int gid = format.getGroupId();
        int uid = JWTUtil.getUid(token);
        String sourceCode = format.getSourceCode();
        LanguageEnum lang = format.getLang();
        String id = null;
        if (gid != 0 && examId != 0) {
//            id = asyncJudgeService.addGroupJudge(sourceCode, lang, owner, pid, cid, gid);
        } else if (examId != 0) {
            //TODO 2019.04.15 Leon 试卷提交
//            id = asyncJudgeService.addContestJudge(sourceCode, lang, owner, pid, cid);
        } else {
            id = asyncJudgeService.addProblemJudge(sourceCode, lang, uid, pid);
        }
        return new ResponseEntity(null, id);
    }

    @ApiOperation("根据id获取判卷任务状况")
    @GetMapping("/{id}")
    public ResponseEntity getStatus(@PathVariable("id") String id) {
        JudgeResult result = judgeService.getJudgeResult(id);
        Map<String, Object> map = new HashMap<>(3);
        map.put("response", result.getResponse());
        map.put("id", result.getId());
        map.put("status", result.getStatus().getMessage());
        return new ResponseEntity(map);
    }

}
