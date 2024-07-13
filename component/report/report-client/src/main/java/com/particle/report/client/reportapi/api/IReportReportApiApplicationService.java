package com.particle.report.client.reportapi.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.dto.command.ReportApiRefreshCacheCommand;
import com.particle.report.client.reportapi.dto.command.ReportReportApiCreateCommand;
import com.particle.report.client.reportapi.dto.command.ReportReportApiUpdateCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;

/**
 * <p>
 * 报告接口 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
public interface IReportReportApiApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param reportReportApiCreateCommand
	 * @return
	 */
	SingleResponse<ReportReportApiVO> create(ReportReportApiCreateCommand reportReportApiCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<ReportReportApiVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param reportReportApiUpdateCommand
	 * @return
	 */
	SingleResponse<ReportReportApiVO> update(ReportReportApiUpdateCommand reportReportApiUpdateCommand);

	/**
	 * 刷新缓存
	 * @param reportApiRefreshCacheCommand
	 * @return
	 */
	public SingleResponse<String> refreshCache(ReportApiRefreshCacheCommand reportApiRefreshCacheCommand);
}
