package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.domain.company.DataCompanyAnnualReport;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Component
@Validated
public class DataCompanyAnnualReportCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportGateway dataCompanyAnnualReportGateway;

	/**
	 * 执行企业年报添加指令
	 * @param dataCompanyAnnualReportCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportVO> execute(@Valid DataCompanyAnnualReportCreateCommand dataCompanyAnnualReportCreateCommand) {
		DataCompanyAnnualReport dataCompanyAnnualReport = createByDataCompanyAnnualReportCreateCommand(dataCompanyAnnualReportCreateCommand);
		dataCompanyAnnualReport.setAddControl(dataCompanyAnnualReportCreateCommand);
		dataCompanyAnnualReport.initForAdd();
		boolean save = dataCompanyAnnualReportGateway.save(dataCompanyAnnualReport);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportAppStructMapping.instance.toDataCompanyAnnualReportVO(dataCompanyAnnualReport));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报创建指令创建企业年报模型
	 * @param dataCompanyAnnualReportCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReport createByDataCompanyAnnualReportCreateCommand(DataCompanyAnnualReportCreateCommand dataCompanyAnnualReportCreateCommand){
		DataCompanyAnnualReport dataCompanyAnnualReport = DataCompanyAnnualReport.create();
		DataCompanyAnnualReportCreateCommandToDataCompanyAnnualReportMapping.instance.fillDataCompanyAnnualReportByDataCompanyAnnualReportCreateCommand(dataCompanyAnnualReport, dataCompanyAnnualReportCreateCommand);
		return dataCompanyAnnualReport;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportCreateCommandToDataCompanyAnnualReportMapping{
		DataCompanyAnnualReportCreateCommandToDataCompanyAnnualReportMapping instance = Mappers.getMapper( DataCompanyAnnualReportCreateCommandToDataCompanyAnnualReportMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReport
		 * @param dataCompanyAnnualReportCreateCommand
		 */
		void fillDataCompanyAnnualReportByDataCompanyAnnualReportCreateCommand(@MappingTarget DataCompanyAnnualReport dataCompanyAnnualReport, DataCompanyAnnualReportCreateCommand dataCompanyAnnualReportCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportGateway(DataCompanyAnnualReportGateway dataCompanyAnnualReportGateway) {
		this.dataCompanyAnnualReportGateway = dataCompanyAnnualReportGateway;
	}
}
