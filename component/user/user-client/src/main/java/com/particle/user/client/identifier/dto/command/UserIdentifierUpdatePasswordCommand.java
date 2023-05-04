package com.particle.user.client.identifier.dto.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户登录标识 修改用户密码指令对象
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 17:13
 */
@Data
@ApiModel
public class UserIdentifierUpdatePasswordCommand extends UserIdentifierResetPasswordCommand{

	@NotEmpty(message = "原密码不能为空")
	@ApiModelProperty(value = "原密码",required = true)
	private String oldPassword;
}
