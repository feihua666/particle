package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCaseFilingPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.data.domain.company.DataCompanyCaseFilingParty;
import com.particle.data.domain.company.gateway.DataCompanyCaseFilingPartyGateway;
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
 * 企业立案信息当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Component
@Validated
public class DataCompanyCaseFilingPartyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCaseFilingPartyGateway dataCompanyCaseFilingPartyGateway;

	/**
	 * 执行企业立案信息当事人添加指令
	 * @param dataCompanyCaseFilingPartyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingPartyVO> execute(@Valid DataCompanyCaseFilingPartyCreateCommand dataCompanyCaseFilingPartyCreateCommand) {
		DataCompanyCaseFilingParty dataCompanyCaseFilingParty = createByDataCompanyCaseFilingPartyCreateCommand(dataCompanyCaseFilingPartyCreateCommand);
		dataCompanyCaseFilingParty.initForAdd();
		dataCompanyCaseFilingParty.setAddControl(dataCompanyCaseFilingPartyCreateCommand);
		boolean save = dataCompanyCaseFilingPartyGateway.save(dataCompanyCaseFilingParty);
		if (save) {
			return SingleResponse.of(DataCompanyCaseFilingPartyAppStructMapping.instance.toDataCompanyCaseFilingPartyVO(dataCompanyCaseFilingParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业立案信息当事人创建指令创建企业立案信息当事人模型
	 * @param dataCompanyCaseFilingPartyCreateCommand
	 * @return
	 */
	private DataCompanyCaseFilingParty createByDataCompanyCaseFilingPartyCreateCommand(DataCompanyCaseFilingPartyCreateCommand dataCompanyCaseFilingPartyCreateCommand){
		DataCompanyCaseFilingParty dataCompanyCaseFilingParty = DataCompanyCaseFilingParty.create();
		DataCompanyCaseFilingPartyCreateCommandToDataCompanyCaseFilingPartyMapping.instance.fillDataCompanyCaseFilingPartyByDataCompanyCaseFilingPartyCreateCommand(dataCompanyCaseFilingParty, dataCompanyCaseFilingPartyCreateCommand);
		return dataCompanyCaseFilingParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyCaseFilingPartyCreateCommandToDataCompanyCaseFilingPartyMapping{
		DataCompanyCaseFilingPartyCreateCommandToDataCompanyCaseFilingPartyMapping instance = Mappers.getMapper( DataCompanyCaseFilingPartyCreateCommandToDataCompanyCaseFilingPartyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCaseFilingParty
		 * @param dataCompanyCaseFilingPartyCreateCommand
		 */
		void fillDataCompanyCaseFilingPartyByDataCompanyCaseFilingPartyCreateCommand(@MappingTarget DataCompanyCaseFilingParty dataCompanyCaseFilingParty, DataCompanyCaseFilingPartyCreateCommand dataCompanyCaseFilingPartyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyCaseFilingPartyGateway
	 */
	@Autowired
	public void setDataCompanyCaseFilingPartyGateway(DataCompanyCaseFilingPartyGateway dataCompanyCaseFilingPartyGateway) {
		this.dataCompanyCaseFilingPartyGateway = dataCompanyCaseFilingPartyGateway;
	}
}
