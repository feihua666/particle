package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprIntegratedCircuitAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuit;
import com.particle.data.domain.company.gateway.DataCompanyIprIntegratedCircuitGateway;
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
 * 企业知识产权集成电路 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprIntegratedCircuitGateway dataCompanyIprIntegratedCircuitGateway;

	/**
	 * 执行企业知识产权集成电路添加指令
	 * @param dataCompanyIprIntegratedCircuitCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitVO> execute(@Valid DataCompanyIprIntegratedCircuitCreateCommand dataCompanyIprIntegratedCircuitCreateCommand) {
		DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit = createByDataCompanyIprIntegratedCircuitCreateCommand(dataCompanyIprIntegratedCircuitCreateCommand);
		dataCompanyIprIntegratedCircuit.initForAdd();
		dataCompanyIprIntegratedCircuit.setAddControl(dataCompanyIprIntegratedCircuitCreateCommand);
		boolean save = dataCompanyIprIntegratedCircuitGateway.save(dataCompanyIprIntegratedCircuit);
		if (save) {
			return SingleResponse.of(DataCompanyIprIntegratedCircuitAppStructMapping.instance.toDataCompanyIprIntegratedCircuitVO(dataCompanyIprIntegratedCircuit));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权集成电路创建指令创建企业知识产权集成电路模型
	 * @param dataCompanyIprIntegratedCircuitCreateCommand
	 * @return
	 */
	private DataCompanyIprIntegratedCircuit createByDataCompanyIprIntegratedCircuitCreateCommand(DataCompanyIprIntegratedCircuitCreateCommand dataCompanyIprIntegratedCircuitCreateCommand){
		DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit = DataCompanyIprIntegratedCircuit.create();
		DataCompanyIprIntegratedCircuitCreateCommandToDataCompanyIprIntegratedCircuitMapping.instance.fillDataCompanyIprIntegratedCircuitByDataCompanyIprIntegratedCircuitCreateCommand(dataCompanyIprIntegratedCircuit, dataCompanyIprIntegratedCircuitCreateCommand);
		return dataCompanyIprIntegratedCircuit;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprIntegratedCircuitCreateCommandToDataCompanyIprIntegratedCircuitMapping{
		DataCompanyIprIntegratedCircuitCreateCommandToDataCompanyIprIntegratedCircuitMapping instance = Mappers.getMapper( DataCompanyIprIntegratedCircuitCreateCommandToDataCompanyIprIntegratedCircuitMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprIntegratedCircuit
		 * @param dataCompanyIprIntegratedCircuitCreateCommand
		 */
		void fillDataCompanyIprIntegratedCircuitByDataCompanyIprIntegratedCircuitCreateCommand(@MappingTarget DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit, DataCompanyIprIntegratedCircuitCreateCommand dataCompanyIprIntegratedCircuitCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprIntegratedCircuitGateway
	 */
	@Autowired
	public void setDataCompanyIprIntegratedCircuitGateway(DataCompanyIprIntegratedCircuitGateway dataCompanyIprIntegratedCircuitGateway) {
		this.dataCompanyIprIntegratedCircuitGateway = dataCompanyIprIntegratedCircuitGateway;
	}
}
