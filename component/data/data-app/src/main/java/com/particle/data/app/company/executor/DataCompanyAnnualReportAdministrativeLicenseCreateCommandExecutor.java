package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicense;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportAdministrativeLicenseGateway;
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
 * 企业年报行政许可 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Component
@Validated
public class DataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportAdministrativeLicenseGateway dataCompanyAnnualReportAdministrativeLicenseGateway;

	/**
	 * 执行企业年报行政许可添加指令
	 * @param dataCompanyAnnualReportAdministrativeLicenseCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> execute(@Valid DataCompanyAnnualReportAdministrativeLicenseCreateCommand dataCompanyAnnualReportAdministrativeLicenseCreateCommand) {
		DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense = createByDataCompanyAnnualReportAdministrativeLicenseCreateCommand(dataCompanyAnnualReportAdministrativeLicenseCreateCommand);
		dataCompanyAnnualReportAdministrativeLicense.setAddControl(dataCompanyAnnualReportAdministrativeLicenseCreateCommand);
		dataCompanyAnnualReportAdministrativeLicense.initForAdd();
		boolean save = dataCompanyAnnualReportAdministrativeLicenseGateway.save(dataCompanyAnnualReportAdministrativeLicense);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.toDataCompanyAnnualReportAdministrativeLicenseVO(dataCompanyAnnualReportAdministrativeLicense));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报行政许可创建指令创建企业年报行政许可模型
	 * @param dataCompanyAnnualReportAdministrativeLicenseCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportAdministrativeLicense createByDataCompanyAnnualReportAdministrativeLicenseCreateCommand(DataCompanyAnnualReportAdministrativeLicenseCreateCommand dataCompanyAnnualReportAdministrativeLicenseCreateCommand){
		DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense = DataCompanyAnnualReportAdministrativeLicense.create();
		DataCompanyAnnualReportAdministrativeLicenseCreateCommandToDataCompanyAnnualReportAdministrativeLicenseMapping.instance.fillDataCompanyAnnualReportAdministrativeLicenseByDataCompanyAnnualReportAdministrativeLicenseCreateCommand(dataCompanyAnnualReportAdministrativeLicense, dataCompanyAnnualReportAdministrativeLicenseCreateCommand);
		return dataCompanyAnnualReportAdministrativeLicense;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportAdministrativeLicenseCreateCommandToDataCompanyAnnualReportAdministrativeLicenseMapping{
		DataCompanyAnnualReportAdministrativeLicenseCreateCommandToDataCompanyAnnualReportAdministrativeLicenseMapping instance = Mappers.getMapper( DataCompanyAnnualReportAdministrativeLicenseCreateCommandToDataCompanyAnnualReportAdministrativeLicenseMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportAdministrativeLicense
		 * @param dataCompanyAnnualReportAdministrativeLicenseCreateCommand
		 */
		void fillDataCompanyAnnualReportAdministrativeLicenseByDataCompanyAnnualReportAdministrativeLicenseCreateCommand(@MappingTarget DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense, DataCompanyAnnualReportAdministrativeLicenseCreateCommand dataCompanyAnnualReportAdministrativeLicenseCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportAdministrativeLicenseGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportAdministrativeLicenseGateway(DataCompanyAnnualReportAdministrativeLicenseGateway dataCompanyAnnualReportAdministrativeLicenseGateway) {
		this.dataCompanyAnnualReportAdministrativeLicenseGateway = dataCompanyAnnualReportAdministrativeLicenseGateway;
	}
}
