package com.particle.report.app.template.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.app.template.structmapping.ReportSegmentTemplateAppStructMapping;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplatePageQueryCommand;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplateQueryListCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 报告片段模板 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Component
@Validated
public class ReportSegmentTemplateQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IReportSegmentTemplateService iReportSegmentTemplateService;

	/**
	 * 执行 报告片段模板 列表查询指令
	 * @param reportSegmentTemplateQueryListCommand
	 * @return
	 */
	public MultiResponse<ReportSegmentTemplateVO> execute(@Valid ReportSegmentTemplateQueryListCommand reportSegmentTemplateQueryListCommand) {
		List<ReportSegmentTemplateDO> reportSegmentTemplateDO = iReportSegmentTemplateService.list(reportSegmentTemplateQueryListCommand);
		List<ReportSegmentTemplateVO> reportSegmentTemplateVOs = ReportSegmentTemplateAppStructMapping.instance.reportSegmentTemplateDOsToReportSegmentTemplateVOs(reportSegmentTemplateDO);
		return MultiResponse.of(reportSegmentTemplateVOs);
	}
	/**
	 * 执行 报告片段模板 分页查询指令
	 * @param reportSegmentTemplatePageQueryCommand
	 * @return
	 */
	public PageResponse<ReportSegmentTemplateVO> execute(@Valid ReportSegmentTemplatePageQueryCommand reportSegmentTemplatePageQueryCommand) {
		Page<ReportSegmentTemplateDO> page = iReportSegmentTemplateService.listPage(reportSegmentTemplatePageQueryCommand);
		return ReportSegmentTemplateAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 报告片段模板 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<ReportSegmentTemplateVO> executeDetail(IdCommand detailCommand) {
		ReportSegmentTemplateDO byId = iReportSegmentTemplateService.getById(detailCommand.getId());
		ReportSegmentTemplateVO reportSegmentTemplateVO = ReportSegmentTemplateAppStructMapping.instance.reportSegmentTemplateDOToReportSegmentTemplateVO(byId);
		return SingleResponse.of(reportSegmentTemplateVO);
	}
	/**
	 * 执行 报告片段模板 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<ReportSegmentTemplateVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		ReportSegmentTemplateDO byId = iReportSegmentTemplateService.getById(detailForUpdateCommand.getId());
		ReportSegmentTemplateVO reportSegmentTemplateVO = ReportSegmentTemplateAppStructMapping.instance.reportSegmentTemplateDOToReportSegmentTemplateVO(byId);
		return SingleResponse.of(reportSegmentTemplateVO);
	}

	@Autowired
	public void setIReportSegmentTemplateService(IReportSegmentTemplateService iReportSegmentTemplateService) {
		this.iReportSegmentTemplateService = iReportSegmentTemplateService;
	}
}
