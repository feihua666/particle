package com.particle.tenant.client.tenantfunc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户功能菜单 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@ApiModel
public class TenantFuncCreateCommand extends AbstractBaseCommand {

    @NotNull(message = "功能id 不能为空")
    @ApiModelProperty(value = "功能id",required = true)
    private Long funcId;

    @ApiModelProperty(value = "名称")
    private String name;

    @NotNull(message = "功能应用id 不能为空")
    @ApiModelProperty(value = "功能应用id",required = true)
    private Long funcApplicationId;

    @NotNull(message = "租户id 不能为空")
    @ApiModelProperty(value = "租户id",required = true)
    private Long tenantId;
}
