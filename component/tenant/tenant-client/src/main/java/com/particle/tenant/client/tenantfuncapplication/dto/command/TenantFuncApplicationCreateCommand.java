package com.particle.tenant.client.tenantfuncapplication.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户功能应用 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Data
@Schema
public class TenantFuncApplicationCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "功能应用id 不能为空")
    @Schema(description = "功能应用id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long funcApplicationId;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "应用主题")
    private String applicationTheme;


    @Schema(description = "应用默认的页面路由")
    private String applicationDefaultRoute;

    @Schema(description = "应用logo地址")
    private String applicationLogoUrl;

    @Schema(description = "应用图标地址")
    private String applicationIconUrl;

    @Schema(description = "额外配置json")
    private String configJson;


    @NotNull(message = "租户id 不能为空")
    @Schema(description = "租户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tenantId;


}
