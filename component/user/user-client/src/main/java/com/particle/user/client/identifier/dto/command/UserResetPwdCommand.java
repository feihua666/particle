package com.particle.user.client.identifier.dto.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户 重置用户密码指令对象
 * </p>
 *
 * @author yw
 * @since 2022-12-25
 */
@Data
@ApiModel
public class UserResetPwdCommand extends UserIdentifierPwdCommand {

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID",required = true)
    private Long userId;
}
