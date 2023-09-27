package com.particle.report.client.reportapi.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiPageQueryCommand;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiQueryListCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;

/**
 * <p>
 * 报告接口 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IReportReportApiRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<ReportReportApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<ReportReportApiVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param reportReportApiQueryListCommand
	 * @return
	 */
	MultiResponse<ReportReportApiVO> queryList(ReportReportApiQueryListCommand reportReportApiQueryListCommand);

	/**
	 * 分页查询
	 * @param reportReportApiPageQueryCommand
	 * @return
	 */
	PageResponse<ReportReportApiVO> pageQuery(ReportReportApiPageQueryCommand reportReportApiPageQueryCommand);

}
