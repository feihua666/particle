package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcFinancingAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.data.domain.company.DataCompanyVcFinancing;
import com.particle.data.domain.company.gateway.DataCompanyVcFinancingGateway;
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
 * 企业融资 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Component
@Validated
public class DataCompanyVcFinancingCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcFinancingGateway dataCompanyVcFinancingGateway;

	/**
	 * 执行企业融资添加指令
	 * @param dataCompanyVcFinancingCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingVO> execute(@Valid DataCompanyVcFinancingCreateCommand dataCompanyVcFinancingCreateCommand) {
		DataCompanyVcFinancing dataCompanyVcFinancing = createByDataCompanyVcFinancingCreateCommand(dataCompanyVcFinancingCreateCommand);
		dataCompanyVcFinancing.initForAdd();
		dataCompanyVcFinancing.setAddControl(dataCompanyVcFinancingCreateCommand);
		boolean save = dataCompanyVcFinancingGateway.save(dataCompanyVcFinancing);
		if (save) {
			return SingleResponse.of(DataCompanyVcFinancingAppStructMapping.instance.toDataCompanyVcFinancingVO(dataCompanyVcFinancing));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业融资创建指令创建企业融资模型
	 * @param dataCompanyVcFinancingCreateCommand
	 * @return
	 */
	private DataCompanyVcFinancing createByDataCompanyVcFinancingCreateCommand(DataCompanyVcFinancingCreateCommand dataCompanyVcFinancingCreateCommand){
		DataCompanyVcFinancing dataCompanyVcFinancing = DataCompanyVcFinancing.create();
		DataCompanyVcFinancingCreateCommandToDataCompanyVcFinancingMapping.instance.fillDataCompanyVcFinancingByDataCompanyVcFinancingCreateCommand(dataCompanyVcFinancing, dataCompanyVcFinancingCreateCommand);
		return dataCompanyVcFinancing;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyVcFinancingCreateCommandToDataCompanyVcFinancingMapping{
		DataCompanyVcFinancingCreateCommandToDataCompanyVcFinancingMapping instance = Mappers.getMapper( DataCompanyVcFinancingCreateCommandToDataCompanyVcFinancingMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcFinancing
		 * @param dataCompanyVcFinancingCreateCommand
		 */
		void fillDataCompanyVcFinancingByDataCompanyVcFinancingCreateCommand(@MappingTarget DataCompanyVcFinancing dataCompanyVcFinancing, DataCompanyVcFinancingCreateCommand dataCompanyVcFinancingCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcFinancingGateway
	 */
	@Autowired
	public void setDataCompanyVcFinancingGateway(DataCompanyVcFinancingGateway dataCompanyVcFinancingGateway) {
		this.dataCompanyVcFinancingGateway = dataCompanyVcFinancingGateway;
	}
}
