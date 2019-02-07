package com.dcoj.controller.exception;

/**
 * @author Leon
 * Web 操作异常
 **/
public class WebErrorException extends RuntimeException {
    public WebErrorException() {
        super("本次操作非法");
    }

    public WebErrorException(String message) {
        super(message);
    }
}
