package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementParty;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementPartyId;
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
 * 企业送达公告当事人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementPartyGateway dataCompanyDeliveryAnnouncementPartyGateway;

	/**
	 * 执行 企业送达公告当事人 更新指令
	 * @param dataCompanyDeliveryAnnouncementPartyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> execute(@Valid DataCompanyDeliveryAnnouncementPartyUpdateCommand dataCompanyDeliveryAnnouncementPartyUpdateCommand) {
		DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty = createByDataCompanyDeliveryAnnouncementPartyUpdateCommand(dataCompanyDeliveryAnnouncementPartyUpdateCommand);
		dataCompanyDeliveryAnnouncementParty.initForUpdate();
		dataCompanyDeliveryAnnouncementParty.setUpdateControl(dataCompanyDeliveryAnnouncementPartyUpdateCommand);
		boolean save = dataCompanyDeliveryAnnouncementPartyGateway.save(dataCompanyDeliveryAnnouncementParty);
		if (save) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.toDataCompanyDeliveryAnnouncementPartyVO(dataCompanyDeliveryAnnouncementParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业送达公告当事人更新指令创建企业送达公告当事人模型
	 * @param dataCompanyDeliveryAnnouncementPartyUpdateCommand
	 * @return
	 */
	private DataCompanyDeliveryAnnouncementParty createByDataCompanyDeliveryAnnouncementPartyUpdateCommand(DataCompanyDeliveryAnnouncementPartyUpdateCommand dataCompanyDeliveryAnnouncementPartyUpdateCommand){
		DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty = DataCompanyDeliveryAnnouncementParty.create();
		DataCompanyDeliveryAnnouncementPartyUpdateCommandToDataCompanyDeliveryAnnouncementPartyMapping.instance.fillDataCompanyDeliveryAnnouncementPartyByDataCompanyDeliveryAnnouncementPartyUpdateCommand(dataCompanyDeliveryAnnouncementParty, dataCompanyDeliveryAnnouncementPartyUpdateCommand);
		return dataCompanyDeliveryAnnouncementParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyDeliveryAnnouncementPartyUpdateCommandToDataCompanyDeliveryAnnouncementPartyMapping{
		DataCompanyDeliveryAnnouncementPartyUpdateCommandToDataCompanyDeliveryAnnouncementPartyMapping instance = Mappers.getMapper(DataCompanyDeliveryAnnouncementPartyUpdateCommandToDataCompanyDeliveryAnnouncementPartyMapping.class );

		default DataCompanyDeliveryAnnouncementPartyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyDeliveryAnnouncementPartyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyDeliveryAnnouncementParty
		 * @param dataCompanyDeliveryAnnouncementPartyUpdateCommand
		 */
		void fillDataCompanyDeliveryAnnouncementPartyByDataCompanyDeliveryAnnouncementPartyUpdateCommand(@MappingTarget DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty, DataCompanyDeliveryAnnouncementPartyUpdateCommand dataCompanyDeliveryAnnouncementPartyUpdateCommand);
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
