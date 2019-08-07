package com.dcoj.controller;

import com.dcoj.controller.format.user.UserObjectJudgeFormat;
import com.dcoj.dao.ObjectProblemUserMapper;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ObjectProblemUserService;
import com.dcoj.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 客观题判卷控制器
 *
 * @author Leon
 */
@RestController
@RequestMapping(value = "/object-judge",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ObjectJudgeController {

    @Autowired
    private ObjectProblemService objectProblemService;

    @Autowired
    private ObjectProblemUserService objectProblemUserService;

    @PostMapping
    public ResponseEntity objectProblemJudge(@Valid UserObjectJudgeFormat userObjectJudgeFormat,
                                             @RequestHeader("authorization") String token){
        int uid = JWTUtil.getUid(token);
        int i = objectProblemService.judgeObjectProblem(userObjectJudgeFormat.getPid(), userObjectJudgeFormat.getAnswer());
        // TODO Leon 20190528：更新客观题状态
        objectProblemUserService.insertOrUpdate(userObjectJudgeFormat.getPid(), uid, (byte) i);
        // TODO Leon 20190528：Update User Log
        return null;
    }

}
