package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumePartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeParty;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumePartyId;
import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumePartyGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业限制高消费当事人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumePartyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumePartyGateway dataCompanyRestrictHighConsumePartyGateway;

	/**
	 * 执行 企业限制高消费当事人 更新指令
	 * @param dataCompanyRestrictHighConsumePartyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumePartyVO> execute(@Valid DataCompanyRestrictHighConsumePartyUpdateCommand dataCompanyRestrictHighConsumePartyUpdateCommand) {
		DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty = createByDataCompanyRestrictHighConsumePartyUpdateCommand(dataCompanyRestrictHighConsumePartyUpdateCommand);
		dataCompanyRestrictHighConsumeParty.initForUpdate();
		dataCompanyRestrictHighConsumeParty.setUpdateControl(dataCompanyRestrictHighConsumePartyUpdateCommand);
		boolean save = dataCompanyRestrictHighConsumePartyGateway.save(dataCompanyRestrictHighConsumeParty);
		if (save) {
			return SingleResponse.of(DataCompanyRestrictHighConsumePartyAppStructMapping.instance.toDataCompanyRestrictHighConsumePartyVO(dataCompanyRestrictHighConsumeParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业限制高消费当事人更新指令创建企业限制高消费当事人模型
	 * @param dataCompanyRestrictHighConsumePartyUpdateCommand
	 * @return
	 */
	private DataCompanyRestrictHighConsumeParty createByDataCompanyRestrictHighConsumePartyUpdateCommand(DataCompanyRestrictHighConsumePartyUpdateCommand dataCompanyRestrictHighConsumePartyUpdateCommand){
		DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty = DataCompanyRestrictHighConsumeParty.create();
		DataCompanyRestrictHighConsumePartyUpdateCommandToDataCompanyRestrictHighConsumePartyMapping.instance.fillDataCompanyRestrictHighConsumePartyByDataCompanyRestrictHighConsumePartyUpdateCommand(dataCompanyRestrictHighConsumeParty, dataCompanyRestrictHighConsumePartyUpdateCommand);
		return dataCompanyRestrictHighConsumeParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyRestrictHighConsumePartyUpdateCommandToDataCompanyRestrictHighConsumePartyMapping{
		DataCompanyRestrictHighConsumePartyUpdateCommandToDataCompanyRestrictHighConsumePartyMapping instance = Mappers.getMapper(DataCompanyRestrictHighConsumePartyUpdateCommandToDataCompanyRestrictHighConsumePartyMapping.class );

		default DataCompanyRestrictHighConsumePartyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyRestrictHighConsumePartyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyRestrictHighConsumeParty
		 * @param dataCompanyRestrictHighConsumePartyUpdateCommand
		 */
		void fillDataCompanyRestrictHighConsumePartyByDataCompanyRestrictHighConsumePartyUpdateCommand(@MappingTarget DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty, DataCompanyRestrictHighConsumePartyUpdateCommand dataCompanyRestrictHighConsumePartyUpdateCommand);
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
