package com.particle.role.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-20 16:08:16
 */
@PropValid
@Data
@ApiModel
public class RoleCreateWithTenantIdCommand extends RoleCreateCommand {

    @ApiModelProperty("租户id")
    private Long tenantId;
}
