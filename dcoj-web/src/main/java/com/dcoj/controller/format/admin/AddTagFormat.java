package com.dcoj.controller.format.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "addTagFormat", description = "添加标签格式校验")
public class AddTagFormat {

    /**
     * 标签名
     */
    @NotBlank
    @Length(max = 20)
    @NotNull
    @ApiModelProperty(value = "标签名", name = "name", example = "二叉树")
    private String name;

}
