package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 当前登录用户用户 更新姓名指令对象
 * </p>
 *
 * @author yw
 * @since 2023-07-12 18:12:43
 */
@Data
@Schema
public class LoginUserUpdateNameCommand extends AbstractBaseCommand {

    @NotEmpty(message = "姓名 不能为空")
    @Schema(description = "姓名，真实姓名",required = true)
    private String name;
}
