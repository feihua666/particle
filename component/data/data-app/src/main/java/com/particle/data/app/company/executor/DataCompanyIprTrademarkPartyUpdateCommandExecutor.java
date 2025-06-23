package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkParty;
import com.particle.data.domain.company.DataCompanyIprTrademarkPartyId;
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
 * 企业知识产权商标当事人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprTrademarkPartyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPartyGateway dataCompanyIprTrademarkPartyGateway;

	/**
	 * 执行 企业知识产权商标当事人 更新指令
	 * @param dataCompanyIprTrademarkPartyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPartyVO> execute(@Valid DataCompanyIprTrademarkPartyUpdateCommand dataCompanyIprTrademarkPartyUpdateCommand) {
		DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty = createByDataCompanyIprTrademarkPartyUpdateCommand(dataCompanyIprTrademarkPartyUpdateCommand);
		dataCompanyIprTrademarkParty.initForUpdate();
		dataCompanyIprTrademarkParty.setUpdateControl(dataCompanyIprTrademarkPartyUpdateCommand);
		boolean save = dataCompanyIprTrademarkPartyGateway.save(dataCompanyIprTrademarkParty);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkPartyAppStructMapping.instance.toDataCompanyIprTrademarkPartyVO(dataCompanyIprTrademarkParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标当事人更新指令创建企业知识产权商标当事人模型
	 * @param dataCompanyIprTrademarkPartyUpdateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkParty createByDataCompanyIprTrademarkPartyUpdateCommand(DataCompanyIprTrademarkPartyUpdateCommand dataCompanyIprTrademarkPartyUpdateCommand){
		DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty = DataCompanyIprTrademarkParty.create();
		DataCompanyIprTrademarkPartyUpdateCommandToDataCompanyIprTrademarkPartyMapping.instance.fillDataCompanyIprTrademarkPartyByDataCompanyIprTrademarkPartyUpdateCommand(dataCompanyIprTrademarkParty, dataCompanyIprTrademarkPartyUpdateCommand);
		return dataCompanyIprTrademarkParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprTrademarkPartyUpdateCommandToDataCompanyIprTrademarkPartyMapping{
		DataCompanyIprTrademarkPartyUpdateCommandToDataCompanyIprTrademarkPartyMapping instance = Mappers.getMapper(DataCompanyIprTrademarkPartyUpdateCommandToDataCompanyIprTrademarkPartyMapping.class );

		default DataCompanyIprTrademarkPartyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprTrademarkPartyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkParty
		 * @param dataCompanyIprTrademarkPartyUpdateCommand
		 */
		void fillDataCompanyIprTrademarkPartyByDataCompanyIprTrademarkPartyUpdateCommand(@MappingTarget DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty, DataCompanyIprTrademarkPartyUpdateCommand dataCompanyIprTrademarkPartyUpdateCommand);
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
