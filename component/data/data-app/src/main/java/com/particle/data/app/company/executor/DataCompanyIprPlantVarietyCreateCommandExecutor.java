package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.data.domain.company.DataCompanyIprPlantVariety;
import com.particle.data.domain.company.gateway.DataCompanyIprPlantVarietyGateway;
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
 * 企业知识产权植物新品种 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyGateway dataCompanyIprPlantVarietyGateway;

	/**
	 * 执行企业知识产权植物新品种添加指令
	 * @param dataCompanyIprPlantVarietyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyVO> execute(@Valid DataCompanyIprPlantVarietyCreateCommand dataCompanyIprPlantVarietyCreateCommand) {
		DataCompanyIprPlantVariety dataCompanyIprPlantVariety = createByDataCompanyIprPlantVarietyCreateCommand(dataCompanyIprPlantVarietyCreateCommand);
		dataCompanyIprPlantVariety.initForAdd();
		dataCompanyIprPlantVariety.setAddControl(dataCompanyIprPlantVarietyCreateCommand);
		boolean save = dataCompanyIprPlantVarietyGateway.save(dataCompanyIprPlantVariety);
		if (save) {
			return SingleResponse.of(DataCompanyIprPlantVarietyAppStructMapping.instance.toDataCompanyIprPlantVarietyVO(dataCompanyIprPlantVariety));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权植物新品种创建指令创建企业知识产权植物新品种模型
	 * @param dataCompanyIprPlantVarietyCreateCommand
	 * @return
	 */
	private DataCompanyIprPlantVariety createByDataCompanyIprPlantVarietyCreateCommand(DataCompanyIprPlantVarietyCreateCommand dataCompanyIprPlantVarietyCreateCommand){
		DataCompanyIprPlantVariety dataCompanyIprPlantVariety = DataCompanyIprPlantVariety.create();
		DataCompanyIprPlantVarietyCreateCommandToDataCompanyIprPlantVarietyMapping.instance.fillDataCompanyIprPlantVarietyByDataCompanyIprPlantVarietyCreateCommand(dataCompanyIprPlantVariety, dataCompanyIprPlantVarietyCreateCommand);
		return dataCompanyIprPlantVariety;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPlantVarietyCreateCommandToDataCompanyIprPlantVarietyMapping{
		DataCompanyIprPlantVarietyCreateCommandToDataCompanyIprPlantVarietyMapping instance = Mappers.getMapper( DataCompanyIprPlantVarietyCreateCommandToDataCompanyIprPlantVarietyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPlantVariety
		 * @param dataCompanyIprPlantVarietyCreateCommand
		 */
		void fillDataCompanyIprPlantVarietyByDataCompanyIprPlantVarietyCreateCommand(@MappingTarget DataCompanyIprPlantVariety dataCompanyIprPlantVariety, DataCompanyIprPlantVarietyCreateCommand dataCompanyIprPlantVarietyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPlantVarietyGateway
	 */
	@Autowired
	public void setDataCompanyIprPlantVarietyGateway(DataCompanyIprPlantVarietyGateway dataCompanyIprPlantVarietyGateway) {
		this.dataCompanyIprPlantVarietyGateway = dataCompanyIprPlantVarietyGateway;
	}
}
