package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementParty;
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
 * 企业法院公告当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementPartyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementPartyGateway dataCompanyCourtAnnouncementPartyGateway;

	/**
	 * 执行企业法院公告当事人添加指令
	 * @param dataCompanyCourtAnnouncementPartyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementPartyVO> execute(@Valid DataCompanyCourtAnnouncementPartyCreateCommand dataCompanyCourtAnnouncementPartyCreateCommand) {
		DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty = createByDataCompanyCourtAnnouncementPartyCreateCommand(dataCompanyCourtAnnouncementPartyCreateCommand);
		dataCompanyCourtAnnouncementParty.initForAdd();
		dataCompanyCourtAnnouncementParty.setAddControl(dataCompanyCourtAnnouncementPartyCreateCommand);
		boolean save = dataCompanyCourtAnnouncementPartyGateway.save(dataCompanyCourtAnnouncementParty);
		if (save) {
			return SingleResponse.of(DataCompanyCourtAnnouncementPartyAppStructMapping.instance.toDataCompanyCourtAnnouncementPartyVO(dataCompanyCourtAnnouncementParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业法院公告当事人创建指令创建企业法院公告当事人模型
	 * @param dataCompanyCourtAnnouncementPartyCreateCommand
	 * @return
	 */
	private DataCompanyCourtAnnouncementParty createByDataCompanyCourtAnnouncementPartyCreateCommand(DataCompanyCourtAnnouncementPartyCreateCommand dataCompanyCourtAnnouncementPartyCreateCommand){
		DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty = DataCompanyCourtAnnouncementParty.create();
		DataCompanyCourtAnnouncementPartyCreateCommandToDataCompanyCourtAnnouncementPartyMapping.instance.fillDataCompanyCourtAnnouncementPartyByDataCompanyCourtAnnouncementPartyCreateCommand(dataCompanyCourtAnnouncementParty, dataCompanyCourtAnnouncementPartyCreateCommand);
		return dataCompanyCourtAnnouncementParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyCourtAnnouncementPartyCreateCommandToDataCompanyCourtAnnouncementPartyMapping{
		DataCompanyCourtAnnouncementPartyCreateCommandToDataCompanyCourtAnnouncementPartyMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementPartyCreateCommandToDataCompanyCourtAnnouncementPartyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCourtAnnouncementParty
		 * @param dataCompanyCourtAnnouncementPartyCreateCommand
		 */
		void fillDataCompanyCourtAnnouncementPartyByDataCompanyCourtAnnouncementPartyCreateCommand(@MappingTarget DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty, DataCompanyCourtAnnouncementPartyCreateCommand dataCompanyCourtAnnouncementPartyCreateCommand);
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
