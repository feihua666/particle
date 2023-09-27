package com.particle.report.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.dto.command.ReportApiGenerateCommand;
import com.particle.report.client.dto.data.ReportApiGenerateVO;

/**
 * <p>
 * 报告生成应用级服务
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 11:22
 */
public interface IReportApiApplicationService extends IBaseApplicationService {

	/**
	 * 生成报告
	 * @param reportApiGenerateCommand
	 * @return
	 */
	SingleResponse<ReportApiGenerateVO> reportApiGenerate(ReportApiGenerateCommand reportApiGenerateCommand);
}
