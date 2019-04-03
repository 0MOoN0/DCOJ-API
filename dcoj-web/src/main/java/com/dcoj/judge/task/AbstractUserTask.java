package com.dcoj.judge.task;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象用户Task
 * @author Leon
 **/
@Getter
@Setter
public class AbstractUserTask extends AbstractBaseTask {

    // Task的拥有者
    private Integer owner;

}
