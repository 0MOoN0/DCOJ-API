package com.dcoj.exam;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Leon
 */
@Data
public class ExamAutoTask implements Delayed {
    private int examAutoTaskId;
    private Long judgeTime;
    private ExamJudgeStatus examJudgeStatus;

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(judgeTime - System.currentTimeMillis(), TimeUnit.SECONDS);
    }
    @Override
    public int compareTo(Delayed o) {
        if(o == this){
            return 0;
        }
        if (o instanceof ExamAutoTask){
            ExamAutoTask otherAutoTask = (ExamAutoTask) o;
            long judgeTime = otherAutoTask.getJudgeTime();
            return (int) (this.judgeTime - judgeTime);
        }
        return 0;
    }
}
