package com.particle.role.client.rolefuncrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 角色菜单功能关系 删除指令对象
 * </p>
 *
 * @author yw
 * @since 删除
 */
@Data
@Schema
public class RoleFuncRelDeleteWithTenantIdCommand extends AbstractBaseCommand {

    @Schema(description = "租户id")
    private Long tenantId;

    /**
     * 排除的 funcIds，这些funcId 的权限不删除
     * 注意：为空时将会清空所有数据
     */
    @Schema(description = "排除的 funcIds")
    private List<Long> excludeFuncIds;
}
