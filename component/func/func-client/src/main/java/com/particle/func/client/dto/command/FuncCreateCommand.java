package com.particle.func.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单功能 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@PropValid
@Data
@ApiModel
public class FuncCreateCommand extends AbstractBaseCommand {


    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "编码",required = true)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @NotNull(message = "功能分组id不能为空")
    @ApiModelProperty(value = "功能分组id",required = true)
    private Long funcGroupId;

    @ApiModelProperty("图标")
    private String icon;

    @NotNull(message = "是否禁用不能为空")
    @ApiModelProperty(value = "是否禁用",required = true)
    private Boolean isDisabled;

    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("地址")
    private String url;

    @ApiModelProperty("shiro权限串，多个以逗号分隔")
    private String permissions;

    @NotNull(message = "类型,字典id不能为空")
    @ApiModelProperty(value = "类型,字典id",required = true)
    private Long typeDictId;

    @NotNull(message = "是否展示不能为空")
    @ApiModelProperty("是否展示")
    private Boolean isShow;

    @ApiModelProperty("描述")
    private String remark;

    @NotNull(message = "排序不能为空")
    @ApiModelProperty("排序,默认按该字段升序排序")
    private Integer seq;

    @ApiModelProperty("父级id")
    private Long parentId;

}
