package com.particle.role.client.dto.command;

import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
@Schema
public class RoleCreateWithTenantIdCommand extends RoleCreateCommand {

    @Schema(description = "租户id")
    private Long tenantId;
}
