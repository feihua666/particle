package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyEndCaseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.data.domain.company.DataCompanyEndCase;
import com.particle.data.domain.company.DataCompanyEndCaseId;
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
 * 企业终本案件 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyEndCaseUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyEndCaseGateway dataCompanyEndCaseGateway;

	/**
	 * 执行 企业终本案件 更新指令
	 * @param dataCompanyEndCaseUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEndCaseVO> execute(@Valid DataCompanyEndCaseUpdateCommand dataCompanyEndCaseUpdateCommand) {
		DataCompanyEndCase dataCompanyEndCase = createByDataCompanyEndCaseUpdateCommand(dataCompanyEndCaseUpdateCommand);
		dataCompanyEndCase.initForUpdate();
		dataCompanyEndCase.setUpdateControl(dataCompanyEndCaseUpdateCommand);
		boolean save = dataCompanyEndCaseGateway.save(dataCompanyEndCase);
		if (save) {
			return SingleResponse.of(DataCompanyEndCaseAppStructMapping.instance.toDataCompanyEndCaseVO(dataCompanyEndCase));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业终本案件更新指令创建企业终本案件模型
	 * @param dataCompanyEndCaseUpdateCommand
	 * @return
	 */
	private DataCompanyEndCase createByDataCompanyEndCaseUpdateCommand(DataCompanyEndCaseUpdateCommand dataCompanyEndCaseUpdateCommand){
		DataCompanyEndCase dataCompanyEndCase = DataCompanyEndCase.create();
		DataCompanyEndCaseUpdateCommandToDataCompanyEndCaseMapping.instance.fillDataCompanyEndCaseByDataCompanyEndCaseUpdateCommand(dataCompanyEndCase, dataCompanyEndCaseUpdateCommand);
		return dataCompanyEndCase;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyEndCaseUpdateCommandToDataCompanyEndCaseMapping{
		DataCompanyEndCaseUpdateCommandToDataCompanyEndCaseMapping instance = Mappers.getMapper(DataCompanyEndCaseUpdateCommandToDataCompanyEndCaseMapping.class );

		default DataCompanyEndCaseId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyEndCaseId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyEndCase
		 * @param dataCompanyEndCaseUpdateCommand
		 */
		void fillDataCompanyEndCaseByDataCompanyEndCaseUpdateCommand(@MappingTarget DataCompanyEndCase dataCompanyEndCase, DataCompanyEndCaseUpdateCommand dataCompanyEndCaseUpdateCommand);
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
