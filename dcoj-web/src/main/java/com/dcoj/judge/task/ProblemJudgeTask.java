package com.dcoj.judge.task;


import com.dcoj.entity.ProgramProblemEntity;

/**
 * @author Leon
 **/
public class ProblemJudgeTask extends AbstractUserTask {

    private int pid;

    private ProgramProblemEntity programProblemEntity;

    public ProgramProblemEntity getProgramProblemEntity() {
        return programProblemEntity;
    }

    public void setProgramProblemEntity(ProgramProblemEntity programProblemEntity) {
        this.programProblemEntity = programProblemEntity;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
