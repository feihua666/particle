package com.particle.role.client.roleuserrel.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色用户关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class RoleUserRelPageQueryCommand extends AbstractBasePageQueryCommand {


    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "角色id")
    private Long roleId;


}
