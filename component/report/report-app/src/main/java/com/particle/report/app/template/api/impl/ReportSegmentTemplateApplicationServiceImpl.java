package com.particle.report.app.template.api.impl;

import com.particle.report.app.template.executor.ReportSegmentTemplateCopyCommandExecutor;
import com.particle.report.app.template.executor.ReportSegmentTemplateCreateCommandExecutor;
import com.particle.report.app.template.executor.ReportSegmentTemplateDeleteCommandExecutor;
import com.particle.report.app.template.executor.ReportSegmentTemplateUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateCopyCommand;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateUpdateCommand;
import com.particle.report.client.template.api.IReportSegmentTemplateApplicationService;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateCreateCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 报告片段模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Transactional
@Service
@CatchAndLog
public class ReportSegmentTemplateApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IReportSegmentTemplateApplicationService {

	private ReportSegmentTemplateCreateCommandExecutor reportSegmentTemplateCreateCommandExecutor;

	private ReportSegmentTemplateDeleteCommandExecutor reportSegmentTemplateDeleteCommandExecutor;

	private ReportSegmentTemplateUpdateCommandExecutor reportSegmentTemplateUpdateCommandExecutor;

	private ReportSegmentTemplateCopyCommandExecutor reportSegmentTemplateCopyCommandExecutor;


	@Override
	public SingleResponse<ReportSegmentTemplateVO> create(ReportSegmentTemplateCreateCommand reportSegmentTemplateCreateCommand) {
		return reportSegmentTemplateCreateCommandExecutor.execute(reportSegmentTemplateCreateCommand);
	}

	@Override
	public SingleResponse<ReportSegmentTemplateVO> copy(ReportSegmentTemplateCopyCommand reportSegmentTemplateCopyCommand) {
		return reportSegmentTemplateCopyCommandExecutor.copy(reportSegmentTemplateCopyCommand);
	}

	@Override
	public SingleResponse<ReportSegmentTemplateVO> delete(IdCommand deleteCommand) {
		return reportSegmentTemplateDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<ReportSegmentTemplateVO> update(ReportSegmentTemplateUpdateCommand reportSegmentTemplateUpdateCommand) {
		return reportSegmentTemplateUpdateCommandExecutor.execute(reportSegmentTemplateUpdateCommand);
	}

	@Autowired
	public void setReportSegmentTemplateCreateCommandExecutor(ReportSegmentTemplateCreateCommandExecutor reportSegmentTemplateCreateCommandExecutor) {
		this.reportSegmentTemplateCreateCommandExecutor = reportSegmentTemplateCreateCommandExecutor;
	}

	@Autowired
	public void setReportSegmentTemplateDeleteCommandExecutor(ReportSegmentTemplateDeleteCommandExecutor reportSegmentTemplateDeleteCommandExecutor) {
		this.reportSegmentTemplateDeleteCommandExecutor = reportSegmentTemplateDeleteCommandExecutor;
	}
	@Autowired
	public void setReportSegmentTemplateUpdateCommandExecutor(ReportSegmentTemplateUpdateCommandExecutor reportSegmentTemplateUpdateCommandExecutor) {
		this.reportSegmentTemplateUpdateCommandExecutor = reportSegmentTemplateUpdateCommandExecutor;
	}
	@Autowired
	public void setReportSegmentTemplateCopyCommandExecutor(ReportSegmentTemplateCopyCommandExecutor reportSegmentTemplateCopyCommandExecutor) {
		this.reportSegmentTemplateCopyCommandExecutor = reportSegmentTemplateCopyCommandExecutor;
	}
}
