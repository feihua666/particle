package com.particle.user.client.identifier.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.Mobile;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
@ApiModel
public class UserIdentifierCreateMobilelBindCommand extends AbstractBaseCommand {

    @Mobile(message = "手机号格式不正确")
    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号",required = true)
    private String mobile;

}
