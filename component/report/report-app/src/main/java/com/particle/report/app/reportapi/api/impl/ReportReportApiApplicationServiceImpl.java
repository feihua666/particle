package com.particle.report.app.reportapi.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.app.executor.ReportApiCommandExecutor;
import com.particle.report.app.reportapi.executor.ReportReportApiCreateCommandExecutor;
import com.particle.report.app.reportapi.executor.ReportReportApiDeleteCommandExecutor;
import com.particle.report.app.reportapi.executor.ReportReportApiUpdateCommandExecutor;
import com.particle.report.client.dto.command.ReportApiRefreshCacheCommand;
import com.particle.report.client.reportapi.api.IReportReportApiApplicationService;
import com.particle.report.client.reportapi.dto.command.ReportReportApiCreateCommand;
import com.particle.report.client.reportapi.dto.command.ReportReportApiUpdateCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 报告接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Transactional
@Service
@CatchAndLog
public class ReportReportApiApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IReportReportApiApplicationService {

	private ReportReportApiCreateCommandExecutor reportReportApiCreateCommandExecutor;

	private ReportReportApiDeleteCommandExecutor reportReportApiDeleteCommandExecutor;

	private ReportReportApiUpdateCommandExecutor reportReportApiUpdateCommandExecutor;

	private ReportApiCommandExecutor reportApiCommandExecutor;

	@Override
	public SingleResponse<ReportReportApiVO> create(ReportReportApiCreateCommand reportReportApiCreateCommand) {
		return reportReportApiCreateCommandExecutor.execute(reportReportApiCreateCommand);
	}

	@Override
	public SingleResponse<ReportReportApiVO> delete(IdCommand deleteCommand) {
		return reportReportApiDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<ReportReportApiVO> update(ReportReportApiUpdateCommand reportReportApiUpdateCommand) {
		return reportReportApiUpdateCommandExecutor.execute(reportReportApiUpdateCommand);
	}

	@Override
	public SingleResponse<String> refreshCache(ReportApiRefreshCacheCommand reportApiRefreshCacheCommand) {
		return reportApiCommandExecutor.refreshCache(reportApiRefreshCacheCommand);
	}

	@Autowired
	public void setReportReportApiCreateCommandExecutor(ReportReportApiCreateCommandExecutor reportReportApiCreateCommandExecutor) {
		this.reportReportApiCreateCommandExecutor = reportReportApiCreateCommandExecutor;
	}

	@Autowired
	public void setReportReportApiDeleteCommandExecutor(ReportReportApiDeleteCommandExecutor reportReportApiDeleteCommandExecutor) {
		this.reportReportApiDeleteCommandExecutor = reportReportApiDeleteCommandExecutor;
	}
	@Autowired
	public void setReportReportApiUpdateCommandExecutor(ReportReportApiUpdateCommandExecutor reportReportApiUpdateCommandExecutor) {
		this.reportReportApiUpdateCommandExecutor = reportReportApiUpdateCommandExecutor;
	}

	@Autowired
	public void setReportApiCommandExecutor(ReportApiCommandExecutor reportApiCommandExecutor) {
		this.reportApiCommandExecutor = reportApiCommandExecutor;
	}
}
