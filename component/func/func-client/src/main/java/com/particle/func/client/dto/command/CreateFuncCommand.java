package com.particle.func.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 菜单创建指令
 * @program: particle
 * @description:
 * @author: 许宝华
 * @create: 2022-07-02 19:58
 */

@Data
@ApiModel(value="创建菜单指令")
public class CreateFuncCommand extends AbstractBaseCommand {
    @NotEmpty(message="编码不能为空")
    @ApiModelProperty(value = "编码，模糊查询",required = true)
    private String code;

    @NotEmpty(message="名称不能为空")
    @ApiModelProperty(value = "名称，模糊查询",required = true)
    private String name;

    @ApiModelProperty(value = "功能分组id")
    private String funcGroupId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "shiro权限串，多个以逗号分隔")
    private String permissions;

    @NotEmpty(message="类型不能为空")
    @ApiModelProperty(value = "类型,字典id",required = true)
    private String typeDictId;

    @ApiModelProperty(value = "描述")
    private String remark;

    @NotNull(message="排序不能为空")
    @ApiModelProperty(value = "排序,默认按该字段升序排序",required = true)
    private Integer seq;

    @ApiModelProperty(value = "父级id")
    private String parentId;
}
