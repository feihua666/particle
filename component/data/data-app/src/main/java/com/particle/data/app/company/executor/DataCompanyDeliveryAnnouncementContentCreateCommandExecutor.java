package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContent;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementContentGateway;
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
 * 企业送达公告内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementContentGateway dataCompanyDeliveryAnnouncementContentGateway;

	/**
	 * 执行企业送达公告内容添加指令
	 * @param dataCompanyDeliveryAnnouncementContentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> execute(@Valid DataCompanyDeliveryAnnouncementContentCreateCommand dataCompanyDeliveryAnnouncementContentCreateCommand) {
		DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent = createByDataCompanyDeliveryAnnouncementContentCreateCommand(dataCompanyDeliveryAnnouncementContentCreateCommand);
		dataCompanyDeliveryAnnouncementContent.setAddControl(dataCompanyDeliveryAnnouncementContentCreateCommand);
		boolean save = dataCompanyDeliveryAnnouncementContentGateway.save(dataCompanyDeliveryAnnouncementContent);
		if (save) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.toDataCompanyDeliveryAnnouncementContentVO(dataCompanyDeliveryAnnouncementContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业送达公告内容创建指令创建企业送达公告内容模型
	 * @param dataCompanyDeliveryAnnouncementContentCreateCommand
	 * @return
	 */
	private DataCompanyDeliveryAnnouncementContent createByDataCompanyDeliveryAnnouncementContentCreateCommand(DataCompanyDeliveryAnnouncementContentCreateCommand dataCompanyDeliveryAnnouncementContentCreateCommand){
		DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent = DataCompanyDeliveryAnnouncementContent.create();
		DataCompanyDeliveryAnnouncementContentCreateCommandToDataCompanyDeliveryAnnouncementContentMapping.instance.fillDataCompanyDeliveryAnnouncementContentByDataCompanyDeliveryAnnouncementContentCreateCommand(dataCompanyDeliveryAnnouncementContent, dataCompanyDeliveryAnnouncementContentCreateCommand);
		return dataCompanyDeliveryAnnouncementContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyDeliveryAnnouncementContentCreateCommandToDataCompanyDeliveryAnnouncementContentMapping{
		DataCompanyDeliveryAnnouncementContentCreateCommandToDataCompanyDeliveryAnnouncementContentMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementContentCreateCommandToDataCompanyDeliveryAnnouncementContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyDeliveryAnnouncementContent
		 * @param dataCompanyDeliveryAnnouncementContentCreateCommand
		 */
		void fillDataCompanyDeliveryAnnouncementContentByDataCompanyDeliveryAnnouncementContentCreateCommand(@MappingTarget DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent, DataCompanyDeliveryAnnouncementContentCreateCommand dataCompanyDeliveryAnnouncementContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementContentGateway(DataCompanyDeliveryAnnouncementContentGateway dataCompanyDeliveryAnnouncementContentGateway) {
		this.dataCompanyDeliveryAnnouncementContentGateway = dataCompanyDeliveryAnnouncementContentGateway;
	}
}
