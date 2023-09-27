package com.particle.report.app.reportapi.executor;

import com.particle.report.app.reportapi.structmapping.ReportReportApiAppStructMapping;
import com.particle.report.client.reportapi.dto.command.ReportReportApiUpdateCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;
import com.particle.report.domain.reportapi.ReportReportApi;
import com.particle.report.domain.reportapi.ReportReportApiId;
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
 * 报告接口 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class ReportReportApiUpdateCommandExecutor  extends AbstractBaseExecutor {

	private ReportReportApiGateway reportReportApiGateway;

	/**
	 * 执行 报告接口 更新指令
	 * @param reportReportApiUpdateCommand
	 * @return
	 */
	public SingleResponse<ReportReportApiVO> execute(@Valid ReportReportApiUpdateCommand reportReportApiUpdateCommand) {
		ReportReportApi reportReportApi = createByReportReportApiUpdateCommand(reportReportApiUpdateCommand);
		reportReportApi.setUpdateControl(reportReportApiUpdateCommand);
		boolean save = reportReportApiGateway.save(reportReportApi);
		if (save) {
			return SingleResponse.of(ReportReportApiAppStructMapping.instance.toReportReportApiVO(reportReportApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据报告接口更新指令创建报告接口模型
	 * @param reportReportApiUpdateCommand
	 * @return
	 */
	private ReportReportApi createByReportReportApiUpdateCommand(ReportReportApiUpdateCommand reportReportApiUpdateCommand){
		ReportReportApi reportReportApi = ReportReportApi.create();
		ReportReportApiUpdateCommandToReportReportApiMapping.instance.fillReportReportApiByReportReportApiUpdateCommand(reportReportApi, reportReportApiUpdateCommand);
		return reportReportApi;
	}

	@Mapper
	interface ReportReportApiUpdateCommandToReportReportApiMapping{
		ReportReportApiUpdateCommandToReportReportApiMapping instance = Mappers.getMapper(ReportReportApiUpdateCommandToReportReportApiMapping.class );

		default ReportReportApiId map(Long id){
			if (id == null) {
				return null;
			}
			return ReportReportApiId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param reportReportApi
		 * @param reportReportApiUpdateCommand
		 */
		void fillReportReportApiByReportReportApiUpdateCommand(@MappingTarget ReportReportApi reportReportApi, ReportReportApiUpdateCommand reportReportApiUpdateCommand);
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
