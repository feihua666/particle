package com.particle.report.app.template.executor;

import com.particle.report.app.template.structmapping.ReportSegmentTemplateAppStructMapping;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateUpdateCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import com.particle.report.domain.template.ReportSegmentTemplate;
import com.particle.report.domain.template.ReportSegmentTemplateId;
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
 * 报告片段模板 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class ReportSegmentTemplateUpdateCommandExecutor  extends AbstractBaseExecutor {

	private ReportSegmentTemplateGateway reportSegmentTemplateGateway;

	/**
	 * 执行 报告片段模板 更新指令
	 * @param reportSegmentTemplateUpdateCommand
	 * @return
	 */
	public SingleResponse<ReportSegmentTemplateVO> execute(@Valid ReportSegmentTemplateUpdateCommand reportSegmentTemplateUpdateCommand) {
		ReportSegmentTemplate reportSegmentTemplate = createByReportSegmentTemplateUpdateCommand(reportSegmentTemplateUpdateCommand);
		reportSegmentTemplate.setUpdateControl(reportSegmentTemplateUpdateCommand);
		boolean save = reportSegmentTemplateGateway.save(reportSegmentTemplate);
		if (save) {
			return SingleResponse.of(ReportSegmentTemplateAppStructMapping.instance.toReportSegmentTemplateVO(reportSegmentTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据报告片段模板更新指令创建报告片段模板模型
	 * @param reportSegmentTemplateUpdateCommand
	 * @return
	 */
	private ReportSegmentTemplate createByReportSegmentTemplateUpdateCommand(ReportSegmentTemplateUpdateCommand reportSegmentTemplateUpdateCommand){
		ReportSegmentTemplate reportSegmentTemplate = ReportSegmentTemplate.create();
		ReportSegmentTemplateUpdateCommandToReportSegmentTemplateMapping.instance.fillReportSegmentTemplateByReportSegmentTemplateUpdateCommand(reportSegmentTemplate, reportSegmentTemplateUpdateCommand);
		return reportSegmentTemplate;
	}

	@Mapper
	interface ReportSegmentTemplateUpdateCommandToReportSegmentTemplateMapping{
		ReportSegmentTemplateUpdateCommandToReportSegmentTemplateMapping instance = Mappers.getMapper(ReportSegmentTemplateUpdateCommandToReportSegmentTemplateMapping.class );

		default ReportSegmentTemplateId map(Long id){
			if (id == null) {
				return null;
			}
			return ReportSegmentTemplateId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param reportSegmentTemplate
		 * @param reportSegmentTemplateUpdateCommand
		 */
		void fillReportSegmentTemplateByReportSegmentTemplateUpdateCommand(@MappingTarget ReportSegmentTemplate reportSegmentTemplate, ReportSegmentTemplateUpdateCommand reportSegmentTemplateUpdateCommand);
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
