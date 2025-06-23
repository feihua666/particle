package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprGeograApproveAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncement;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyIprGeograApproveAnnouncementGateway;
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
 * 企业知识产权地理标识核准公告 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprGeograApproveAnnouncementGateway dataCompanyIprGeograApproveAnnouncementGateway;

	/**
	 * 执行 企业知识产权地理标识核准公告 更新指令
	 * @param dataCompanyIprGeograApproveAnnouncementUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> execute(@Valid DataCompanyIprGeograApproveAnnouncementUpdateCommand dataCompanyIprGeograApproveAnnouncementUpdateCommand) {
		DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement = createByDataCompanyIprGeograApproveAnnouncementUpdateCommand(dataCompanyIprGeograApproveAnnouncementUpdateCommand);
		dataCompanyIprGeograApproveAnnouncement.initForUpdate();
		dataCompanyIprGeograApproveAnnouncement.setUpdateControl(dataCompanyIprGeograApproveAnnouncementUpdateCommand);
		boolean save = dataCompanyIprGeograApproveAnnouncementGateway.save(dataCompanyIprGeograApproveAnnouncement);
		if (save) {
			return SingleResponse.of(DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.toDataCompanyIprGeograApproveAnnouncementVO(dataCompanyIprGeograApproveAnnouncement));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权地理标识核准公告更新指令创建企业知识产权地理标识核准公告模型
	 * @param dataCompanyIprGeograApproveAnnouncementUpdateCommand
	 * @return
	 */
	private DataCompanyIprGeograApproveAnnouncement createByDataCompanyIprGeograApproveAnnouncementUpdateCommand(DataCompanyIprGeograApproveAnnouncementUpdateCommand dataCompanyIprGeograApproveAnnouncementUpdateCommand){
		DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement = DataCompanyIprGeograApproveAnnouncement.create();
		DataCompanyIprGeograApproveAnnouncementUpdateCommandToDataCompanyIprGeograApproveAnnouncementMapping.instance.fillDataCompanyIprGeograApproveAnnouncementByDataCompanyIprGeograApproveAnnouncementUpdateCommand(dataCompanyIprGeograApproveAnnouncement, dataCompanyIprGeograApproveAnnouncementUpdateCommand);
		return dataCompanyIprGeograApproveAnnouncement;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprGeograApproveAnnouncementUpdateCommandToDataCompanyIprGeograApproveAnnouncementMapping{
		DataCompanyIprGeograApproveAnnouncementUpdateCommandToDataCompanyIprGeograApproveAnnouncementMapping instance = Mappers.getMapper(DataCompanyIprGeograApproveAnnouncementUpdateCommandToDataCompanyIprGeograApproveAnnouncementMapping.class );

		default DataCompanyIprGeograApproveAnnouncementId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprGeograApproveAnnouncementId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprGeograApproveAnnouncement
		 * @param dataCompanyIprGeograApproveAnnouncementUpdateCommand
		 */
		void fillDataCompanyIprGeograApproveAnnouncementByDataCompanyIprGeograApproveAnnouncementUpdateCommand(@MappingTarget DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement, DataCompanyIprGeograApproveAnnouncementUpdateCommand dataCompanyIprGeograApproveAnnouncementUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprGeograApproveAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyIprGeograApproveAnnouncementGateway(DataCompanyIprGeograApproveAnnouncementGateway dataCompanyIprGeograApproveAnnouncementGateway) {
		this.dataCompanyIprGeograApproveAnnouncementGateway = dataCompanyIprGeograApproveAnnouncementGateway;
	}
}
