package com.particle.report.client.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 报告服务，报告刷新缓存指令
 * </p>
 *
 * @author yangwei
 * @since 2024-07-11 17:55:46
 */
@Data
@Schema
public class ReportApiRefreshCacheCommand extends ReportApiCommand {
	/**
	 * 参数对象
	 */
	@Schema(description = "是否包含对应的模板")
	private Boolean isIncludeSegmentTemplate = true;

}
