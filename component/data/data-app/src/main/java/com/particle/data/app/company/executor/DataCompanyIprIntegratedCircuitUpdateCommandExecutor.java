package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprIntegratedCircuitAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuit;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuitId;
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
 * 企业知识产权集成电路 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprIntegratedCircuitGateway dataCompanyIprIntegratedCircuitGateway;

	/**
	 * 执行 企业知识产权集成电路 更新指令
	 * @param dataCompanyIprIntegratedCircuitUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitVO> execute(@Valid DataCompanyIprIntegratedCircuitUpdateCommand dataCompanyIprIntegratedCircuitUpdateCommand) {
		DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit = createByDataCompanyIprIntegratedCircuitUpdateCommand(dataCompanyIprIntegratedCircuitUpdateCommand);
		dataCompanyIprIntegratedCircuit.initForUpdate();
		dataCompanyIprIntegratedCircuit.setUpdateControl(dataCompanyIprIntegratedCircuitUpdateCommand);
		boolean save = dataCompanyIprIntegratedCircuitGateway.save(dataCompanyIprIntegratedCircuit);
		if (save) {
			return SingleResponse.of(DataCompanyIprIntegratedCircuitAppStructMapping.instance.toDataCompanyIprIntegratedCircuitVO(dataCompanyIprIntegratedCircuit));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权集成电路更新指令创建企业知识产权集成电路模型
	 * @param dataCompanyIprIntegratedCircuitUpdateCommand
	 * @return
	 */
	private DataCompanyIprIntegratedCircuit createByDataCompanyIprIntegratedCircuitUpdateCommand(DataCompanyIprIntegratedCircuitUpdateCommand dataCompanyIprIntegratedCircuitUpdateCommand){
		DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit = DataCompanyIprIntegratedCircuit.create();
		DataCompanyIprIntegratedCircuitUpdateCommandToDataCompanyIprIntegratedCircuitMapping.instance.fillDataCompanyIprIntegratedCircuitByDataCompanyIprIntegratedCircuitUpdateCommand(dataCompanyIprIntegratedCircuit, dataCompanyIprIntegratedCircuitUpdateCommand);
		return dataCompanyIprIntegratedCircuit;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprIntegratedCircuitUpdateCommandToDataCompanyIprIntegratedCircuitMapping{
		DataCompanyIprIntegratedCircuitUpdateCommandToDataCompanyIprIntegratedCircuitMapping instance = Mappers.getMapper(DataCompanyIprIntegratedCircuitUpdateCommandToDataCompanyIprIntegratedCircuitMapping.class );

		default DataCompanyIprIntegratedCircuitId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprIntegratedCircuitId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprIntegratedCircuit
		 * @param dataCompanyIprIntegratedCircuitUpdateCommand
		 */
		void fillDataCompanyIprIntegratedCircuitByDataCompanyIprIntegratedCircuitUpdateCommand(@MappingTarget DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit, DataCompanyIprIntegratedCircuitUpdateCommand dataCompanyIprIntegratedCircuitUpdateCommand);
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
