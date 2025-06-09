package com.particle.report.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.app.executor.ReportApiCommandExecutor;
import com.particle.report.app.interceptor.IReportApiGenerateInterceptor;
import com.particle.report.client.api.IReportApiApplicationService;
import com.particle.report.client.dto.command.ReportApiGenerateCommand;
import com.particle.report.client.dto.data.ReportApiGenerateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	private List<IReportApiGenerateInterceptor> iReportApiGenerateInterceptors;

	@Override
	public SingleResponse<ReportApiGenerateVO> reportApiGenerate(ReportApiGenerateCommand reportApiGenerateCommand) {

		if (iReportApiGenerateInterceptors != null) {
			for (IReportApiGenerateInterceptor iReportApiGenerateInterceptor : iReportApiGenerateInterceptors) {
				iReportApiGenerateInterceptor.pre(reportApiGenerateCommand);
			}
		}
		SingleResponse<ReportApiGenerateVO> r = reportApiCommandExecutor.reportApiGenerate(reportApiGenerateCommand);

		if (iReportApiGenerateInterceptors != null) {
			for (IReportApiGenerateInterceptor iReportApiGenerateInterceptor : iReportApiGenerateInterceptors) {
				iReportApiGenerateInterceptor.post(r,reportApiGenerateCommand);
			}
		}
		return r;
	}

	@Autowired
	public void setReportApiCommandExecutor(ReportApiCommandExecutor reportApiCommandExecutor) {
		this.reportApiCommandExecutor = reportApiCommandExecutor;
	}
	@Autowired(required = false)
	public void setiReportApiGenerateInterceptors(List<IReportApiGenerateInterceptor> iReportApiGenerateInterceptors) {
		this.iReportApiGenerateInterceptors = iReportApiGenerateInterceptors;
	}
}
