package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicense;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicenseGateway;
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
 * 企业知识产权商标许可信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicenseCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicenseGateway dataCompanyIprTrademarkLicenseGateway;

	/**
	 * 执行企业知识产权商标许可信息添加指令
	 * @param dataCompanyIprTrademarkLicenseCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicenseVO> execute(@Valid DataCompanyIprTrademarkLicenseCreateCommand dataCompanyIprTrademarkLicenseCreateCommand) {
		DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense = createByDataCompanyIprTrademarkLicenseCreateCommand(dataCompanyIprTrademarkLicenseCreateCommand);
		dataCompanyIprTrademarkLicense.initForAdd();
		dataCompanyIprTrademarkLicense.setAddControl(dataCompanyIprTrademarkLicenseCreateCommand);
		boolean save = dataCompanyIprTrademarkLicenseGateway.save(dataCompanyIprTrademarkLicense);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkLicenseAppStructMapping.instance.toDataCompanyIprTrademarkLicenseVO(dataCompanyIprTrademarkLicense));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标许可信息创建指令创建企业知识产权商标许可信息模型
	 * @param dataCompanyIprTrademarkLicenseCreateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkLicense createByDataCompanyIprTrademarkLicenseCreateCommand(DataCompanyIprTrademarkLicenseCreateCommand dataCompanyIprTrademarkLicenseCreateCommand){
		DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense = DataCompanyIprTrademarkLicense.create();
		DataCompanyIprTrademarkLicenseCreateCommandToDataCompanyIprTrademarkLicenseMapping.instance.fillDataCompanyIprTrademarkLicenseByDataCompanyIprTrademarkLicenseCreateCommand(dataCompanyIprTrademarkLicense, dataCompanyIprTrademarkLicenseCreateCommand);
		return dataCompanyIprTrademarkLicense;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprTrademarkLicenseCreateCommandToDataCompanyIprTrademarkLicenseMapping{
		DataCompanyIprTrademarkLicenseCreateCommandToDataCompanyIprTrademarkLicenseMapping instance = Mappers.getMapper( DataCompanyIprTrademarkLicenseCreateCommandToDataCompanyIprTrademarkLicenseMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkLicense
		 * @param dataCompanyIprTrademarkLicenseCreateCommand
		 */
		void fillDataCompanyIprTrademarkLicenseByDataCompanyIprTrademarkLicenseCreateCommand(@MappingTarget DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense, DataCompanyIprTrademarkLicenseCreateCommand dataCompanyIprTrademarkLicenseCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkLicenseGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkLicenseGateway(DataCompanyIprTrademarkLicenseGateway dataCompanyIprTrademarkLicenseGateway) {
		this.dataCompanyIprTrademarkLicenseGateway = dataCompanyIprTrademarkLicenseGateway;
	}
}
