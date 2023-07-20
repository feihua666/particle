package com.particle.tenant.client.tenantfunc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户功能菜单 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@Schema
public class TenantFuncUpdateCommand extends AbstractBaseUpdateCommand {

    @NotNull(message = "功能id 不能为空")
    @Schema(description = "功能id",required = true)
    private Long funcId;

    @Schema(description = "名称")
    private String name;

    @NotNull(message = "功能应用id 不能为空")
    @Schema(description = "功能应用id",required = true)
    private Long funcApplicationId;

    @NotNull(message = "租户id 不能为空")
    @Schema(description = "租户id",required = true)
    private Long tenantId;
}
