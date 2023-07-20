package com.particle.user.client.identifier.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识 邮箱绑定创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@PropValid
@Data
@Schema
public class UserIdentifierCreateEmailBindCommand extends AbstractBaseCommand {

    @Email(message = "邮箱格式不正确")
    @NotEmpty(message = "邮箱不能为空")
    @Schema(description = "邮箱",requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

}
