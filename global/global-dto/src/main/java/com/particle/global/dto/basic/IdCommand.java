package com.particle.global.dto.basic;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 适合于通用的根据id处理的情况，如：根据id查询或根据id删除
 * </p>
 *
 * @author yw
 * @since 2024-07-04 14:53:58
 */
@Data
@Schema
public class IdCommand extends Command {

	@Min(value = 1,message = "id 不能小于1")
    @NotNull(message = "id不能为空")
	@Schema(description = "id",requiredMode = Schema.RequiredMode.REQUIRED)
	private Long id;

	public static IdCommand create(Long id){
		IdCommand idCommand = new IdCommand();
		idCommand.id = id;
		return idCommand;
	}

}
