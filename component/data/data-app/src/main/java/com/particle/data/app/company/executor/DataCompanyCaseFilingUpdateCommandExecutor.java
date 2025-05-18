package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCaseFilingAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.data.domain.company.DataCompanyCaseFiling;
import com.particle.data.domain.company.DataCompanyCaseFilingId;
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
 * 企业立案信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyCaseFilingUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCaseFilingGateway dataCompanyCaseFilingGateway;

	/**
	 * 执行 企业立案信息 更新指令
	 * @param dataCompanyCaseFilingUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingVO> execute(@Valid DataCompanyCaseFilingUpdateCommand dataCompanyCaseFilingUpdateCommand) {
		DataCompanyCaseFiling dataCompanyCaseFiling = createByDataCompanyCaseFilingUpdateCommand(dataCompanyCaseFilingUpdateCommand);
		dataCompanyCaseFiling.setUpdateControl(dataCompanyCaseFilingUpdateCommand);
		boolean save = dataCompanyCaseFilingGateway.save(dataCompanyCaseFiling);
		if (save) {
			return SingleResponse.of(DataCompanyCaseFilingAppStructMapping.instance.toDataCompanyCaseFilingVO(dataCompanyCaseFiling));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业立案信息更新指令创建企业立案信息模型
	 * @param dataCompanyCaseFilingUpdateCommand
	 * @return
	 */
	private DataCompanyCaseFiling createByDataCompanyCaseFilingUpdateCommand(DataCompanyCaseFilingUpdateCommand dataCompanyCaseFilingUpdateCommand){
		DataCompanyCaseFiling dataCompanyCaseFiling = DataCompanyCaseFiling.create();
		DataCompanyCaseFilingUpdateCommandToDataCompanyCaseFilingMapping.instance.fillDataCompanyCaseFilingByDataCompanyCaseFilingUpdateCommand(dataCompanyCaseFiling, dataCompanyCaseFilingUpdateCommand);
		return dataCompanyCaseFiling;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyCaseFilingUpdateCommandToDataCompanyCaseFilingMapping{
		DataCompanyCaseFilingUpdateCommandToDataCompanyCaseFilingMapping instance = Mappers.getMapper(DataCompanyCaseFilingUpdateCommandToDataCompanyCaseFilingMapping.class );

		default DataCompanyCaseFilingId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyCaseFilingId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCaseFiling
		 * @param dataCompanyCaseFilingUpdateCommand
		 */
		void fillDataCompanyCaseFilingByDataCompanyCaseFilingUpdateCommand(@MappingTarget DataCompanyCaseFiling dataCompanyCaseFiling, DataCompanyCaseFilingUpdateCommand dataCompanyCaseFilingUpdateCommand);
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
