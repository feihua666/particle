package com.particle.user.client.identifier.dto.command;

import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识 重置用户密码指令对象
 * </p>
 *
 * @author yw
 * @since 2022-12-25
 */
@Data
@ApiModel
public class UserIdentifierResetPasswordCommand extends UserIdentifierPasswordCommand {

    @NotNull(message = "用户登录标识ID不能为空")
    @ApiModelProperty(value = "用户登录标识ID",required = true)
    private Long userIdentifierId;
}
