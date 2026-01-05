package com.particle.role.client.roleuserrel.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色用户关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class RoleUserRelWithTenantIdCreateCommand extends RoleUserRelCreateCommand {
    @Schema(description = "租户id")
    private Long tenantId;
}
