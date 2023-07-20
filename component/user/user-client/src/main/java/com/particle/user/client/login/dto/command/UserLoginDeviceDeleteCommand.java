package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class UserLoginDeviceDeleteCommand extends AbstractBaseCommand {

    @NotNull
	@Schema(description = "id")
	private Long id;

}
