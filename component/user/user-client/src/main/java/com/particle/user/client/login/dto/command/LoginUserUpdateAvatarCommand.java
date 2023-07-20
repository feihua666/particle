package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 当前登录用户用户 更新头像指令对象
 * </p>
 *
 * @author yw
 * @since 2023-07-12 18:12:43
 */
@Data
@Schema
public class LoginUserUpdateAvatarCommand extends AbstractBaseCommand {

    @NotNull(message = "头像 不能为空")
    @Schema(description = "头像，建议图片相对路径",requiredMode = Schema.RequiredMode.REQUIRED)
    private String avatar;
}
