package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class LoginUserUpdateGenderCommand extends AbstractBaseCommand {

    @NotNull(message = "性别 不能为空")
    @ApiModelProperty(value = "性别，字典id",required = true)
    private Long genderDictId;
}
