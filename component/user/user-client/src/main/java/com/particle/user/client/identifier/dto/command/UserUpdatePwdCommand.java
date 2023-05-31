package com.particle.user.client.identifier.dto.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户 找回密码 指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-30 17:24:46
 */
@Data
@ApiModel
public class UserUpdatePwdCommand extends UserIdentifierPwdCommand {

    @NotEmpty(message = "原密码不能为空")
    @ApiModelProperty(value = "原密码",required = true)
    private String oldPassword;
}
