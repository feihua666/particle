package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContent;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContentId;
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
 * 企业送达公告内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementContentGateway dataCompanyDeliveryAnnouncementContentGateway;

	/**
	 * 执行 企业送达公告内容 更新指令
	 * @param dataCompanyDeliveryAnnouncementContentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> execute(@Valid DataCompanyDeliveryAnnouncementContentUpdateCommand dataCompanyDeliveryAnnouncementContentUpdateCommand) {
		DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent = createByDataCompanyDeliveryAnnouncementContentUpdateCommand(dataCompanyDeliveryAnnouncementContentUpdateCommand);
		dataCompanyDeliveryAnnouncementContent.setUpdateControl(dataCompanyDeliveryAnnouncementContentUpdateCommand);
		boolean save = dataCompanyDeliveryAnnouncementContentGateway.save(dataCompanyDeliveryAnnouncementContent);
		if (save) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.toDataCompanyDeliveryAnnouncementContentVO(dataCompanyDeliveryAnnouncementContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业送达公告内容更新指令创建企业送达公告内容模型
	 * @param dataCompanyDeliveryAnnouncementContentUpdateCommand
	 * @return
	 */
	private DataCompanyDeliveryAnnouncementContent createByDataCompanyDeliveryAnnouncementContentUpdateCommand(DataCompanyDeliveryAnnouncementContentUpdateCommand dataCompanyDeliveryAnnouncementContentUpdateCommand){
		DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent = DataCompanyDeliveryAnnouncementContent.create();
		DataCompanyDeliveryAnnouncementContentUpdateCommandToDataCompanyDeliveryAnnouncementContentMapping.instance.fillDataCompanyDeliveryAnnouncementContentByDataCompanyDeliveryAnnouncementContentUpdateCommand(dataCompanyDeliveryAnnouncementContent, dataCompanyDeliveryAnnouncementContentUpdateCommand);
		return dataCompanyDeliveryAnnouncementContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyDeliveryAnnouncementContentUpdateCommandToDataCompanyDeliveryAnnouncementContentMapping{
		DataCompanyDeliveryAnnouncementContentUpdateCommandToDataCompanyDeliveryAnnouncementContentMapping instance = Mappers.getMapper(DataCompanyDeliveryAnnouncementContentUpdateCommandToDataCompanyDeliveryAnnouncementContentMapping.class );

		default DataCompanyDeliveryAnnouncementContentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyDeliveryAnnouncementContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyDeliveryAnnouncementContent
		 * @param dataCompanyDeliveryAnnouncementContentUpdateCommand
		 */
		void fillDataCompanyDeliveryAnnouncementContentByDataCompanyDeliveryAnnouncementContentUpdateCommand(@MappingTarget DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent, DataCompanyDeliveryAnnouncementContentUpdateCommand dataCompanyDeliveryAnnouncementContentUpdateCommand);
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
