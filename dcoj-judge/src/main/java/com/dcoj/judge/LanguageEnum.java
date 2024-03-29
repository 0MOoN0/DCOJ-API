package com.dcoj.judge;

/**
 * 判卷语言枚举类型
 *
 * @author Leon
 **/
public enum LanguageEnum {
    JAVA8("Java8"), PYTHON27("Python2.7"), PYTHON35("Python3.5"), CPP("C++"), C("C");

    private String name;

    private LanguageEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
