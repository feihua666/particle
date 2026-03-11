package com.particle.role.client.dto.command.representation;

import com.particle.common.client.dto.command.CommonBatchIdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色 根据角色ids查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-29 13:00:58
 */
@Data
@Schema
public class RoleQueryListByRoleIdsCommand extends CommonBatchIdCommand {

    @Schema(description = "是否禁用")
    private Boolean isDisabled;
}
