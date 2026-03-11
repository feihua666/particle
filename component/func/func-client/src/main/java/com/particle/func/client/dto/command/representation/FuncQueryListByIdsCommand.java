package com.particle.func.client.dto.command.representation;

import com.particle.common.client.dto.command.CommonBatchIdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 菜单功能 根据ids查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-29 14:21:41
 */
@Data
@Schema
public class FuncQueryListByIdsCommand extends CommonBatchIdCommand {

    @Schema(description = "是否禁用")
    private Boolean isDisabled;
}
