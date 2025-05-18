package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyPersonAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyPersonCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import com.particle.data.domain.company.DataCompanyPerson;
import com.particle.data.domain.company.gateway.DataCompanyPersonGateway;
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
 * 企业个人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Component
@Validated
public class DataCompanyPersonCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPersonGateway dataCompanyPersonGateway;

	/**
	 * 执行企业个人添加指令
	 * @param dataCompanyPersonCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPersonVO> execute(@Valid DataCompanyPersonCreateCommand dataCompanyPersonCreateCommand) {
		DataCompanyPerson dataCompanyPerson = createByDataCompanyPersonCreateCommand(dataCompanyPersonCreateCommand);
		dataCompanyPerson.initForAdd();
		dataCompanyPerson.setAddControl(dataCompanyPersonCreateCommand);
		boolean save = dataCompanyPersonGateway.save(dataCompanyPerson);
		if (save) {
			return SingleResponse.of(DataCompanyPersonAppStructMapping.instance.toDataCompanyPersonVO(dataCompanyPerson));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业个人创建指令创建企业个人模型
	 * @param dataCompanyPersonCreateCommand
	 * @return
	 */
	private DataCompanyPerson createByDataCompanyPersonCreateCommand(DataCompanyPersonCreateCommand dataCompanyPersonCreateCommand){
		DataCompanyPerson dataCompanyPerson = DataCompanyPerson.create();
		DataCompanyPersonCreateCommandToDataCompanyPersonMapping.instance.fillDataCompanyPersonByDataCompanyPersonCreateCommand(dataCompanyPerson, dataCompanyPersonCreateCommand);
		return dataCompanyPerson;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyPersonCreateCommandToDataCompanyPersonMapping{
		DataCompanyPersonCreateCommandToDataCompanyPersonMapping instance = Mappers.getMapper( DataCompanyPersonCreateCommandToDataCompanyPersonMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyPerson
		 * @param dataCompanyPersonCreateCommand
		 */
		void fillDataCompanyPersonByDataCompanyPersonCreateCommand(@MappingTarget DataCompanyPerson dataCompanyPerson, DataCompanyPersonCreateCommand dataCompanyPersonCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyPersonGateway
	 */
	@Autowired
	public void setDataCompanyPersonGateway(DataCompanyPersonGateway dataCompanyPersonGateway) {
		this.dataCompanyPersonGateway = dataCompanyPersonGateway;
	}
}
