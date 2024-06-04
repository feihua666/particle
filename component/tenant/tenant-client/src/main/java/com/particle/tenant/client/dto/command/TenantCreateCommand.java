package com.particle.tenant.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Data
@Schema
public class TenantCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "租户编码 不能为空")
    @Schema(description = "租户编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "租户名称 不能为空")
    @Schema(description = "租户名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "是否禁用 不能为空")
    @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;


    @Schema(description = "禁用原因")
    private String disabledReason;


    @Schema(description = "姓名，仅作为联系人，展示使用")
    private String userName;


    @Schema(description = "邮箱，仅作为联系人邮箱，展示使用")
    private String email;


    @Schema(description = "手机号，仅作为联系人手机号，展示使用")
    private String mobile;


    @Schema(description = "租户域名")
    private String tenantDomain;


    @Schema(description = "租户主题")
    private String tenantThemeJson;


    @Schema(description = "租户默认的页面路由")
    private String tenantDefaultRouteJson;

    @NotNull(message = "是否正式 不能为空")
    @Schema(description = "是否正式，1=正式，0=试用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isFormal;

    @Schema(description = "用户数量限制")
    private Integer userLimitCount;

    @Schema(description = "生效日期，从什么时候开始生效")
    private LocalDateTime effectiveAt;

    @Schema(description = "过期时间，从什么时候失效")
    private LocalDateTime expireAt;

    @NotNull(message = "主用户 不能为空")
    @Schema(description = "主用户，一般该用户为租户的超级管理员",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long masterUserId;

    @Schema(description = "租户logo地址")
    private String tenantLogoJson;

	@Schema(description = "图标,支持base64编码,支持http://开头的图片地址,支持本地文件路径地址，支持classpath文件路径")
	private String tenantFaviconJson;

    @Schema(description = "额外配置json")
    private String configJson;


    @Schema(description = "描述")
    private String remark;


}