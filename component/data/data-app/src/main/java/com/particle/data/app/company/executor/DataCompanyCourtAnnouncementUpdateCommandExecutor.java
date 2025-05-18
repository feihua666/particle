package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementGateway;
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
 * 企业法院公告 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementGateway dataCompanyCourtAnnouncementGateway;

	/**
	 * 执行 企业法院公告 更新指令
	 * @param dataCompanyCourtAnnouncementUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementVO> execute(@Valid DataCompanyCourtAnnouncementUpdateCommand dataCompanyCourtAnnouncementUpdateCommand) {
		DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement = createByDataCompanyCourtAnnouncementUpdateCommand(dataCompanyCourtAnnouncementUpdateCommand);
		dataCompanyCourtAnnouncement.initForUpdate();
		dataCompanyCourtAnnouncement.setUpdateControl(dataCompanyCourtAnnouncementUpdateCommand);
		boolean save = dataCompanyCourtAnnouncementGateway.save(dataCompanyCourtAnnouncement);
		if (save) {
			return SingleResponse.of(DataCompanyCourtAnnouncementAppStructMapping.instance.toDataCompanyCourtAnnouncementVO(dataCompanyCourtAnnouncement));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业法院公告更新指令创建企业法院公告模型
	 * @param dataCompanyCourtAnnouncementUpdateCommand
	 * @return
	 */
	private DataCompanyCourtAnnouncement createByDataCompanyCourtAnnouncementUpdateCommand(DataCompanyCourtAnnouncementUpdateCommand dataCompanyCourtAnnouncementUpdateCommand){
		DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement = DataCompanyCourtAnnouncement.create();
		DataCompanyCourtAnnouncementUpdateCommandToDataCompanyCourtAnnouncementMapping.instance.fillDataCompanyCourtAnnouncementByDataCompanyCourtAnnouncementUpdateCommand(dataCompanyCourtAnnouncement, dataCompanyCourtAnnouncementUpdateCommand);
		return dataCompanyCourtAnnouncement;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyCourtAnnouncementUpdateCommandToDataCompanyCourtAnnouncementMapping{
		DataCompanyCourtAnnouncementUpdateCommandToDataCompanyCourtAnnouncementMapping instance = Mappers.getMapper(DataCompanyCourtAnnouncementUpdateCommandToDataCompanyCourtAnnouncementMapping.class );

		default DataCompanyCourtAnnouncementId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyCourtAnnouncementId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCourtAnnouncement
		 * @param dataCompanyCourtAnnouncementUpdateCommand
		 */
		void fillDataCompanyCourtAnnouncementByDataCompanyCourtAnnouncementUpdateCommand(@MappingTarget DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement, DataCompanyCourtAnnouncementUpdateCommand dataCompanyCourtAnnouncementUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementGateway(DataCompanyCourtAnnouncementGateway dataCompanyCourtAnnouncementGateway) {
		this.dataCompanyCourtAnnouncementGateway = dataCompanyCourtAnnouncementGateway;
	}
}
