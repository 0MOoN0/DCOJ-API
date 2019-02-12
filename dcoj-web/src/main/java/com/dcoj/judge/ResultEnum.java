package com.dcoj.judge;

/**
 * 编程题判断枚举类
 */
public enum ResultEnum {
    //判断结果 枚举
    AC("Accepted"), WA("Wrong Answer"), RTE("Runtime Error"),
    TLE("Time Limit Exceed"), CE("Compile Error"), SE("Server Error");

    private String name;

    ResultEnum(String name) {
        this.name = name;
    }


}
