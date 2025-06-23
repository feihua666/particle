package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanySpotCheckAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanySpotCheckCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import com.particle.data.domain.company.DataCompanySpotCheck;
import com.particle.data.domain.company.gateway.DataCompanySpotCheckGateway;
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
 * 企业抽查检查 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Component
@Validated
public class DataCompanySpotCheckCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanySpotCheckGateway dataCompanySpotCheckGateway;

	/**
	 * 执行企业抽查检查添加指令
	 * @param dataCompanySpotCheckCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanySpotCheckVO> execute(@Valid DataCompanySpotCheckCreateCommand dataCompanySpotCheckCreateCommand) {
		DataCompanySpotCheck dataCompanySpotCheck = createByDataCompanySpotCheckCreateCommand(dataCompanySpotCheckCreateCommand);
		dataCompanySpotCheck.initForAdd();
		dataCompanySpotCheck.setAddControl(dataCompanySpotCheckCreateCommand);
		boolean save = dataCompanySpotCheckGateway.save(dataCompanySpotCheck);
		if (save) {
			return SingleResponse.of(DataCompanySpotCheckAppStructMapping.instance.toDataCompanySpotCheckVO(dataCompanySpotCheck));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业抽查检查创建指令创建企业抽查检查模型
	 * @param dataCompanySpotCheckCreateCommand
	 * @return
	 */
	private DataCompanySpotCheck createByDataCompanySpotCheckCreateCommand(DataCompanySpotCheckCreateCommand dataCompanySpotCheckCreateCommand){
		DataCompanySpotCheck dataCompanySpotCheck = DataCompanySpotCheck.create();
		DataCompanySpotCheckCreateCommandToDataCompanySpotCheckMapping.instance.fillDataCompanySpotCheckByDataCompanySpotCheckCreateCommand(dataCompanySpotCheck, dataCompanySpotCheckCreateCommand);
		return dataCompanySpotCheck;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanySpotCheckCreateCommandToDataCompanySpotCheckMapping{
		DataCompanySpotCheckCreateCommandToDataCompanySpotCheckMapping instance = Mappers.getMapper( DataCompanySpotCheckCreateCommandToDataCompanySpotCheckMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanySpotCheck
		 * @param dataCompanySpotCheckCreateCommand
		 */
		void fillDataCompanySpotCheckByDataCompanySpotCheckCreateCommand(@MappingTarget DataCompanySpotCheck dataCompanySpotCheck, DataCompanySpotCheckCreateCommand dataCompanySpotCheckCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanySpotCheckGateway
	 */
	@Autowired
	public void setDataCompanySpotCheckGateway(DataCompanySpotCheckGateway dataCompanySpotCheckGateway) {
		this.dataCompanySpotCheckGateway = dataCompanySpotCheckGateway;
	}
}
