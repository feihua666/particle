package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicensePersonAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicensePersonCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePerson;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicensePersonGateway;
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
 * 企业知识产权商标许可人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicensePersonCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicensePersonGateway dataCompanyIprTrademarkLicensePersonGateway;

	/**
	 * 执行企业知识产权商标许可人添加指令
	 * @param dataCompanyIprTrademarkLicensePersonCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> execute(@Valid DataCompanyIprTrademarkLicensePersonCreateCommand dataCompanyIprTrademarkLicensePersonCreateCommand) {
		DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson = createByDataCompanyIprTrademarkLicensePersonCreateCommand(dataCompanyIprTrademarkLicensePersonCreateCommand);
		dataCompanyIprTrademarkLicensePerson.initForAdd();
		dataCompanyIprTrademarkLicensePerson.setAddControl(dataCompanyIprTrademarkLicensePersonCreateCommand);
		boolean save = dataCompanyIprTrademarkLicensePersonGateway.save(dataCompanyIprTrademarkLicensePerson);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.toDataCompanyIprTrademarkLicensePersonVO(dataCompanyIprTrademarkLicensePerson));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标许可人创建指令创建企业知识产权商标许可人模型
	 * @param dataCompanyIprTrademarkLicensePersonCreateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkLicensePerson createByDataCompanyIprTrademarkLicensePersonCreateCommand(DataCompanyIprTrademarkLicensePersonCreateCommand dataCompanyIprTrademarkLicensePersonCreateCommand){
		DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson = DataCompanyIprTrademarkLicensePerson.create();
		DataCompanyIprTrademarkLicensePersonCreateCommandToDataCompanyIprTrademarkLicensePersonMapping.instance.fillDataCompanyIprTrademarkLicensePersonByDataCompanyIprTrademarkLicensePersonCreateCommand(dataCompanyIprTrademarkLicensePerson, dataCompanyIprTrademarkLicensePersonCreateCommand);
		return dataCompanyIprTrademarkLicensePerson;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprTrademarkLicensePersonCreateCommandToDataCompanyIprTrademarkLicensePersonMapping{
		DataCompanyIprTrademarkLicensePersonCreateCommandToDataCompanyIprTrademarkLicensePersonMapping instance = Mappers.getMapper( DataCompanyIprTrademarkLicensePersonCreateCommandToDataCompanyIprTrademarkLicensePersonMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkLicensePerson
		 * @param dataCompanyIprTrademarkLicensePersonCreateCommand
		 */
		void fillDataCompanyIprTrademarkLicensePersonByDataCompanyIprTrademarkLicensePersonCreateCommand(@MappingTarget DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson, DataCompanyIprTrademarkLicensePersonCreateCommand dataCompanyIprTrademarkLicensePersonCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkLicensePersonGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkLicensePersonGateway(DataCompanyIprTrademarkLicensePersonGateway dataCompanyIprTrademarkLicensePersonGateway) {
		this.dataCompanyIprTrademarkLicensePersonGateway = dataCompanyIprTrademarkLicensePersonGateway;
	}
}
