package com.particle.oauth2authorization.client.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * oauth2客户端 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Data
@Schema
public class Oauth2RegisteredClientUpdateCommand extends AbstractBaseUpdateCommand {

	@Schema(description = "是否重新生成客户端秘钥")
	private Boolean isChangeClientSecret = false;


	@SetNullWhenNull
	@Schema(description = "客户端秘钥的过期时间")
	private LocalDateTime clientSecretExpiresAt;


	@NotEmpty(message = "客户端名称 不能为空")
	@Schema(description = "客户端名称", requiredMode = Schema.RequiredMode.REQUIRED)
	private String clientName;


	@NotEmpty(message = "客户端的身份验证方法 不能为空")
	@Schema(description = "客户端的身份验证方法", requiredMode = Schema.RequiredMode.REQUIRED)
	private String clientAuthenticationMethods;


	@NotEmpty(message = "客户端支持的授权类型 不能为空")
	@Schema(description = "客户端支持的授权类型", requiredMode = Schema.RequiredMode.REQUIRED)
	private String authorizationGrantTypes;

	@SetNullWhenNull
	@Schema(description = "客户端重定向URI")
	private String redirectUris;


	@NotEmpty(message = "客户端的访问范围 不能为空")
	@Schema(description = "客户端的访问范围", requiredMode = Schema.RequiredMode.REQUIRED)
	private String scopes;


	@NotEmpty(message = "客户端的设置 不能为空")
	@Schema(description = "客户端的设置", requiredMode = Schema.RequiredMode.REQUIRED)
	private String clientSettings;


	@NotEmpty(message = "令牌的设置 不能为空")
	@Schema(description = "令牌的设置", requiredMode = Schema.RequiredMode.REQUIRED)
	private String tokenSettings;

}
