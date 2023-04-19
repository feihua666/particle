package com.particle.tenant.client.tenantfuncapplication.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户功能应用 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Data
@ApiModel
public class TenantFuncApplicationUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "功能应用id 不能为空")
    @ApiModelProperty(value = "功能应用id",required = true)
    private Long funcApplicationId;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "应用主题")
    private String applicationTheme;


    @ApiModelProperty(value = "应用默认的页面路由")
    private String applicationDefaultRoute;

    @ApiModelProperty("应用logo地址")
    private String applicationLogoUrl;

    @ApiModelProperty("应用图标地址")
    private String applicationIconUrl;

    @ApiModelProperty(value = "额外配置json")
    private String configJson;

    @NotNull(message = "租户id 不能为空")
    @ApiModelProperty(value = "租户id",required = true)
    private Long tenantId;
}
