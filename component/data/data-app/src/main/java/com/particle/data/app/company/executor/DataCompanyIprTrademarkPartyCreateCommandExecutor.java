package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkParty;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkPartyGateway;
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
 * 企业知识产权商标当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Component
@Validated
public class DataCompanyIprTrademarkPartyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPartyGateway dataCompanyIprTrademarkPartyGateway;

	/**
	 * 执行企业知识产权商标当事人添加指令
	 * @param dataCompanyIprTrademarkPartyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPartyVO> execute(@Valid DataCompanyIprTrademarkPartyCreateCommand dataCompanyIprTrademarkPartyCreateCommand) {
		DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty = createByDataCompanyIprTrademarkPartyCreateCommand(dataCompanyIprTrademarkPartyCreateCommand);
		dataCompanyIprTrademarkParty.initForAdd();
		dataCompanyIprTrademarkParty.setAddControl(dataCompanyIprTrademarkPartyCreateCommand);
		boolean save = dataCompanyIprTrademarkPartyGateway.save(dataCompanyIprTrademarkParty);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkPartyAppStructMapping.instance.toDataCompanyIprTrademarkPartyVO(dataCompanyIprTrademarkParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标当事人创建指令创建企业知识产权商标当事人模型
	 * @param dataCompanyIprTrademarkPartyCreateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkParty createByDataCompanyIprTrademarkPartyCreateCommand(DataCompanyIprTrademarkPartyCreateCommand dataCompanyIprTrademarkPartyCreateCommand){
		DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty = DataCompanyIprTrademarkParty.create();
		DataCompanyIprTrademarkPartyCreateCommandToDataCompanyIprTrademarkPartyMapping.instance.fillDataCompanyIprTrademarkPartyByDataCompanyIprTrademarkPartyCreateCommand(dataCompanyIprTrademarkParty, dataCompanyIprTrademarkPartyCreateCommand);
		return dataCompanyIprTrademarkParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprTrademarkPartyCreateCommandToDataCompanyIprTrademarkPartyMapping{
		DataCompanyIprTrademarkPartyCreateCommandToDataCompanyIprTrademarkPartyMapping instance = Mappers.getMapper( DataCompanyIprTrademarkPartyCreateCommandToDataCompanyIprTrademarkPartyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkParty
		 * @param dataCompanyIprTrademarkPartyCreateCommand
		 */
		void fillDataCompanyIprTrademarkPartyByDataCompanyIprTrademarkPartyCreateCommand(@MappingTarget DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty, DataCompanyIprTrademarkPartyCreateCommand dataCompanyIprTrademarkPartyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkPartyGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkPartyGateway(DataCompanyIprTrademarkPartyGateway dataCompanyIprTrademarkPartyGateway) {
		this.dataCompanyIprTrademarkPartyGateway = dataCompanyIprTrademarkPartyGateway;
	}
}
