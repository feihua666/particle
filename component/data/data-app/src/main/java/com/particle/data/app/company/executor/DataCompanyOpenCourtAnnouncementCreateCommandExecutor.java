package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncement;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementGateway;
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
 * 企业开庭公告 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementGateway dataCompanyOpenCourtAnnouncementGateway;

	/**
	 * 执行企业开庭公告添加指令
	 * @param dataCompanyOpenCourtAnnouncementCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementVO> execute(@Valid DataCompanyOpenCourtAnnouncementCreateCommand dataCompanyOpenCourtAnnouncementCreateCommand) {
		DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement = createByDataCompanyOpenCourtAnnouncementCreateCommand(dataCompanyOpenCourtAnnouncementCreateCommand);
		dataCompanyOpenCourtAnnouncement.setAddControl(dataCompanyOpenCourtAnnouncementCreateCommand);
		boolean save = dataCompanyOpenCourtAnnouncementGateway.save(dataCompanyOpenCourtAnnouncement);
		if (save) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementVO(dataCompanyOpenCourtAnnouncement));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业开庭公告创建指令创建企业开庭公告模型
	 * @param dataCompanyOpenCourtAnnouncementCreateCommand
	 * @return
	 */
	private DataCompanyOpenCourtAnnouncement createByDataCompanyOpenCourtAnnouncementCreateCommand(DataCompanyOpenCourtAnnouncementCreateCommand dataCompanyOpenCourtAnnouncementCreateCommand){
		DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement = DataCompanyOpenCourtAnnouncement.create();
		DataCompanyOpenCourtAnnouncementCreateCommandToDataCompanyOpenCourtAnnouncementMapping.instance.fillDataCompanyOpenCourtAnnouncementByDataCompanyOpenCourtAnnouncementCreateCommand(dataCompanyOpenCourtAnnouncement, dataCompanyOpenCourtAnnouncementCreateCommand);
		return dataCompanyOpenCourtAnnouncement;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyOpenCourtAnnouncementCreateCommandToDataCompanyOpenCourtAnnouncementMapping{
		DataCompanyOpenCourtAnnouncementCreateCommandToDataCompanyOpenCourtAnnouncementMapping instance = Mappers.getMapper( DataCompanyOpenCourtAnnouncementCreateCommandToDataCompanyOpenCourtAnnouncementMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyOpenCourtAnnouncement
		 * @param dataCompanyOpenCourtAnnouncementCreateCommand
		 */
		void fillDataCompanyOpenCourtAnnouncementByDataCompanyOpenCourtAnnouncementCreateCommand(@MappingTarget DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement, DataCompanyOpenCourtAnnouncementCreateCommand dataCompanyOpenCourtAnnouncementCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementGateway(DataCompanyOpenCourtAnnouncementGateway dataCompanyOpenCourtAnnouncementGateway) {
		this.dataCompanyOpenCourtAnnouncementGateway = dataCompanyOpenCourtAnnouncementGateway;
	}
}
