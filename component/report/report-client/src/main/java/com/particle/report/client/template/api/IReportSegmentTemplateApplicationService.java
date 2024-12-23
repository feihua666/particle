package com.particle.report.client.template.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateCopyCommand;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateCreateCommand;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateUpdateCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;

/**
 * <p>
 * 报告片段模板 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
public interface IReportSegmentTemplateApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param reportSegmentTemplateCreateCommand
	 * @return
	 */
	SingleResponse<ReportSegmentTemplateVO> create(ReportSegmentTemplateCreateCommand reportSegmentTemplateCreateCommand);

	/**
	 * 复制
	 * @param reportSegmentTemplateCopyCommand
	 * @return
	 */
	public SingleResponse<ReportSegmentTemplateVO> copy(ReportSegmentTemplateCopyCommand reportSegmentTemplateCopyCommand);
	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<ReportSegmentTemplateVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param reportSegmentTemplateUpdateCommand
	 * @return
	 */
	SingleResponse<ReportSegmentTemplateVO> update(ReportSegmentTemplateUpdateCommand reportSegmentTemplateUpdateCommand);

	/**
	 * 刷新报告片段模板缓存
	 * @param idCommand
	 * @return
	 */
	public SingleResponse<String> refreshCache(IdCommand idCommand);

}
