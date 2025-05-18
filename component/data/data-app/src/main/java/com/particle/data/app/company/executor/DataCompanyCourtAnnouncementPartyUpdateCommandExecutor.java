package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementPartyId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementPartyGateway;
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
 * 企业法院公告当事人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementPartyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementPartyGateway dataCompanyCourtAnnouncementPartyGateway;

	/**
	 * 执行 企业法院公告当事人 更新指令
	 * @param dataCompanyCourtAnnouncementPartyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementPartyVO> execute(@Valid DataCompanyCourtAnnouncementPartyUpdateCommand dataCompanyCourtAnnouncementPartyUpdateCommand) {
		DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty = createByDataCompanyCourtAnnouncementPartyUpdateCommand(dataCompanyCourtAnnouncementPartyUpdateCommand);
		dataCompanyCourtAnnouncementParty.initForUpdate();
		dataCompanyCourtAnnouncementParty.setUpdateControl(dataCompanyCourtAnnouncementPartyUpdateCommand);
		boolean save = dataCompanyCourtAnnouncementPartyGateway.save(dataCompanyCourtAnnouncementParty);
		if (save) {
			return SingleResponse.of(DataCompanyCourtAnnouncementPartyAppStructMapping.instance.toDataCompanyCourtAnnouncementPartyVO(dataCompanyCourtAnnouncementParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业法院公告当事人更新指令创建企业法院公告当事人模型
	 * @param dataCompanyCourtAnnouncementPartyUpdateCommand
	 * @return
	 */
	private DataCompanyCourtAnnouncementParty createByDataCompanyCourtAnnouncementPartyUpdateCommand(DataCompanyCourtAnnouncementPartyUpdateCommand dataCompanyCourtAnnouncementPartyUpdateCommand){
		DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty = DataCompanyCourtAnnouncementParty.create();
		DataCompanyCourtAnnouncementPartyUpdateCommandToDataCompanyCourtAnnouncementPartyMapping.instance.fillDataCompanyCourtAnnouncementPartyByDataCompanyCourtAnnouncementPartyUpdateCommand(dataCompanyCourtAnnouncementParty, dataCompanyCourtAnnouncementPartyUpdateCommand);
		return dataCompanyCourtAnnouncementParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyCourtAnnouncementPartyUpdateCommandToDataCompanyCourtAnnouncementPartyMapping{
		DataCompanyCourtAnnouncementPartyUpdateCommandToDataCompanyCourtAnnouncementPartyMapping instance = Mappers.getMapper(DataCompanyCourtAnnouncementPartyUpdateCommandToDataCompanyCourtAnnouncementPartyMapping.class );

		default DataCompanyCourtAnnouncementPartyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyCourtAnnouncementPartyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCourtAnnouncementParty
		 * @param dataCompanyCourtAnnouncementPartyUpdateCommand
		 */
		void fillDataCompanyCourtAnnouncementPartyByDataCompanyCourtAnnouncementPartyUpdateCommand(@MappingTarget DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty, DataCompanyCourtAnnouncementPartyUpdateCommand dataCompanyCourtAnnouncementPartyUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementPartyGateway(DataCompanyCourtAnnouncementPartyGateway dataCompanyCourtAnnouncementPartyGateway) {
		this.dataCompanyCourtAnnouncementPartyGateway = dataCompanyCourtAnnouncementPartyGateway;
	}
}
