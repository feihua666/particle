package com.particle.role.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class RoleUpdateCommand extends AbstractBaseUpdateCommand {

    @NotEmpty(message = "角色编码不能为空")
    @ApiModelProperty("角色编码")
    private String code;

    @NotEmpty(message = "角色名称不能为空")
    @ApiModelProperty("角色名称")
    private String name;

    @NotNull(message = "是否禁用不能为空")
    @ApiModelProperty(value = "是否禁用",required = true)
    private Boolean isDisabled;


    /**
     * 禁用时，禁用原因必填
     */
    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("父级")
    private Long parentId;
}
