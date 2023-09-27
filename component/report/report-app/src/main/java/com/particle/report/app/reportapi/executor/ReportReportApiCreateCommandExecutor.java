package com.particle.report.app.reportapi.executor;

import com.particle.report.app.reportapi.structmapping.ReportReportApiAppStructMapping;
import com.particle.report.client.reportapi.dto.command.ReportReportApiCreateCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;
import com.particle.report.domain.reportapi.ReportReportApi;
import com.particle.report.domain.reportapi.gateway.ReportReportApiGateway;
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
 * 报告接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Component
@Validated
public class ReportReportApiCreateCommandExecutor  extends AbstractBaseExecutor {

	private ReportReportApiGateway reportReportApiGateway;

	/**
	 * 执行报告接口添加指令
	 * @param reportReportApiCreateCommand
	 * @return
	 */
	public SingleResponse<ReportReportApiVO> execute(@Valid ReportReportApiCreateCommand reportReportApiCreateCommand) {
		ReportReportApi reportReportApi = createByReportReportApiCreateCommand(reportReportApiCreateCommand);
		reportReportApi.setAddControl(reportReportApiCreateCommand);
		boolean save = reportReportApiGateway.save(reportReportApi);
		if (save) {
			return SingleResponse.of(ReportReportApiAppStructMapping.instance.toReportReportApiVO(reportReportApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据报告接口创建指令创建报告接口模型
	 * @param reportReportApiCreateCommand
	 * @return
	 */
	private ReportReportApi createByReportReportApiCreateCommand(ReportReportApiCreateCommand reportReportApiCreateCommand){
		ReportReportApi reportReportApi = ReportReportApi.create();
		ReportReportApiCreateCommandToReportReportApiMapping.instance.fillReportReportApiByReportReportApiCreateCommand(reportReportApi, reportReportApiCreateCommand);
		return reportReportApi;
	}

	@Mapper
	interface  ReportReportApiCreateCommandToReportReportApiMapping{
		ReportReportApiCreateCommandToReportReportApiMapping instance = Mappers.getMapper( ReportReportApiCreateCommandToReportReportApiMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param reportReportApi
		 * @param reportReportApiCreateCommand
		 */
		void fillReportReportApiByReportReportApiCreateCommand(@MappingTarget ReportReportApi reportReportApi, ReportReportApiCreateCommand reportReportApiCreateCommand);
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
