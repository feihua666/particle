package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 当前登录用户用户 更新昵称指令对象
 * </p>
 *
 * @author yw
 * @since 2023-07-12 18:12:43
 */
@Data
@ApiModel
public class LoginUserUpdateNicknameCommand extends AbstractBaseCommand {

    @NotEmpty(message = "昵称 不能为空")
    @ApiModelProperty(value = "昵称",required = true)
    private String nickname;
}
