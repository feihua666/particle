package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCaseFilingAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.data.domain.company.DataCompanyCaseFiling;
import com.particle.data.domain.company.gateway.DataCompanyCaseFilingGateway;
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
 * 企业立案信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Component
@Validated
public class DataCompanyCaseFilingCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCaseFilingGateway dataCompanyCaseFilingGateway;

	/**
	 * 执行企业立案信息添加指令
	 * @param dataCompanyCaseFilingCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingVO> execute(@Valid DataCompanyCaseFilingCreateCommand dataCompanyCaseFilingCreateCommand) {
		DataCompanyCaseFiling dataCompanyCaseFiling = createByDataCompanyCaseFilingCreateCommand(dataCompanyCaseFilingCreateCommand);
		dataCompanyCaseFiling.setAddControl(dataCompanyCaseFilingCreateCommand);
		boolean save = dataCompanyCaseFilingGateway.save(dataCompanyCaseFiling);
		if (save) {
			return SingleResponse.of(DataCompanyCaseFilingAppStructMapping.instance.toDataCompanyCaseFilingVO(dataCompanyCaseFiling));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业立案信息创建指令创建企业立案信息模型
	 * @param dataCompanyCaseFilingCreateCommand
	 * @return
	 */
	private DataCompanyCaseFiling createByDataCompanyCaseFilingCreateCommand(DataCompanyCaseFilingCreateCommand dataCompanyCaseFilingCreateCommand){
		DataCompanyCaseFiling dataCompanyCaseFiling = DataCompanyCaseFiling.create();
		DataCompanyCaseFilingCreateCommandToDataCompanyCaseFilingMapping.instance.fillDataCompanyCaseFilingByDataCompanyCaseFilingCreateCommand(dataCompanyCaseFiling, dataCompanyCaseFilingCreateCommand);
		return dataCompanyCaseFiling;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyCaseFilingCreateCommandToDataCompanyCaseFilingMapping{
		DataCompanyCaseFilingCreateCommandToDataCompanyCaseFilingMapping instance = Mappers.getMapper( DataCompanyCaseFilingCreateCommandToDataCompanyCaseFilingMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCaseFiling
		 * @param dataCompanyCaseFilingCreateCommand
		 */
		void fillDataCompanyCaseFilingByDataCompanyCaseFilingCreateCommand(@MappingTarget DataCompanyCaseFiling dataCompanyCaseFiling, DataCompanyCaseFilingCreateCommand dataCompanyCaseFilingCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyCaseFilingGateway
	 */
	@Autowired
	public void setDataCompanyCaseFilingGateway(DataCompanyCaseFilingGateway dataCompanyCaseFilingGateway) {
		this.dataCompanyCaseFilingGateway = dataCompanyCaseFilingGateway;
	}
}
