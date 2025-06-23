package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.data.domain.company.DataCompanyAdministrativeLicense;
import com.particle.data.domain.company.gateway.DataCompanyAdministrativeLicenseGateway;
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
 * 企业行政许可 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAdministrativeLicenseGateway dataCompanyAdministrativeLicenseGateway;

	/**
	 * 执行企业行政许可添加指令
	 * @param dataCompanyAdministrativeLicenseCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAdministrativeLicenseVO> execute(@Valid DataCompanyAdministrativeLicenseCreateCommand dataCompanyAdministrativeLicenseCreateCommand) {
		DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense = createByDataCompanyAdministrativeLicenseCreateCommand(dataCompanyAdministrativeLicenseCreateCommand);
		dataCompanyAdministrativeLicense.initForAdd();
		dataCompanyAdministrativeLicense.setAddControl(dataCompanyAdministrativeLicenseCreateCommand);
		boolean save = dataCompanyAdministrativeLicenseGateway.save(dataCompanyAdministrativeLicense);
		if (save) {
			return SingleResponse.of(DataCompanyAdministrativeLicenseAppStructMapping.instance.toDataCompanyAdministrativeLicenseVO(dataCompanyAdministrativeLicense));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业行政许可创建指令创建企业行政许可模型
	 * @param dataCompanyAdministrativeLicenseCreateCommand
	 * @return
	 */
	private DataCompanyAdministrativeLicense createByDataCompanyAdministrativeLicenseCreateCommand(DataCompanyAdministrativeLicenseCreateCommand dataCompanyAdministrativeLicenseCreateCommand){
		DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense = DataCompanyAdministrativeLicense.create();
		DataCompanyAdministrativeLicenseCreateCommandToDataCompanyAdministrativeLicenseMapping.instance.fillDataCompanyAdministrativeLicenseByDataCompanyAdministrativeLicenseCreateCommand(dataCompanyAdministrativeLicense, dataCompanyAdministrativeLicenseCreateCommand);
		return dataCompanyAdministrativeLicense;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAdministrativeLicenseCreateCommandToDataCompanyAdministrativeLicenseMapping{
		DataCompanyAdministrativeLicenseCreateCommandToDataCompanyAdministrativeLicenseMapping instance = Mappers.getMapper( DataCompanyAdministrativeLicenseCreateCommandToDataCompanyAdministrativeLicenseMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAdministrativeLicense
		 * @param dataCompanyAdministrativeLicenseCreateCommand
		 */
		void fillDataCompanyAdministrativeLicenseByDataCompanyAdministrativeLicenseCreateCommand(@MappingTarget DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense, DataCompanyAdministrativeLicenseCreateCommand dataCompanyAdministrativeLicenseCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAdministrativeLicenseGateway
	 */
	@Autowired
	public void setDataCompanyAdministrativeLicenseGateway(DataCompanyAdministrativeLicenseGateway dataCompanyAdministrativeLicenseGateway) {
		this.dataCompanyAdministrativeLicenseGateway = dataCompanyAdministrativeLicenseGateway;
	}
}
