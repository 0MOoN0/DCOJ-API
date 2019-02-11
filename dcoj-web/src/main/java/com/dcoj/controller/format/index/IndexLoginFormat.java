package com.dcoj.controller.format.index;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Leon
 **/
public class IndexLoginFormat {

    @Email
    private String email;

    @NotBlank
    @Length(max = 13)
    private String studentId;

    @NotBlank
    @NotNull
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
