package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCaseFilingPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.data.domain.company.DataCompanyCaseFilingParty;
import com.particle.data.domain.company.DataCompanyCaseFilingPartyId;
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
 * 企业立案信息当事人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyCaseFilingPartyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCaseFilingPartyGateway dataCompanyCaseFilingPartyGateway;

	/**
	 * 执行 企业立案信息当事人 更新指令
	 * @param dataCompanyCaseFilingPartyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingPartyVO> execute(@Valid DataCompanyCaseFilingPartyUpdateCommand dataCompanyCaseFilingPartyUpdateCommand) {
		DataCompanyCaseFilingParty dataCompanyCaseFilingParty = createByDataCompanyCaseFilingPartyUpdateCommand(dataCompanyCaseFilingPartyUpdateCommand);
		dataCompanyCaseFilingParty.initForUpdate();
		dataCompanyCaseFilingParty.setUpdateControl(dataCompanyCaseFilingPartyUpdateCommand);
		boolean save = dataCompanyCaseFilingPartyGateway.save(dataCompanyCaseFilingParty);
		if (save) {
			return SingleResponse.of(DataCompanyCaseFilingPartyAppStructMapping.instance.toDataCompanyCaseFilingPartyVO(dataCompanyCaseFilingParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业立案信息当事人更新指令创建企业立案信息当事人模型
	 * @param dataCompanyCaseFilingPartyUpdateCommand
	 * @return
	 */
	private DataCompanyCaseFilingParty createByDataCompanyCaseFilingPartyUpdateCommand(DataCompanyCaseFilingPartyUpdateCommand dataCompanyCaseFilingPartyUpdateCommand){
		DataCompanyCaseFilingParty dataCompanyCaseFilingParty = DataCompanyCaseFilingParty.create();
		DataCompanyCaseFilingPartyUpdateCommandToDataCompanyCaseFilingPartyMapping.instance.fillDataCompanyCaseFilingPartyByDataCompanyCaseFilingPartyUpdateCommand(dataCompanyCaseFilingParty, dataCompanyCaseFilingPartyUpdateCommand);
		return dataCompanyCaseFilingParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyCaseFilingPartyUpdateCommandToDataCompanyCaseFilingPartyMapping{
		DataCompanyCaseFilingPartyUpdateCommandToDataCompanyCaseFilingPartyMapping instance = Mappers.getMapper(DataCompanyCaseFilingPartyUpdateCommandToDataCompanyCaseFilingPartyMapping.class );

		default DataCompanyCaseFilingPartyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyCaseFilingPartyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCaseFilingParty
		 * @param dataCompanyCaseFilingPartyUpdateCommand
		 */
		void fillDataCompanyCaseFilingPartyByDataCompanyCaseFilingPartyUpdateCommand(@MappingTarget DataCompanyCaseFilingParty dataCompanyCaseFilingParty, DataCompanyCaseFilingPartyUpdateCommand dataCompanyCaseFilingPartyUpdateCommand);
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
