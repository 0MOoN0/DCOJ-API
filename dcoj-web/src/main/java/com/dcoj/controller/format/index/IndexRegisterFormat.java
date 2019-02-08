package com.dcoj.controller.format.index;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Leon
 **/
public class IndexRegisterFormat {

    @Email
    @NotNull
    // TODO:临时邮箱认证
    private String email;

    @NotBlank
    @NotNull
    @Length(max = 20)
    private String nickname;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    @Length(max = 13)
    private String studentId;

    @NotNull
    @NotBlank
    @Length(max = 6)
    private String verifyCode;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


