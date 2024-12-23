package com.particle.user.client.identifier.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 根据用户登录标识 重置用户密码指令对象
 * </p>
 *
 * @author yw
 * @since 2022-12-25
 */
@Data
@Schema
public class UserIdentifierResetPasswordCommand extends UserIdentifierPwdCommand {

    @NotNull(message = "用户登录标识ID不能为空")
    @Schema(description = "用户登录标识ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userIdentifierId;
}
