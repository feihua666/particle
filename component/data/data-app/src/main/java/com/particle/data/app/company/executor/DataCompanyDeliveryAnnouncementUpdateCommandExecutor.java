package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncement;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementId;
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
 * 企业送达公告 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementGateway dataCompanyDeliveryAnnouncementGateway;

	/**
	 * 执行 企业送达公告 更新指令
	 * @param dataCompanyDeliveryAnnouncementUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementVO> execute(@Valid DataCompanyDeliveryAnnouncementUpdateCommand dataCompanyDeliveryAnnouncementUpdateCommand) {
		DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement = createByDataCompanyDeliveryAnnouncementUpdateCommand(dataCompanyDeliveryAnnouncementUpdateCommand);
		dataCompanyDeliveryAnnouncement.initForUpdate();
		dataCompanyDeliveryAnnouncement.setUpdateControl(dataCompanyDeliveryAnnouncementUpdateCommand);
		boolean save = dataCompanyDeliveryAnnouncementGateway.save(dataCompanyDeliveryAnnouncement);
		if (save) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementAppStructMapping.instance.toDataCompanyDeliveryAnnouncementVO(dataCompanyDeliveryAnnouncement));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业送达公告更新指令创建企业送达公告模型
	 * @param dataCompanyDeliveryAnnouncementUpdateCommand
	 * @return
	 */
	private DataCompanyDeliveryAnnouncement createByDataCompanyDeliveryAnnouncementUpdateCommand(DataCompanyDeliveryAnnouncementUpdateCommand dataCompanyDeliveryAnnouncementUpdateCommand){
		DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement = DataCompanyDeliveryAnnouncement.create();
		DataCompanyDeliveryAnnouncementUpdateCommandToDataCompanyDeliveryAnnouncementMapping.instance.fillDataCompanyDeliveryAnnouncementByDataCompanyDeliveryAnnouncementUpdateCommand(dataCompanyDeliveryAnnouncement, dataCompanyDeliveryAnnouncementUpdateCommand);
		return dataCompanyDeliveryAnnouncement;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyDeliveryAnnouncementUpdateCommandToDataCompanyDeliveryAnnouncementMapping{
		DataCompanyDeliveryAnnouncementUpdateCommandToDataCompanyDeliveryAnnouncementMapping instance = Mappers.getMapper(DataCompanyDeliveryAnnouncementUpdateCommandToDataCompanyDeliveryAnnouncementMapping.class );

		default DataCompanyDeliveryAnnouncementId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyDeliveryAnnouncementId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyDeliveryAnnouncement
		 * @param dataCompanyDeliveryAnnouncementUpdateCommand
		 */
		void fillDataCompanyDeliveryAnnouncementByDataCompanyDeliveryAnnouncementUpdateCommand(@MappingTarget DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement, DataCompanyDeliveryAnnouncementUpdateCommand dataCompanyDeliveryAnnouncementUpdateCommand);
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
