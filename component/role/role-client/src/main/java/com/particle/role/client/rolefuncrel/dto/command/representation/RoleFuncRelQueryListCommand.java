package com.particle.role.client.rolefuncrel.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色菜单功能关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class RoleFuncRelQueryListCommand extends AbstractBaseQueryCommand {


    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "功能id")
    private Long funcId;


}
