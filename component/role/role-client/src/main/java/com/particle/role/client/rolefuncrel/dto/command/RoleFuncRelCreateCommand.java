package com.particle.role.client.rolefuncrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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


    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("功能id")
    private Long funcId;


}
