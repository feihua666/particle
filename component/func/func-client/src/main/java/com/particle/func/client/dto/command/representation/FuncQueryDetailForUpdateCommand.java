package com.particle.func.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单功能 通用更新时查询详情指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@ApiModel
public class FuncQueryDetailForUpdateCommand extends AbstractBaseCommand {

    @NotNull
	@ApiModelProperty(value = "id",notes = "")
	private Long id;

}
