package com.dcoj.controller.format.index;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 登录索引 格式校验
 *
 * @author WangQing
 */
@Data
public class IndexLoginFormat {

    @NotBlank(message = "用户名不能为空")
    @Length(max = 32)
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
