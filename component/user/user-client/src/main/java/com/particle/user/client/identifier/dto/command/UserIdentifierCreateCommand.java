package com.particle.user.client.identifier.dto.command;

import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class UserIdentifierCreateCommand extends UserIdentifierSimpleCreateCommand {


    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID",required = true)
    private Long userId;

}
