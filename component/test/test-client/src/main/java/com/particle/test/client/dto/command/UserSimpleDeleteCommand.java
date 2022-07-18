package com.particle.test.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
/**
 * <p>
 * 简单用户 通用删除指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Data
@ApiModel(value="简单用户 通用删除指令对象")
public class UserSimpleDeleteCommand extends AbstractBaseCommand {

    @NotNull
	@ApiModelProperty(value = "id",notes = "")
	private Long id;

}
