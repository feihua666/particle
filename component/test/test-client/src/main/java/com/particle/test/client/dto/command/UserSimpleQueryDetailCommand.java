package com.particle.test.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 简单用户 通用仅展示用查询详情指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Data
@ApiModel
public class UserSimpleQueryDetailCommand extends AbstractBaseCommand {

    @NotNull
	@ApiModelProperty(value = "id",notes = "")
	private Long id;

}
