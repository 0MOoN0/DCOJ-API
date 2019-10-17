package com.dcoj.controller;

import com.dcoj.entity.ExaminationProblemEntity;
import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ExaminationProblemService;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ProgramProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxw
 * @Desriiption:
 */
@RestController
@RequestMapping(value = "/exam", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExaminationProblemController {

    @Autowired
    private ExaminationProblemService examinationProblemService;

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private ObjectProblemService objectProblemService;

    /**
     *  查询所有试卷和题目之间的关系
     * @return
     */
    @GetMapping("/papers")
    public ResponseEntity findAll(){
        return new ResponseEntity(examinationProblemService.listAll());
    }

    /**
     *  根据试卷id查询所对应的题目   1：编程题  2：客观题
     * @param examId
     * @return
     */
    @GetMapping("/{exam_id}")
    public ResponseEntity listById(@PathVariable("exam_id")int examId){
        List<Map<String, Object>> programProblemEntityList = programProblemService.listByExamIdAndType(examId);
        List<Map<String, Object>> objectProblemEntityList = objectProblemService.listByExamIdAndType(examId);
        Map<Integer, List<?>> map = new HashMap<>();
        map.put(1,programProblemEntityList);
        map.put(2,objectProblemEntityList);
        return new ResponseEntity(map);
    }

    /**
     *  批量保存
     * @param examinationProblemEntityList
     * @return
     */
    @PostMapping
    public int saveAll(@RequestBody List<ExaminationProblemEntity> examinationProblemEntityList){
        return examinationProblemService.saveAll(examinationProblemEntityList);
    }

}
