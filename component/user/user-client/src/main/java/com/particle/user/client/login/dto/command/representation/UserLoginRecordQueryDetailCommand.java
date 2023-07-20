package com.particle.user.client.login.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户登录记录 通用仅展示用查询详情指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@Schema
public class UserLoginRecordQueryDetailCommand extends AbstractBaseCommand {

    @NotNull
	@Schema(description = "id")
	private Long id;

}
