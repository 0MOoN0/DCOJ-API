package com.dcoj.judge.task;


import com.dcoj.entity.ProblemEntity;

/**
 * @author Leon
 **/
public class ProblemJudgeTask extends AbstractUserTask {

    private int pid;

    private ProblemEntity problemEntity;

    public ProblemEntity getProblemEntity() {
        return problemEntity;
    }

    public void setProblemEntity(ProblemEntity problemEntity) {
        this.problemEntity = problemEntity;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
