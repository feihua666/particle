package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyEndCaseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.data.domain.company.DataCompanyEndCase;
import com.particle.data.domain.company.gateway.DataCompanyEndCaseGateway;
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
 * 企业终本案件 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Component
@Validated
public class DataCompanyEndCaseCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyEndCaseGateway dataCompanyEndCaseGateway;

	/**
	 * 执行企业终本案件添加指令
	 * @param dataCompanyEndCaseCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEndCaseVO> execute(@Valid DataCompanyEndCaseCreateCommand dataCompanyEndCaseCreateCommand) {
		DataCompanyEndCase dataCompanyEndCase = createByDataCompanyEndCaseCreateCommand(dataCompanyEndCaseCreateCommand);
		dataCompanyEndCase.initForAdd();
		dataCompanyEndCase.setAddControl(dataCompanyEndCaseCreateCommand);
		boolean save = dataCompanyEndCaseGateway.save(dataCompanyEndCase);
		if (save) {
			return SingleResponse.of(DataCompanyEndCaseAppStructMapping.instance.toDataCompanyEndCaseVO(dataCompanyEndCase));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业终本案件创建指令创建企业终本案件模型
	 * @param dataCompanyEndCaseCreateCommand
	 * @return
	 */
	private DataCompanyEndCase createByDataCompanyEndCaseCreateCommand(DataCompanyEndCaseCreateCommand dataCompanyEndCaseCreateCommand){
		DataCompanyEndCase dataCompanyEndCase = DataCompanyEndCase.create();
		DataCompanyEndCaseCreateCommandToDataCompanyEndCaseMapping.instance.fillDataCompanyEndCaseByDataCompanyEndCaseCreateCommand(dataCompanyEndCase, dataCompanyEndCaseCreateCommand);
		return dataCompanyEndCase;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyEndCaseCreateCommandToDataCompanyEndCaseMapping{
		DataCompanyEndCaseCreateCommandToDataCompanyEndCaseMapping instance = Mappers.getMapper( DataCompanyEndCaseCreateCommandToDataCompanyEndCaseMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyEndCase
		 * @param dataCompanyEndCaseCreateCommand
		 */
		void fillDataCompanyEndCaseByDataCompanyEndCaseCreateCommand(@MappingTarget DataCompanyEndCase dataCompanyEndCase, DataCompanyEndCaseCreateCommand dataCompanyEndCaseCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyEndCaseGateway
	 */
	@Autowired
	public void setDataCompanyEndCaseGateway(DataCompanyEndCaseGateway dataCompanyEndCaseGateway) {
		this.dataCompanyEndCaseGateway = dataCompanyEndCaseGateway;
	}
}
