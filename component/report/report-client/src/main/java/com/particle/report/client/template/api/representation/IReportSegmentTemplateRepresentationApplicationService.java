package com.particle.report.client.template.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplatePageQueryCommand;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplateQueryListCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;

/**
 * <p>
 * 报告片段模板 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IReportSegmentTemplateRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<ReportSegmentTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<ReportSegmentTemplateVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param reportSegmentTemplateQueryListCommand
	 * @return
	 */
	MultiResponse<ReportSegmentTemplateVO> queryList(ReportSegmentTemplateQueryListCommand reportSegmentTemplateQueryListCommand);

	/**
	 * 分页查询
	 * @param reportSegmentTemplatePageQueryCommand
	 * @return
	 */
	PageResponse<ReportSegmentTemplateVO> pageQuery(ReportSegmentTemplatePageQueryCommand reportSegmentTemplatePageQueryCommand);

}
