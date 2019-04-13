package com.dcoj.controller.format.admin;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加标签格式校验
 *
 * @author WANGQING
 **/
@Getter
@Setter
public class AddTagFormat {

    /** 标签名 */
    @NotBlank
    @Length(max = 20)
    @NotNull
    private String name;

}
