package com.dcoj.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zxw
 * @Desriiption:  类别参数
 */
@Getter
@Setter
@ToString
public class CateParam {
    private Integer id;

    @NotBlank(message = "类别名称不可以为空")
    @Length(max = 15, min = 2, message = "类别名称长度需要在2-15个字之间")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "类别展示顺序不可以为空")
    private Integer seq;

    @Length(max = 150, message = "备注的长度需要在150个字以内")
    private String remark;
}
