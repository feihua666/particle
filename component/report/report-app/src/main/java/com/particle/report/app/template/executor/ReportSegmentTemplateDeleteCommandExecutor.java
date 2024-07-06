package com.particle.report.app.template.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.report.app.template.structmapping.ReportSegmentTemplateAppStructMapping;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import com.particle.report.domain.template.ReportSegmentTemplate;
import com.particle.report.domain.template.ReportSegmentTemplateId;
import com.particle.report.domain.template.gateway.ReportSegmentTemplateGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 报告片段模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Component
@Validated
public class ReportSegmentTemplateDeleteCommandExecutor  extends AbstractBaseExecutor {

	private ReportSegmentTemplateGateway reportSegmentTemplateGateway;

	/**
	 * 执行 报告片段模板 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<ReportSegmentTemplateVO> execute(@Valid IdCommand deleteCommand) {
		ReportSegmentTemplateId reportSegmentTemplateId = ReportSegmentTemplateId.of(deleteCommand.getId());
		ReportSegmentTemplate byId = reportSegmentTemplateGateway.getById(reportSegmentTemplateId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = reportSegmentTemplateGateway.delete(reportSegmentTemplateId,deleteCommand);
		if (delete) {
			return SingleResponse.of(ReportSegmentTemplateAppStructMapping.instance.toReportSegmentTemplateVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param reportSegmentTemplateGateway
	 */
	@Autowired
	public void setReportSegmentTemplateGateway(ReportSegmentTemplateGateway reportSegmentTemplateGateway) {
		this.reportSegmentTemplateGateway = reportSegmentTemplateGateway;
	}
}
