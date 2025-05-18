package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.data.domain.company.DataCompanyIprPatentLicense;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentLicenseGateway;
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
 * 企业知识产权专利许可信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Component
@Validated
public class DataCompanyIprPatentLicenseCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentLicenseGateway dataCompanyIprPatentLicenseGateway;

	/**
	 * 执行企业知识产权专利许可信息添加指令
	 * @param dataCompanyIprPatentLicenseCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLicenseVO> execute(@Valid DataCompanyIprPatentLicenseCreateCommand dataCompanyIprPatentLicenseCreateCommand) {
		DataCompanyIprPatentLicense dataCompanyIprPatentLicense = createByDataCompanyIprPatentLicenseCreateCommand(dataCompanyIprPatentLicenseCreateCommand);
		dataCompanyIprPatentLicense.initForAdd();
		dataCompanyIprPatentLicense.setAddControl(dataCompanyIprPatentLicenseCreateCommand);
		boolean save = dataCompanyIprPatentLicenseGateway.save(dataCompanyIprPatentLicense);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentLicenseAppStructMapping.instance.toDataCompanyIprPatentLicenseVO(dataCompanyIprPatentLicense));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利许可信息创建指令创建企业知识产权专利许可信息模型
	 * @param dataCompanyIprPatentLicenseCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentLicense createByDataCompanyIprPatentLicenseCreateCommand(DataCompanyIprPatentLicenseCreateCommand dataCompanyIprPatentLicenseCreateCommand){
		DataCompanyIprPatentLicense dataCompanyIprPatentLicense = DataCompanyIprPatentLicense.create();
		DataCompanyIprPatentLicenseCreateCommandToDataCompanyIprPatentLicenseMapping.instance.fillDataCompanyIprPatentLicenseByDataCompanyIprPatentLicenseCreateCommand(dataCompanyIprPatentLicense, dataCompanyIprPatentLicenseCreateCommand);
		return dataCompanyIprPatentLicense;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentLicenseCreateCommandToDataCompanyIprPatentLicenseMapping{
		DataCompanyIprPatentLicenseCreateCommandToDataCompanyIprPatentLicenseMapping instance = Mappers.getMapper( DataCompanyIprPatentLicenseCreateCommandToDataCompanyIprPatentLicenseMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentLicense
		 * @param dataCompanyIprPatentLicenseCreateCommand
		 */
		void fillDataCompanyIprPatentLicenseByDataCompanyIprPatentLicenseCreateCommand(@MappingTarget DataCompanyIprPatentLicense dataCompanyIprPatentLicense, DataCompanyIprPatentLicenseCreateCommand dataCompanyIprPatentLicenseCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentLicenseGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentLicenseGateway(DataCompanyIprPatentLicenseGateway dataCompanyIprPatentLicenseGateway) {
		this.dataCompanyIprPatentLicenseGateway = dataCompanyIprPatentLicenseGateway;
	}
}
