package com.particle.report.app.template.executor;

import com.particle.report.app.template.structmapping.ReportSegmentTemplateAppStructMapping;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateCreateCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import com.particle.report.domain.template.ReportSegmentTemplate;
import com.particle.report.domain.template.gateway.ReportSegmentTemplateGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
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
public class ReportSegmentTemplateCreateCommandExecutor  extends AbstractBaseExecutor {

	private ReportSegmentTemplateGateway reportSegmentTemplateGateway;

	/**
	 * 执行报告片段模板添加指令
	 * @param reportSegmentTemplateCreateCommand
	 * @return
	 */
	public SingleResponse<ReportSegmentTemplateVO> execute(@Valid ReportSegmentTemplateCreateCommand reportSegmentTemplateCreateCommand) {
		ReportSegmentTemplate reportSegmentTemplate = createByReportSegmentTemplateCreateCommand(reportSegmentTemplateCreateCommand);
		reportSegmentTemplate.setAddControl(reportSegmentTemplateCreateCommand);
		boolean save = reportSegmentTemplateGateway.save(reportSegmentTemplate);
		if (save) {
			return SingleResponse.of(ReportSegmentTemplateAppStructMapping.instance.toReportSegmentTemplateVO(reportSegmentTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据报告片段模板创建指令创建报告片段模板模型
	 * @param reportSegmentTemplateCreateCommand
	 * @return
	 */
	private ReportSegmentTemplate createByReportSegmentTemplateCreateCommand(ReportSegmentTemplateCreateCommand reportSegmentTemplateCreateCommand){
		ReportSegmentTemplate reportSegmentTemplate = ReportSegmentTemplate.create();
		ReportSegmentTemplateCreateCommandToReportSegmentTemplateMapping.instance.fillReportSegmentTemplateByReportSegmentTemplateCreateCommand(reportSegmentTemplate, reportSegmentTemplateCreateCommand);
		return reportSegmentTemplate;
	}

	@Mapper
	interface  ReportSegmentTemplateCreateCommandToReportSegmentTemplateMapping{
		ReportSegmentTemplateCreateCommandToReportSegmentTemplateMapping instance = Mappers.getMapper( ReportSegmentTemplateCreateCommandToReportSegmentTemplateMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param reportSegmentTemplate
		 * @param reportSegmentTemplateCreateCommand
		 */
		void fillReportSegmentTemplateByReportSegmentTemplateCreateCommand(@MappingTarget ReportSegmentTemplate reportSegmentTemplate, ReportSegmentTemplateCreateCommand reportSegmentTemplateCreateCommand);
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
