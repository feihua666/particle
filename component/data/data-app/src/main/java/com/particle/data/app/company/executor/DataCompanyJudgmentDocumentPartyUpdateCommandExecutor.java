package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentParty;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentPartyId;
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
 * 企业裁判文书当事人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentPartyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentPartyGateway dataCompanyJudgmentDocumentPartyGateway;

	/**
	 * 执行 企业裁判文书当事人 更新指令
	 * @param dataCompanyJudgmentDocumentPartyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentPartyVO> execute(@Valid DataCompanyJudgmentDocumentPartyUpdateCommand dataCompanyJudgmentDocumentPartyUpdateCommand) {
		DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty = createByDataCompanyJudgmentDocumentPartyUpdateCommand(dataCompanyJudgmentDocumentPartyUpdateCommand);
		dataCompanyJudgmentDocumentParty.setUpdateControl(dataCompanyJudgmentDocumentPartyUpdateCommand);
		boolean save = dataCompanyJudgmentDocumentPartyGateway.save(dataCompanyJudgmentDocumentParty);
		if (save) {
			return SingleResponse.of(DataCompanyJudgmentDocumentPartyAppStructMapping.instance.toDataCompanyJudgmentDocumentPartyVO(dataCompanyJudgmentDocumentParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业裁判文书当事人更新指令创建企业裁判文书当事人模型
	 * @param dataCompanyJudgmentDocumentPartyUpdateCommand
	 * @return
	 */
	private DataCompanyJudgmentDocumentParty createByDataCompanyJudgmentDocumentPartyUpdateCommand(DataCompanyJudgmentDocumentPartyUpdateCommand dataCompanyJudgmentDocumentPartyUpdateCommand){
		DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty = DataCompanyJudgmentDocumentParty.create();
		DataCompanyJudgmentDocumentPartyUpdateCommandToDataCompanyJudgmentDocumentPartyMapping.instance.fillDataCompanyJudgmentDocumentPartyByDataCompanyJudgmentDocumentPartyUpdateCommand(dataCompanyJudgmentDocumentParty, dataCompanyJudgmentDocumentPartyUpdateCommand);
		return dataCompanyJudgmentDocumentParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyJudgmentDocumentPartyUpdateCommandToDataCompanyJudgmentDocumentPartyMapping{
		DataCompanyJudgmentDocumentPartyUpdateCommandToDataCompanyJudgmentDocumentPartyMapping instance = Mappers.getMapper(DataCompanyJudgmentDocumentPartyUpdateCommandToDataCompanyJudgmentDocumentPartyMapping.class );

		default DataCompanyJudgmentDocumentPartyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyJudgmentDocumentPartyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyJudgmentDocumentParty
		 * @param dataCompanyJudgmentDocumentPartyUpdateCommand
		 */
		void fillDataCompanyJudgmentDocumentPartyByDataCompanyJudgmentDocumentPartyUpdateCommand(@MappingTarget DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty, DataCompanyJudgmentDocumentPartyUpdateCommand dataCompanyJudgmentDocumentPartyUpdateCommand);
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
