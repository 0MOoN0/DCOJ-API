package com.dcoj.controller.format.index;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 忘记密码 格式校验
 *
 * @author WANGQING
 */
@Data
public class ForgetPasswordFormat {

    @NotNull
    private Integer userId;

    @NotBlank(message = "密码不能为空")
    @Length(min = 32,max = 32)
    private String oldPassword;

    @NotBlank(message = "密码不能为空")
    @Length(min = 32,max = 32)
    private String newPassword;


}
