package com.dcoj.controller.backstage;


import com.dcoj.entity.ResponseEntity;
import com.dcoj.service.ObjectProblemService;
import com.dcoj.service.ProgramProblemService;
import com.dcoj.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 后台统计分析 控制器
 *
 * @author  Jack Lin
 */
@RestController
@Validated
@Api(tags = "后台统计分析")
@RequestMapping(value = "/backStageStatistics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageStatisticsController {

    @Autowired
    private ObjectProblemService objectProblemService;

    @Autowired
    private ProgramProblemService programProblemService;

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("获取所有统计信息")
    @GetMapping("allStatistics")
    public ResponseEntity allStatistics() {

        //题目类型 0 选择题 1 填空题 2 判断题
        //按题目类型统计，分别统计不同类别题目的数量
        int countSingle = 0;
        int countmultiple = 0 ;//此处为统计填空题
        int countJudge = 0;
        int countProgram = 0;
        countSingle = objectProblemService.countObjectProblemsByType(0);
        countmultiple = objectProblemService.countObjectProblemsByType(1);
        countJudge = objectProblemService.countObjectProblemsByType(2);
        countProgram = programProblemService.countProgramProblems();
        HashMap<String, Integer> programCount  = new HashMap<String,Integer>();
        programCount.put("single", countSingle);
        programCount.put("multiple",0);
        programCount.put("judge", countJudge);
        programCount.put("program",countProgram);

        //分别按照今日、昨天、本月和所有统计新增的题目数（包括编程题和客观题）
        int countAllProblemToday = 0;
        int countAllProblemYesterday = 0;
        int countAllProblemMonth = 0;
        int countAllProblem = 0;

        int countAllProblemTodayObjectProblemPart = 0;
        int countAllProblemTodayProgramProblemPart = 0;
        int countAllProblemYesterdayObjectProblemPart = 0;
        int countAllProblemYesterdayProgramProblemPart = 0;
        int countAllProblemMonthObjectProblemPart = 0;
        int countAllProblemMonthProgramProblemPart = 0;
        int countAllProblemObjectProblemPart = 0;
        int countAllProblemProgramProblemPart = 0;

        //统计今日新增题目数，为编程题新增的题目数加上客观题新增的题目数
        countAllProblemTodayObjectProblemPart = objectProblemService.countObjectProblemsToday();
        countAllProblemTodayProgramProblemPart = programProblemService.countProgramProblemsToday();
        countAllProblemToday = countAllProblemTodayObjectProblemPart + countAllProblemTodayProgramProblemPart;

        //统计昨天新增题目数，为编程题新增的题目数加上客观题新增的题目数
        countAllProblemYesterdayObjectProblemPart = objectProblemService.countObjectProblemsYesterday();
        countAllProblemYesterdayProgramProblemPart = programProblemService.countProgramProblemsYesterday();
        countAllProblemYesterday = countAllProblemYesterdayObjectProblemPart + countAllProblemYesterdayProgramProblemPart ;

        //统计本月新增题目数，为编程题新增的题目数加上客观题新增的题目数
        countAllProblemMonthObjectProblemPart = objectProblemService.countObjectProblemsMonth();
        countAllProblemMonthProgramProblemPart = programProblemService.countProgramProblemsMonth();
        countAllProblemMonth = countAllProblemMonthObjectProblemPart + countAllProblemMonthProgramProblemPart;

        //统计一直以来新增题目数，为编程题新增的题目数加上客观题新增的题目数
        countAllProblemObjectProblemPart = objectProblemService.countObjectProblems();
        countAllProblemProgramProblemPart = programProblemService.countProgramProblems();
        countAllProblem = countAllProblemObjectProblemPart + countAllProblemProgramProblemPart;

        HashMap<String, Integer> count1  = new HashMap<String,Integer>();
        count1.put("today",countAllProblemToday);
        count1.put("yesterday",countAllProblemYesterday);
        count1.put("month",countAllProblemMonth);
        count1.put("total",countAllProblem);

        //分别按照今日、昨天、本月和所有统计新增的学生数量
        int countStudentToday = 0;
        int countStudentYesterday = 0;
        int countStudentMonth = 0;
        int countAllStudent = 0;
        int studentRoleId  = 1 ;
        countStudentToday = userRoleService.countByRoleIdToday(studentRoleId);
        countStudentYesterday = userRoleService.countByRoleIdYesterday(studentRoleId);
        countStudentMonth = userRoleService.countByRoleIdMonth(studentRoleId);
        countAllStudent = userRoleService.countByRoleId(studentRoleId);
        HashMap<String, Integer> count2  = new HashMap<String,Integer>();
        count2.put("today",countStudentToday);
        count2.put("yesterday",countStudentYesterday);
        count2.put("month",countStudentMonth);
        count2.put("total",countAllStudent);

        //分别按照今日、昨天、本月和所有统计新增的教师数量
        int countTeacherToday = 0;
        int countTeacherYesterday = 0;
        int countTeacherMonth = 0;
        int countAllTeacher = 0;
        countTeacherToday = userRoleService.countByRoleIdToday(2);
        countTeacherYesterday = userRoleService.countByRoleIdYesterday(2);
        countTeacherMonth = userRoleService.countByRoleIdMonth(2);
        countAllTeacher = userRoleService.countByRoleId(2);
        HashMap<String, Integer> count3  = new HashMap<String,Integer>();
        count3.put("today",countTeacherToday);
        count3.put("yesterday",countTeacherYesterday);
        count3.put("month",countTeacherMonth);
        count3.put("total",countAllTeacher);

        HashMap<String,HashMap<String,Integer>> data = new HashMap<>();
        data.put("problemType",programCount);
        data.put("problemCount",count1);
        data.put("studentCount",count2);
        data.put("teacherCount",count3);
        return new ResponseEntity(data);
    }
}
