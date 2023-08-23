package com.particle.openplatform.client.app.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 开放平台应用 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Data
@Schema
public class OpenplatformAppCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "名称 不能为空")
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "appId 不能为空")
    @Schema(description = "appId",requiredMode = Schema.RequiredMode.REQUIRED)
    private String appId;


    @Schema(description = "归属用户id")
    private Long ownerUserId;


    @Schema(description = "归属客户id")
    private Long ownerCustomerId;


    @NotEmpty(message = "请求算法与密钥等相关配置 不能为空")
    @Schema(description = "请求算法与密钥等相关配置",requiredMode = Schema.RequiredMode.REQUIRED)
    private String requestAlgorithmSecretJson;


    @Schema(description = "响应算法与密钥等相关配置")
    private String responseAlgorithmSecretJson;


    @Schema(description = "访问范围配置")
    private String scopes;


    @Schema(description = "计费id")
    private Long openplatformOpenapiFeeId;

	@Schema(description = "是否禁用")
	private Boolean isDisabled;

	@Schema(description = "禁用原因")
	private String disabledReason;

	@Schema(description = "是否检查签名,主要用于在oauth2 token时可以不检查")
	private Boolean isCheckSignature;









}