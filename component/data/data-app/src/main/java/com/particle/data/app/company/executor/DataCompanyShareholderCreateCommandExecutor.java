package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyShareholderAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyShareholderCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.domain.company.DataCompanyShareholder;
import com.particle.data.domain.company.gateway.DataCompanyShareholderGateway;
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
 * 企业股东 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Component
@Validated
public class DataCompanyShareholderCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyShareholderGateway dataCompanyShareholderGateway;

	/**
	 * 执行企业股东添加指令
	 * @param dataCompanyShareholderCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyShareholderVO> execute(@Valid DataCompanyShareholderCreateCommand dataCompanyShareholderCreateCommand) {
		DataCompanyShareholder dataCompanyShareholder = createByDataCompanyShareholderCreateCommand(dataCompanyShareholderCreateCommand);
		dataCompanyShareholder.setAddControl(dataCompanyShareholderCreateCommand);
		dataCompanyShareholder.initForAdd();
		boolean save = dataCompanyShareholderGateway.save(dataCompanyShareholder);
		if (save) {
			return SingleResponse.of(DataCompanyShareholderAppStructMapping.instance.toDataCompanyShareholderVO(dataCompanyShareholder));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业股东创建指令创建企业股东模型
	 * @param dataCompanyShareholderCreateCommand
	 * @return
	 */
	private DataCompanyShareholder createByDataCompanyShareholderCreateCommand(DataCompanyShareholderCreateCommand dataCompanyShareholderCreateCommand){
		DataCompanyShareholder dataCompanyShareholder = DataCompanyShareholder.create();
		DataCompanyShareholderCreateCommandToDataCompanyShareholderMapping.instance.fillDataCompanyShareholderByDataCompanyShareholderCreateCommand(dataCompanyShareholder, dataCompanyShareholderCreateCommand);
		return dataCompanyShareholder;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyShareholderCreateCommandToDataCompanyShareholderMapping{
		DataCompanyShareholderCreateCommandToDataCompanyShareholderMapping instance = Mappers.getMapper( DataCompanyShareholderCreateCommandToDataCompanyShareholderMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyShareholder
		 * @param dataCompanyShareholderCreateCommand
		 */
		void fillDataCompanyShareholderByDataCompanyShareholderCreateCommand(@MappingTarget DataCompanyShareholder dataCompanyShareholder, DataCompanyShareholderCreateCommand dataCompanyShareholderCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyShareholderGateway
	 */
	@Autowired
	public void setDataCompanyShareholderGateway(DataCompanyShareholderGateway dataCompanyShareholderGateway) {
		this.dataCompanyShareholderGateway = dataCompanyShareholderGateway;
	}
}
