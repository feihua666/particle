package com.particle.report.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.app.executor.ReportApiCommandExecutor;
import com.particle.report.client.api.IReportApiApplicationService;
import com.particle.report.client.dto.command.ReportApiGenerateCommand;
import com.particle.report.client.dto.data.ReportApiGenerateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报告接口应用级服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 11:41
 */
@Service
@CatchAndLog
public class ReportApiApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IReportApiApplicationService {

	private ReportApiCommandExecutor reportApiCommandExecutor;

	@Override
	public SingleResponse<ReportApiGenerateVO> reportApiGenerate(ReportApiGenerateCommand reportApiGenerateCommand) {
		return reportApiCommandExecutor.reportApiGenerate(reportApiGenerateCommand);
	}

	@Autowired
	public void setReportApiCommandExecutor(ReportApiCommandExecutor reportApiCommandExecutor) {
		this.reportApiCommandExecutor = reportApiCommandExecutor;
	}
}
