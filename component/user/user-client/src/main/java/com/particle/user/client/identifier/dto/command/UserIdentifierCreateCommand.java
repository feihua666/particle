package com.particle.user.client.identifier.dto.command;

import com.particle.global.validation.props.PropValid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户登录标识 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@PropValid
@Data
@Schema
public class UserIdentifierCreateCommand extends UserIdentifierSimpleCreateCommand {


    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

}
