package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncement;
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
 * 企业法院公告 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementGateway dataCompanyCourtAnnouncementGateway;

	/**
	 * 执行企业法院公告添加指令
	 * @param dataCompanyCourtAnnouncementCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementVO> execute(@Valid DataCompanyCourtAnnouncementCreateCommand dataCompanyCourtAnnouncementCreateCommand) {
		DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement = createByDataCompanyCourtAnnouncementCreateCommand(dataCompanyCourtAnnouncementCreateCommand);
		dataCompanyCourtAnnouncement.initForAdd();
		dataCompanyCourtAnnouncement.setAddControl(dataCompanyCourtAnnouncementCreateCommand);
		boolean save = dataCompanyCourtAnnouncementGateway.save(dataCompanyCourtAnnouncement);
		if (save) {
			return SingleResponse.of(DataCompanyCourtAnnouncementAppStructMapping.instance.toDataCompanyCourtAnnouncementVO(dataCompanyCourtAnnouncement));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业法院公告创建指令创建企业法院公告模型
	 * @param dataCompanyCourtAnnouncementCreateCommand
	 * @return
	 */
	private DataCompanyCourtAnnouncement createByDataCompanyCourtAnnouncementCreateCommand(DataCompanyCourtAnnouncementCreateCommand dataCompanyCourtAnnouncementCreateCommand){
		DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement = DataCompanyCourtAnnouncement.create();
		DataCompanyCourtAnnouncementCreateCommandToDataCompanyCourtAnnouncementMapping.instance.fillDataCompanyCourtAnnouncementByDataCompanyCourtAnnouncementCreateCommand(dataCompanyCourtAnnouncement, dataCompanyCourtAnnouncementCreateCommand);
		return dataCompanyCourtAnnouncement;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyCourtAnnouncementCreateCommandToDataCompanyCourtAnnouncementMapping{
		DataCompanyCourtAnnouncementCreateCommandToDataCompanyCourtAnnouncementMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementCreateCommandToDataCompanyCourtAnnouncementMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCourtAnnouncement
		 * @param dataCompanyCourtAnnouncementCreateCommand
		 */
		void fillDataCompanyCourtAnnouncementByDataCompanyCourtAnnouncementCreateCommand(@MappingTarget DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement, DataCompanyCourtAnnouncementCreateCommand dataCompanyCourtAnnouncementCreateCommand);
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
