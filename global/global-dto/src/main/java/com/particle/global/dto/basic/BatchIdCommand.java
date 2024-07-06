package com.particle.global.dto.basic;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 适合于通用的根据id批量处理的情况，如：根据id查询或根据id删除
 * </p>
 *
 * @author yw
 * @since 2024-07-04 14:55:11
 */
@Data
@Schema
public class BatchIdCommand extends Command {

    @NotEmpty(message = "ids 不能为空")
	@Schema(description = "ids",requiredMode = Schema.RequiredMode.REQUIRED)
	private List<Long> ids;

}
