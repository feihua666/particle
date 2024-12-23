package com.particle.report.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 报告服务，报告指令
 * </p>
 *
 * @author yangwei
 * @since 2024-07-11 17:49:16
 */
@Data
@Schema
public class ReportApiCommand extends AbstractBaseCommand {

	@NotNull(message = "报告接口地址 不能为空")
	@Schema(description = "报告接口地址",requiredMode = Schema.RequiredMode.REQUIRED)
	private String url;
}
