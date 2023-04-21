package com.particle.role.client.roleuserrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色用户关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class RoleUserRelWithTenantIdCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private Long userId;

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id",required = true)
    private Long roleId;

    @ApiModelProperty("租户id")
    private Long tenantId;
}
