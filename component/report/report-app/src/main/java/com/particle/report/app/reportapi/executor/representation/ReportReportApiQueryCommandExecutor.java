package com.particle.report.app.reportapi.executor.representation;

import com.particle.report.app.reportapi.structmapping.ReportReportApiAppStructMapping;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiQueryListCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;
import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import com.particle.report.infrastructure.reportapi.service.IReportReportApiService;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 报告接口 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Component
@Validated
public class ReportReportApiQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IReportReportApiService iReportReportApiService;

	/**
	 * 执行 报告接口 列表查询指令
	 * @param reportReportApiQueryListCommand
	 * @return
	 */
	public MultiResponse<ReportReportApiVO> execute(@Valid ReportReportApiQueryListCommand reportReportApiQueryListCommand) {
		List<ReportReportApiDO> reportReportApiDO = iReportReportApiService.list(reportReportApiQueryListCommand);
		List<ReportReportApiVO> reportReportApiVOs = ReportReportApiAppStructMapping.instance.reportReportApiDOsToReportReportApiVOs(reportReportApiDO);
		return MultiResponse.of(reportReportApiVOs);
	}
	/**
	 * 执行 报告接口 分页查询指令
	 * @param reportReportApiPageQueryCommand
	 * @return
	 */
	public PageResponse<ReportReportApiVO> execute(@Valid ReportReportApiPageQueryCommand reportReportApiPageQueryCommand) {
		Page<ReportReportApiDO> page = iReportReportApiService.listPage(reportReportApiPageQueryCommand);
		return ReportReportApiAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 报告接口 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<ReportReportApiVO> executeDetail(IdCommand detailCommand) {
		ReportReportApiDO byId = iReportReportApiService.getById(detailCommand.getId());
		ReportReportApiVO reportReportApiVO = ReportReportApiAppStructMapping.instance.reportReportApiDOToReportReportApiVO(byId);
		return SingleResponse.of(reportReportApiVO);
	}
	/**
	 * 执行 报告接口 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<ReportReportApiVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		ReportReportApiDO byId = iReportReportApiService.getById(detailForUpdateCommand.getId());
		ReportReportApiVO reportReportApiVO = ReportReportApiAppStructMapping.instance.reportReportApiDOToReportReportApiVO(byId);
		return SingleResponse.of(reportReportApiVO);
	}

	@Autowired
	public void setIReportReportApiService(IReportReportApiService iReportReportApiService) {
		this.iReportReportApiService = iReportReportApiService;
	}
}
