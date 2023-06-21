package com.particle.common.client.dto.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 适合于通用的根据id批量处理的情况，如：根据id查询或根据id删除
 * </p>
 *
 * @author yw
 * @since 2023-06-21 10:00:47
 */
@Data
@ApiModel
public class BatchIdCommand extends AbstractBaseCommand {

    @NotEmpty(message = "ids 不能为空")
	@ApiModelProperty(value = "ids",required = true)
	private List<Long> ids;

}
