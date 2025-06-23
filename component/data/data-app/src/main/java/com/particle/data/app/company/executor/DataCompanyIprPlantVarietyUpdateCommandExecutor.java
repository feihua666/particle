package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.data.domain.company.DataCompanyIprPlantVariety;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyId;
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
 * 企业知识产权植物新品种 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyGateway dataCompanyIprPlantVarietyGateway;

	/**
	 * 执行 企业知识产权植物新品种 更新指令
	 * @param dataCompanyIprPlantVarietyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyVO> execute(@Valid DataCompanyIprPlantVarietyUpdateCommand dataCompanyIprPlantVarietyUpdateCommand) {
		DataCompanyIprPlantVariety dataCompanyIprPlantVariety = createByDataCompanyIprPlantVarietyUpdateCommand(dataCompanyIprPlantVarietyUpdateCommand);
		dataCompanyIprPlantVariety.initForUpdate();
		dataCompanyIprPlantVariety.setUpdateControl(dataCompanyIprPlantVarietyUpdateCommand);
		boolean save = dataCompanyIprPlantVarietyGateway.save(dataCompanyIprPlantVariety);
		if (save) {
			return SingleResponse.of(DataCompanyIprPlantVarietyAppStructMapping.instance.toDataCompanyIprPlantVarietyVO(dataCompanyIprPlantVariety));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权植物新品种更新指令创建企业知识产权植物新品种模型
	 * @param dataCompanyIprPlantVarietyUpdateCommand
	 * @return
	 */
	private DataCompanyIprPlantVariety createByDataCompanyIprPlantVarietyUpdateCommand(DataCompanyIprPlantVarietyUpdateCommand dataCompanyIprPlantVarietyUpdateCommand){
		DataCompanyIprPlantVariety dataCompanyIprPlantVariety = DataCompanyIprPlantVariety.create();
		DataCompanyIprPlantVarietyUpdateCommandToDataCompanyIprPlantVarietyMapping.instance.fillDataCompanyIprPlantVarietyByDataCompanyIprPlantVarietyUpdateCommand(dataCompanyIprPlantVariety, dataCompanyIprPlantVarietyUpdateCommand);
		return dataCompanyIprPlantVariety;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPlantVarietyUpdateCommandToDataCompanyIprPlantVarietyMapping{
		DataCompanyIprPlantVarietyUpdateCommandToDataCompanyIprPlantVarietyMapping instance = Mappers.getMapper(DataCompanyIprPlantVarietyUpdateCommandToDataCompanyIprPlantVarietyMapping.class );

		default DataCompanyIprPlantVarietyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPlantVarietyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPlantVariety
		 * @param dataCompanyIprPlantVarietyUpdateCommand
		 */
		void fillDataCompanyIprPlantVarietyByDataCompanyIprPlantVarietyUpdateCommand(@MappingTarget DataCompanyIprPlantVariety dataCompanyIprPlantVariety, DataCompanyIprPlantVarietyUpdateCommand dataCompanyIprPlantVarietyUpdateCommand);
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
