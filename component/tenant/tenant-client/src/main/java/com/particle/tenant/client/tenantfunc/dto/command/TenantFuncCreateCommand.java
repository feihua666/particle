package com.particle.tenant.client.tenantfunc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 租户功能菜单 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@Schema
public class TenantFuncCreateCommand extends AbstractBaseCommand {

    @NotNull(message = "功能id 不能为空")
    @Schema(description = "功能id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long funcId;

    @Schema(description = "名称")
    private String name;

    @NotNull(message = "功能应用id 不能为空")
    @Schema(description = "功能应用id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long funcApplicationId;

    @NotNull(message = "租户id 不能为空")
    @Schema(description = "租户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tenantId;
}
