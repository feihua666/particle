package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyChangeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChange;
import com.particle.data.domain.company.gateway.DataCompanyIprPlantVarietyChangeGateway;
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
 * 企业知识产权植物新品种变更信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyChangeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyChangeGateway dataCompanyIprPlantVarietyChangeGateway;

	/**
	 * 执行企业知识产权植物新品种变更信息添加指令
	 * @param dataCompanyIprPlantVarietyChangeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyChangeVO> execute(@Valid DataCompanyIprPlantVarietyChangeCreateCommand dataCompanyIprPlantVarietyChangeCreateCommand) {
		DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange = createByDataCompanyIprPlantVarietyChangeCreateCommand(dataCompanyIprPlantVarietyChangeCreateCommand);
		dataCompanyIprPlantVarietyChange.initForAdd();
		dataCompanyIprPlantVarietyChange.setAddControl(dataCompanyIprPlantVarietyChangeCreateCommand);
		boolean save = dataCompanyIprPlantVarietyChangeGateway.save(dataCompanyIprPlantVarietyChange);
		if (save) {
			return SingleResponse.of(DataCompanyIprPlantVarietyChangeAppStructMapping.instance.toDataCompanyIprPlantVarietyChangeVO(dataCompanyIprPlantVarietyChange));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权植物新品种变更信息创建指令创建企业知识产权植物新品种变更信息模型
	 * @param dataCompanyIprPlantVarietyChangeCreateCommand
	 * @return
	 */
	private DataCompanyIprPlantVarietyChange createByDataCompanyIprPlantVarietyChangeCreateCommand(DataCompanyIprPlantVarietyChangeCreateCommand dataCompanyIprPlantVarietyChangeCreateCommand){
		DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange = DataCompanyIprPlantVarietyChange.create();
		DataCompanyIprPlantVarietyChangeCreateCommandToDataCompanyIprPlantVarietyChangeMapping.instance.fillDataCompanyIprPlantVarietyChangeByDataCompanyIprPlantVarietyChangeCreateCommand(dataCompanyIprPlantVarietyChange, dataCompanyIprPlantVarietyChangeCreateCommand);
		return dataCompanyIprPlantVarietyChange;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPlantVarietyChangeCreateCommandToDataCompanyIprPlantVarietyChangeMapping{
		DataCompanyIprPlantVarietyChangeCreateCommandToDataCompanyIprPlantVarietyChangeMapping instance = Mappers.getMapper( DataCompanyIprPlantVarietyChangeCreateCommandToDataCompanyIprPlantVarietyChangeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPlantVarietyChange
		 * @param dataCompanyIprPlantVarietyChangeCreateCommand
		 */
		void fillDataCompanyIprPlantVarietyChangeByDataCompanyIprPlantVarietyChangeCreateCommand(@MappingTarget DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange, DataCompanyIprPlantVarietyChangeCreateCommand dataCompanyIprPlantVarietyChangeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPlantVarietyChangeGateway
	 */
	@Autowired
	public void setDataCompanyIprPlantVarietyChangeGateway(DataCompanyIprPlantVarietyChangeGateway dataCompanyIprPlantVarietyChangeGateway) {
		this.dataCompanyIprPlantVarietyChangeGateway = dataCompanyIprPlantVarietyChangeGateway;
	}
}
