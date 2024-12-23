package com.particle.report.app.reportapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.report.app.reportapi.structmapping.ReportReportApiAppStructMapping;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;
import com.particle.report.domain.reportapi.ReportReportApi;
import com.particle.report.domain.reportapi.ReportReportApiId;
import com.particle.report.domain.reportapi.gateway.ReportReportApiGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 报告接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Component
@Validated
public class ReportReportApiDeleteCommandExecutor  extends AbstractBaseExecutor {

	private ReportReportApiGateway reportReportApiGateway;

	/**
	 * 执行 报告接口 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<ReportReportApiVO> execute(@Valid IdCommand deleteCommand) {
		ReportReportApiId reportReportApiId = ReportReportApiId.of(deleteCommand.getId());
		ReportReportApi byId = reportReportApiGateway.getById(reportReportApiId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = reportReportApiGateway.delete(reportReportApiId,deleteCommand);
		if (delete) {
			return SingleResponse.of(ReportReportApiAppStructMapping.instance.toReportReportApiVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param reportReportApiGateway
	 */
	@Autowired
	public void setReportReportApiGateway(ReportReportApiGateway reportReportApiGateway) {
		this.reportReportApiGateway = reportReportApiGateway;
	}
}
