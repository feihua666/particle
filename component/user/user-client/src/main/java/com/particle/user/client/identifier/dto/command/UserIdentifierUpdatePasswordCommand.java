package com.particle.user.client.identifier.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 根据用户登录标识 修改用户密码指令对象
 * 需要原密码匹配
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 17:13
 */
@Data
@Schema
public class UserIdentifierUpdatePasswordCommand extends UserIdentifierResetPasswordCommand {

	@NotEmpty(message = "原密码不能为空")
	@Schema(description = "原密码",requiredMode = Schema.RequiredMode.REQUIRED)
	private String oldPassword;
}
