package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 针对申请额外数据，提取一个对象
 * </p>
 *
 * @author yangwei
 * @since 2023-05-11 16:33
 */
@Data
@Schema
public class TenantCreateApplyExtJsonFuncApplicationCommand extends AbstractBaseCommand {

	@NotNull(message = "功能应用id 不能为空")
	@Schema(description = "功能应用id",requiredMode = Schema.RequiredMode.REQUIRED)
	private Long funcApplicationId;

	@NotEmpty(message = "功能id 不能为空")
	@Schema(description = "功能id",requiredMode = Schema.RequiredMode.REQUIRED)
	private List<Long> funcIds;

}
