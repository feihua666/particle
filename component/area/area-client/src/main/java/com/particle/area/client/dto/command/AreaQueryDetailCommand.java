package com.particle.area.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 区域 通用仅展示用查询详情指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Data
@ApiModel(value="区域 通用仅展示用查询详情指令对象")
public class AreaQueryDetailCommand extends AbstractBaseCommand {


	/**
	 * 主键
	 */
	@ApiModelProperty(value = "id",notes = "")
	private Long id;

}
