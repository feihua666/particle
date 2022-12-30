package com.particle.role.client.rolefuncrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色菜单功能关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class RoleFuncRelCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id",required = true)
    private Long roleId;

    @NotNull(message = "功能id不能为空")
    @ApiModelProperty(value = "功能id",required = true)
    private Long funcId;


}
