package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
/**
 * <p>
 * 用户登录设备 通用删除指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@ApiModel
public class UserLoginDeviceDeleteCommand extends AbstractBaseCommand {

    @NotNull
	@ApiModelProperty(value = "id",notes = "")
	private Long id;

}