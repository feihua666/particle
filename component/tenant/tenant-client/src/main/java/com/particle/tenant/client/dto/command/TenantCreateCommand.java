package com.particle.tenant.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Data
@ApiModel
public class TenantCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "租户编码 不能为空")
        @ApiModelProperty(value = "租户编码",required = true)
    private String code;


    @NotEmpty(message = "租户名称 不能为空")
        @ApiModelProperty(value = "租户名称",required = true)
    private String name;


    @NotNull(message = "是否禁用 不能为空")
        @ApiModelProperty(value = "是否禁用",required = true)
    private Boolean isDisabled;


    @ApiModelProperty(value = "禁用原因")
    private String disabledReason;


    @ApiModelProperty(value = "联系人姓名")
    private String contactUserName;


    @ApiModelProperty(value = "联系人邮箱")
    private String contactUserEmail;


    @ApiModelProperty(value = "联系人电话")
    private String contactUserPhone;


    @ApiModelProperty(value = "租户域名")
    private String tenantDomain;


    @ApiModelProperty(value = "租户主题")
    private String tenantTheme;


    @ApiModelProperty(value = "租户默认的页面路由")
    private String tenantDefaultRoute;

    @ApiModelProperty("租户logo地址")
    private String tenantLogoUrl;

    @ApiModelProperty(value = "额外配置json")
    private String configJson;


    @ApiModelProperty(value = "描述")
    private String remark;





















}
