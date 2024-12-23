package com.particle.user.client.identifier.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 用户 找回密码 指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-30 17:24:46
 */
@Data
@Schema
public class UserUpdatePwdCommand extends UserIdentifierPwdCommand {

    @NotEmpty(message = "原密码不能为空")
    @Schema(description = "原密码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String oldPassword;
}
