package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncement;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementGateway;
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
 * 企业送达公告 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementGateway dataCompanyDeliveryAnnouncementGateway;

	/**
	 * 执行企业送达公告添加指令
	 * @param dataCompanyDeliveryAnnouncementCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementVO> execute(@Valid DataCompanyDeliveryAnnouncementCreateCommand dataCompanyDeliveryAnnouncementCreateCommand) {
		DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement = createByDataCompanyDeliveryAnnouncementCreateCommand(dataCompanyDeliveryAnnouncementCreateCommand);
		dataCompanyDeliveryAnnouncement.initForAdd();
		dataCompanyDeliveryAnnouncement.setAddControl(dataCompanyDeliveryAnnouncementCreateCommand);
		boolean save = dataCompanyDeliveryAnnouncementGateway.save(dataCompanyDeliveryAnnouncement);
		if (save) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementAppStructMapping.instance.toDataCompanyDeliveryAnnouncementVO(dataCompanyDeliveryAnnouncement));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业送达公告创建指令创建企业送达公告模型
	 * @param dataCompanyDeliveryAnnouncementCreateCommand
	 * @return
	 */
	private DataCompanyDeliveryAnnouncement createByDataCompanyDeliveryAnnouncementCreateCommand(DataCompanyDeliveryAnnouncementCreateCommand dataCompanyDeliveryAnnouncementCreateCommand){
		DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement = DataCompanyDeliveryAnnouncement.create();
		DataCompanyDeliveryAnnouncementCreateCommandToDataCompanyDeliveryAnnouncementMapping.instance.fillDataCompanyDeliveryAnnouncementByDataCompanyDeliveryAnnouncementCreateCommand(dataCompanyDeliveryAnnouncement, dataCompanyDeliveryAnnouncementCreateCommand);
		return dataCompanyDeliveryAnnouncement;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyDeliveryAnnouncementCreateCommandToDataCompanyDeliveryAnnouncementMapping{
		DataCompanyDeliveryAnnouncementCreateCommandToDataCompanyDeliveryAnnouncementMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementCreateCommandToDataCompanyDeliveryAnnouncementMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyDeliveryAnnouncement
		 * @param dataCompanyDeliveryAnnouncementCreateCommand
		 */
		void fillDataCompanyDeliveryAnnouncementByDataCompanyDeliveryAnnouncementCreateCommand(@MappingTarget DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement, DataCompanyDeliveryAnnouncementCreateCommand dataCompanyDeliveryAnnouncementCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementGateway(DataCompanyDeliveryAnnouncementGateway dataCompanyDeliveryAnnouncementGateway) {
		this.dataCompanyDeliveryAnnouncementGateway = dataCompanyDeliveryAnnouncementGateway;
	}
}
