package com.dcoj.controller.exception;

/**
 * Created by Leon on 2019/2/9.
 */
public class EmailVerifyException extends RuntimeException {

    public EmailVerifyException(){
        super("邮箱认证失败");
    }

    public EmailVerifyException(String message){
        super(message);
    }

}
