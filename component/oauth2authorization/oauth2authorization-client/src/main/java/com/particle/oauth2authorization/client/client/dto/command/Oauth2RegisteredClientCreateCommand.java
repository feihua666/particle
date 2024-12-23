package com.particle.oauth2authorization.client.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * oauth2客户端 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Data
@Schema
public class Oauth2RegisteredClientCreateCommand extends AbstractBaseCommand {

	@Schema(description = "客户端秘钥的过期时间")
	private LocalDateTime clientSecretExpiresAt;


	@NotEmpty(message = "客户端名称 不能为空")
	@Schema(description = "客户端名称", requiredMode = Schema.RequiredMode.REQUIRED)
	private String clientName;

	@Schema(description = "客户端的身份验证方法，多个以逗号分隔")
	private String clientAuthenticationMethods;


	@NotEmpty(message = "客户端支持的授权类型 不能为空")
	@Schema(description = "客户端支持的授权类型，多个以逗号分隔", requiredMode = Schema.RequiredMode.REQUIRED)
	private String authorizationGrantTypes;



	@Schema(description = "客户端重定向URI，多个以逗号分隔，authorizationGrantTypes 包含 authorization_code 时必填")
	private String redirectUris;


	@NotEmpty(message = "客户端的访问范围 不能为空")
	@Schema(description = "客户端的访问范围", requiredMode = Schema.RequiredMode.REQUIRED)
	private String scopes;


	@Schema(description = "客户端的设置，这是一个json对象数据结构")
	private String clientSettings;

	@Schema(description = "令牌的设置，这是一个json对象数据结构")
	private String tokenSettings;

}
