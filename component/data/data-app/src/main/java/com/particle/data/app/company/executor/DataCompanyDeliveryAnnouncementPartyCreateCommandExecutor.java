package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementParty;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementPartyGateway;
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
 * 企业送达公告当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementPartyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementPartyGateway dataCompanyDeliveryAnnouncementPartyGateway;

	/**
	 * 执行企业送达公告当事人添加指令
	 * @param dataCompanyDeliveryAnnouncementPartyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> execute(@Valid DataCompanyDeliveryAnnouncementPartyCreateCommand dataCompanyDeliveryAnnouncementPartyCreateCommand) {
		DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty = createByDataCompanyDeliveryAnnouncementPartyCreateCommand(dataCompanyDeliveryAnnouncementPartyCreateCommand);
		dataCompanyDeliveryAnnouncementParty.initForAdd();
		dataCompanyDeliveryAnnouncementParty.setAddControl(dataCompanyDeliveryAnnouncementPartyCreateCommand);
		boolean save = dataCompanyDeliveryAnnouncementPartyGateway.save(dataCompanyDeliveryAnnouncementParty);
		if (save) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.toDataCompanyDeliveryAnnouncementPartyVO(dataCompanyDeliveryAnnouncementParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业送达公告当事人创建指令创建企业送达公告当事人模型
	 * @param dataCompanyDeliveryAnnouncementPartyCreateCommand
	 * @return
	 */
	private DataCompanyDeliveryAnnouncementParty createByDataCompanyDeliveryAnnouncementPartyCreateCommand(DataCompanyDeliveryAnnouncementPartyCreateCommand dataCompanyDeliveryAnnouncementPartyCreateCommand){
		DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty = DataCompanyDeliveryAnnouncementParty.create();
		DataCompanyDeliveryAnnouncementPartyCreateCommandToDataCompanyDeliveryAnnouncementPartyMapping.instance.fillDataCompanyDeliveryAnnouncementPartyByDataCompanyDeliveryAnnouncementPartyCreateCommand(dataCompanyDeliveryAnnouncementParty, dataCompanyDeliveryAnnouncementPartyCreateCommand);
		return dataCompanyDeliveryAnnouncementParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyDeliveryAnnouncementPartyCreateCommandToDataCompanyDeliveryAnnouncementPartyMapping{
		DataCompanyDeliveryAnnouncementPartyCreateCommandToDataCompanyDeliveryAnnouncementPartyMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementPartyCreateCommandToDataCompanyDeliveryAnnouncementPartyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyDeliveryAnnouncementParty
		 * @param dataCompanyDeliveryAnnouncementPartyCreateCommand
		 */
		void fillDataCompanyDeliveryAnnouncementPartyByDataCompanyDeliveryAnnouncementPartyCreateCommand(@MappingTarget DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty, DataCompanyDeliveryAnnouncementPartyCreateCommand dataCompanyDeliveryAnnouncementPartyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementPartyGateway(DataCompanyDeliveryAnnouncementPartyGateway dataCompanyDeliveryAnnouncementPartyGateway) {
		this.dataCompanyDeliveryAnnouncementPartyGateway = dataCompanyDeliveryAnnouncementPartyGateway;
	}
}
