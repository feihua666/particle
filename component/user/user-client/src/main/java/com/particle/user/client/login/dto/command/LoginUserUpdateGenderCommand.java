package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 当前登录用户用户 更新性别指令对象
 * </p>
 *
 * @author yw
 * @since 2023-07-12 18:12:43
 */
@Data
@Schema
public class LoginUserUpdateGenderCommand extends AbstractBaseCommand {

    @NotNull(message = "性别 不能为空")
    @Schema(description = "性别，字典id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long genderDictId;
}
