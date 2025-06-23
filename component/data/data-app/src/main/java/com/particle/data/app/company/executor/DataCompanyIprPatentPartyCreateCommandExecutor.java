package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import com.particle.data.domain.company.DataCompanyIprPatentParty;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPartyGateway;
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
 * 企业知识产权专利当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Component
@Validated
public class DataCompanyIprPatentPartyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPartyGateway dataCompanyIprPatentPartyGateway;

	/**
	 * 执行企业知识产权专利当事人添加指令
	 * @param dataCompanyIprPatentPartyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPartyVO> execute(@Valid DataCompanyIprPatentPartyCreateCommand dataCompanyIprPatentPartyCreateCommand) {
		DataCompanyIprPatentParty dataCompanyIprPatentParty = createByDataCompanyIprPatentPartyCreateCommand(dataCompanyIprPatentPartyCreateCommand);
		dataCompanyIprPatentParty.initForAdd();
		dataCompanyIprPatentParty.setAddControl(dataCompanyIprPatentPartyCreateCommand);
		boolean save = dataCompanyIprPatentPartyGateway.save(dataCompanyIprPatentParty);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentPartyAppStructMapping.instance.toDataCompanyIprPatentPartyVO(dataCompanyIprPatentParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利当事人创建指令创建企业知识产权专利当事人模型
	 * @param dataCompanyIprPatentPartyCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentParty createByDataCompanyIprPatentPartyCreateCommand(DataCompanyIprPatentPartyCreateCommand dataCompanyIprPatentPartyCreateCommand){
		DataCompanyIprPatentParty dataCompanyIprPatentParty = DataCompanyIprPatentParty.create();
		DataCompanyIprPatentPartyCreateCommandToDataCompanyIprPatentPartyMapping.instance.fillDataCompanyIprPatentPartyByDataCompanyIprPatentPartyCreateCommand(dataCompanyIprPatentParty, dataCompanyIprPatentPartyCreateCommand);
		return dataCompanyIprPatentParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentPartyCreateCommandToDataCompanyIprPatentPartyMapping{
		DataCompanyIprPatentPartyCreateCommandToDataCompanyIprPatentPartyMapping instance = Mappers.getMapper( DataCompanyIprPatentPartyCreateCommandToDataCompanyIprPatentPartyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentParty
		 * @param dataCompanyIprPatentPartyCreateCommand
		 */
		void fillDataCompanyIprPatentPartyByDataCompanyIprPatentPartyCreateCommand(@MappingTarget DataCompanyIprPatentParty dataCompanyIprPatentParty, DataCompanyIprPatentPartyCreateCommand dataCompanyIprPatentPartyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPartyGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPartyGateway(DataCompanyIprPatentPartyGateway dataCompanyIprPatentPartyGateway) {
		this.dataCompanyIprPatentPartyGateway = dataCompanyIprPatentPartyGateway;
	}
}
