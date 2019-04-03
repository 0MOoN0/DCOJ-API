package com.dcoj.controller.exception;

/**
 *
 * @author WANGQING
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("禁止访问");
    }

    public ForbiddenException(String msg) {
        super(msg);
    }
}
