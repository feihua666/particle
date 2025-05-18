package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumePartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeParty;
import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumePartyGateway;
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
 * 企业限制高消费当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumePartyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumePartyGateway dataCompanyRestrictHighConsumePartyGateway;

	/**
	 * 执行企业限制高消费当事人添加指令
	 * @param dataCompanyRestrictHighConsumePartyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumePartyVO> execute(@Valid DataCompanyRestrictHighConsumePartyCreateCommand dataCompanyRestrictHighConsumePartyCreateCommand) {
		DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty = createByDataCompanyRestrictHighConsumePartyCreateCommand(dataCompanyRestrictHighConsumePartyCreateCommand);
		dataCompanyRestrictHighConsumeParty.initForAdd();
		dataCompanyRestrictHighConsumeParty.setAddControl(dataCompanyRestrictHighConsumePartyCreateCommand);
		boolean save = dataCompanyRestrictHighConsumePartyGateway.save(dataCompanyRestrictHighConsumeParty);
		if (save) {
			return SingleResponse.of(DataCompanyRestrictHighConsumePartyAppStructMapping.instance.toDataCompanyRestrictHighConsumePartyVO(dataCompanyRestrictHighConsumeParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业限制高消费当事人创建指令创建企业限制高消费当事人模型
	 * @param dataCompanyRestrictHighConsumePartyCreateCommand
	 * @return
	 */
	private DataCompanyRestrictHighConsumeParty createByDataCompanyRestrictHighConsumePartyCreateCommand(DataCompanyRestrictHighConsumePartyCreateCommand dataCompanyRestrictHighConsumePartyCreateCommand){
		DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty = DataCompanyRestrictHighConsumeParty.create();
		DataCompanyRestrictHighConsumePartyCreateCommandToDataCompanyRestrictHighConsumePartyMapping.instance.fillDataCompanyRestrictHighConsumePartyByDataCompanyRestrictHighConsumePartyCreateCommand(dataCompanyRestrictHighConsumeParty, dataCompanyRestrictHighConsumePartyCreateCommand);
		return dataCompanyRestrictHighConsumeParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyRestrictHighConsumePartyCreateCommandToDataCompanyRestrictHighConsumePartyMapping{
		DataCompanyRestrictHighConsumePartyCreateCommandToDataCompanyRestrictHighConsumePartyMapping instance = Mappers.getMapper( DataCompanyRestrictHighConsumePartyCreateCommandToDataCompanyRestrictHighConsumePartyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyRestrictHighConsumeParty
		 * @param dataCompanyRestrictHighConsumePartyCreateCommand
		 */
		void fillDataCompanyRestrictHighConsumePartyByDataCompanyRestrictHighConsumePartyCreateCommand(@MappingTarget DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty, DataCompanyRestrictHighConsumePartyCreateCommand dataCompanyRestrictHighConsumePartyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyRestrictHighConsumePartyGateway
	 */
	@Autowired
	public void setDataCompanyRestrictHighConsumePartyGateway(DataCompanyRestrictHighConsumePartyGateway dataCompanyRestrictHighConsumePartyGateway) {
		this.dataCompanyRestrictHighConsumePartyGateway = dataCompanyRestrictHighConsumePartyGateway;
	}
}
