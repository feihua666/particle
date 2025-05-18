package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentParty;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentPartyGateway;
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
 * 企业裁判文书当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentPartyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentPartyGateway dataCompanyJudgmentDocumentPartyGateway;

	/**
	 * 执行企业裁判文书当事人添加指令
	 * @param dataCompanyJudgmentDocumentPartyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentPartyVO> execute(@Valid DataCompanyJudgmentDocumentPartyCreateCommand dataCompanyJudgmentDocumentPartyCreateCommand) {
		DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty = createByDataCompanyJudgmentDocumentPartyCreateCommand(dataCompanyJudgmentDocumentPartyCreateCommand);
		dataCompanyJudgmentDocumentParty.setAddControl(dataCompanyJudgmentDocumentPartyCreateCommand);
		boolean save = dataCompanyJudgmentDocumentPartyGateway.save(dataCompanyJudgmentDocumentParty);
		if (save) {
			return SingleResponse.of(DataCompanyJudgmentDocumentPartyAppStructMapping.instance.toDataCompanyJudgmentDocumentPartyVO(dataCompanyJudgmentDocumentParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业裁判文书当事人创建指令创建企业裁判文书当事人模型
	 * @param dataCompanyJudgmentDocumentPartyCreateCommand
	 * @return
	 */
	private DataCompanyJudgmentDocumentParty createByDataCompanyJudgmentDocumentPartyCreateCommand(DataCompanyJudgmentDocumentPartyCreateCommand dataCompanyJudgmentDocumentPartyCreateCommand){
		DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty = DataCompanyJudgmentDocumentParty.create();
		DataCompanyJudgmentDocumentPartyCreateCommandToDataCompanyJudgmentDocumentPartyMapping.instance.fillDataCompanyJudgmentDocumentPartyByDataCompanyJudgmentDocumentPartyCreateCommand(dataCompanyJudgmentDocumentParty, dataCompanyJudgmentDocumentPartyCreateCommand);
		return dataCompanyJudgmentDocumentParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyJudgmentDocumentPartyCreateCommandToDataCompanyJudgmentDocumentPartyMapping{
		DataCompanyJudgmentDocumentPartyCreateCommandToDataCompanyJudgmentDocumentPartyMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentPartyCreateCommandToDataCompanyJudgmentDocumentPartyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyJudgmentDocumentParty
		 * @param dataCompanyJudgmentDocumentPartyCreateCommand
		 */
		void fillDataCompanyJudgmentDocumentPartyByDataCompanyJudgmentDocumentPartyCreateCommand(@MappingTarget DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty, DataCompanyJudgmentDocumentPartyCreateCommand dataCompanyJudgmentDocumentPartyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyJudgmentDocumentPartyGateway
	 */
	@Autowired
	public void setDataCompanyJudgmentDocumentPartyGateway(DataCompanyJudgmentDocumentPartyGateway dataCompanyJudgmentDocumentPartyGateway) {
		this.dataCompanyJudgmentDocumentPartyGateway = dataCompanyJudgmentDocumentPartyGateway;
	}
}
