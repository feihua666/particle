package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @Schema(description = "性别，字典id",required = true)
    private Long genderDictId;
}
