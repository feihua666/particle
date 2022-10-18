package com.particle.user.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
/**
 * <p>
 * 后台管理用户 通用删除指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@ApiModel
public class UserDeleteCommand extends AbstractBaseCommand {

    @NotNull
	@ApiModelProperty(value = "id",notes = "")
	private Long id;

}
