package com.dcoj.judge.judger;


import com.dcoj.judge.entity.ResponseEntity;

/**
 * @author Smith
 **/
abstract class AbstractJudger {
    JudgerApi judgerApi;

    AbstractJudger(JudgerApi judgerApi) {
        this.judgerApi = judgerApi;
    }

    abstract ResponseEntity judge();
}
