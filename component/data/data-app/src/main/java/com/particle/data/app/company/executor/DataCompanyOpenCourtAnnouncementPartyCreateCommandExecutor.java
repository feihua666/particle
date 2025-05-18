package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementParty;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementPartyGateway;
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
 * 企业开庭公告当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementPartyGateway dataCompanyOpenCourtAnnouncementPartyGateway;

	/**
	 * 执行企业开庭公告当事人添加指令
	 * @param dataCompanyOpenCourtAnnouncementPartyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> execute(@Valid DataCompanyOpenCourtAnnouncementPartyCreateCommand dataCompanyOpenCourtAnnouncementPartyCreateCommand) {
		DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty = createByDataCompanyOpenCourtAnnouncementPartyCreateCommand(dataCompanyOpenCourtAnnouncementPartyCreateCommand);
		dataCompanyOpenCourtAnnouncementParty.setAddControl(dataCompanyOpenCourtAnnouncementPartyCreateCommand);
		boolean save = dataCompanyOpenCourtAnnouncementPartyGateway.save(dataCompanyOpenCourtAnnouncementParty);
		if (save) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementPartyVO(dataCompanyOpenCourtAnnouncementParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业开庭公告当事人创建指令创建企业开庭公告当事人模型
	 * @param dataCompanyOpenCourtAnnouncementPartyCreateCommand
	 * @return
	 */
	private DataCompanyOpenCourtAnnouncementParty createByDataCompanyOpenCourtAnnouncementPartyCreateCommand(DataCompanyOpenCourtAnnouncementPartyCreateCommand dataCompanyOpenCourtAnnouncementPartyCreateCommand){
		DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty = DataCompanyOpenCourtAnnouncementParty.create();
		DataCompanyOpenCourtAnnouncementPartyCreateCommandToDataCompanyOpenCourtAnnouncementPartyMapping.instance.fillDataCompanyOpenCourtAnnouncementPartyByDataCompanyOpenCourtAnnouncementPartyCreateCommand(dataCompanyOpenCourtAnnouncementParty, dataCompanyOpenCourtAnnouncementPartyCreateCommand);
		return dataCompanyOpenCourtAnnouncementParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyOpenCourtAnnouncementPartyCreateCommandToDataCompanyOpenCourtAnnouncementPartyMapping{
		DataCompanyOpenCourtAnnouncementPartyCreateCommandToDataCompanyOpenCourtAnnouncementPartyMapping instance = Mappers.getMapper( DataCompanyOpenCourtAnnouncementPartyCreateCommandToDataCompanyOpenCourtAnnouncementPartyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyOpenCourtAnnouncementParty
		 * @param dataCompanyOpenCourtAnnouncementPartyCreateCommand
		 */
		void fillDataCompanyOpenCourtAnnouncementPartyByDataCompanyOpenCourtAnnouncementPartyCreateCommand(@MappingTarget DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty, DataCompanyOpenCourtAnnouncementPartyCreateCommand dataCompanyOpenCourtAnnouncementPartyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementPartyGateway(DataCompanyOpenCourtAnnouncementPartyGateway dataCompanyOpenCourtAnnouncementPartyGateway) {
		this.dataCompanyOpenCourtAnnouncementPartyGateway = dataCompanyOpenCourtAnnouncementPartyGateway;
	}
}
