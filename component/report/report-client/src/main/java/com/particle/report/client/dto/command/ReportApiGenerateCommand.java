package com.particle.report.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 报告服务，报告生成指令
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 11:24
 */
@Data
@Schema
public class ReportApiGenerateCommand  extends AbstractBaseCommand {

	@NotNull(message = "报告接口地址 不能为空")
	@Schema(description = "报告接口地址",requiredMode = Schema.RequiredMode.REQUIRED)
	private String url;

	/**
	 * 参数对象
	 */
	@Schema(description = "参数对象")
	private Object param;

	/**
	 * 查询参数一般在 url后面以 & 分隔
	 */
	@Schema(description = "查询参数")
	private String queryString;
}
