package com.particle.role.client.rolefuncrel.dto.command.representation;

import com.particle.common.client.dto.command.CommonBatchIdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色菜单功能关系 根据funcIds查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-29 15:18:22
 */
@Data
@Schema
public class RoleFuncRelQueryListByFuncIdsCommand extends CommonBatchIdCommand {

}
