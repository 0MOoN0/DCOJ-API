package com.dcoj.judge.task;

/**
 * 抽象用户Task
 * @author Leon
 **/
public class AbstractUserTask extends AbstractBaseTask {

    // Task的拥有者
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
