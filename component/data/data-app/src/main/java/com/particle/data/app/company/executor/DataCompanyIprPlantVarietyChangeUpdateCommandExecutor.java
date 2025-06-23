package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyChangeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChange;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChangeId;
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
 * 企业知识产权植物新品种变更信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyChangeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyChangeGateway dataCompanyIprPlantVarietyChangeGateway;

	/**
	 * 执行 企业知识产权植物新品种变更信息 更新指令
	 * @param dataCompanyIprPlantVarietyChangeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyChangeVO> execute(@Valid DataCompanyIprPlantVarietyChangeUpdateCommand dataCompanyIprPlantVarietyChangeUpdateCommand) {
		DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange = createByDataCompanyIprPlantVarietyChangeUpdateCommand(dataCompanyIprPlantVarietyChangeUpdateCommand);
		dataCompanyIprPlantVarietyChange.initForUpdate();
		dataCompanyIprPlantVarietyChange.setUpdateControl(dataCompanyIprPlantVarietyChangeUpdateCommand);
		boolean save = dataCompanyIprPlantVarietyChangeGateway.save(dataCompanyIprPlantVarietyChange);
		if (save) {
			return SingleResponse.of(DataCompanyIprPlantVarietyChangeAppStructMapping.instance.toDataCompanyIprPlantVarietyChangeVO(dataCompanyIprPlantVarietyChange));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权植物新品种变更信息更新指令创建企业知识产权植物新品种变更信息模型
	 * @param dataCompanyIprPlantVarietyChangeUpdateCommand
	 * @return
	 */
	private DataCompanyIprPlantVarietyChange createByDataCompanyIprPlantVarietyChangeUpdateCommand(DataCompanyIprPlantVarietyChangeUpdateCommand dataCompanyIprPlantVarietyChangeUpdateCommand){
		DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange = DataCompanyIprPlantVarietyChange.create();
		DataCompanyIprPlantVarietyChangeUpdateCommandToDataCompanyIprPlantVarietyChangeMapping.instance.fillDataCompanyIprPlantVarietyChangeByDataCompanyIprPlantVarietyChangeUpdateCommand(dataCompanyIprPlantVarietyChange, dataCompanyIprPlantVarietyChangeUpdateCommand);
		return dataCompanyIprPlantVarietyChange;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPlantVarietyChangeUpdateCommandToDataCompanyIprPlantVarietyChangeMapping{
		DataCompanyIprPlantVarietyChangeUpdateCommandToDataCompanyIprPlantVarietyChangeMapping instance = Mappers.getMapper(DataCompanyIprPlantVarietyChangeUpdateCommandToDataCompanyIprPlantVarietyChangeMapping.class );

		default DataCompanyIprPlantVarietyChangeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPlantVarietyChangeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPlantVarietyChange
		 * @param dataCompanyIprPlantVarietyChangeUpdateCommand
		 */
		void fillDataCompanyIprPlantVarietyChangeByDataCompanyIprPlantVarietyChangeUpdateCommand(@MappingTarget DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange, DataCompanyIprPlantVarietyChangeUpdateCommand dataCompanyIprPlantVarietyChangeUpdateCommand);
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
