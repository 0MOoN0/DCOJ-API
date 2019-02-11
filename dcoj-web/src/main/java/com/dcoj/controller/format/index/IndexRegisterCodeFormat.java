package com.dcoj.controller.format.index;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Created by Leon on 2019/2/10.
 */
public class IndexRegisterCodeFormat {

    @Email
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
