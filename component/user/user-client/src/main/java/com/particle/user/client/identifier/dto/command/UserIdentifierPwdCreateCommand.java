package com.particle.user.client.identifier.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户密码 通用创建指令对象
 * 必要时可配合 {@link UserIdentifierPwdCommand} 一起使用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class UserIdentifierPwdCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "用户id 不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private Long userId;

    @NotNull(message = "用户标识id 不能为空")
    @ApiModelProperty(value = "用户标识id",required = true)
    private Long identifierId;

    @ApiModelProperty("分组标识")
    private String groupFlag;


}
