package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprGeograApproveAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncement;
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
 * 企业知识产权地理标识核准公告 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Component
@Validated
public class DataCompanyIprGeograApproveAnnouncementCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprGeograApproveAnnouncementGateway dataCompanyIprGeograApproveAnnouncementGateway;

	/**
	 * 执行企业知识产权地理标识核准公告添加指令
	 * @param dataCompanyIprGeograApproveAnnouncementCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> execute(@Valid DataCompanyIprGeograApproveAnnouncementCreateCommand dataCompanyIprGeograApproveAnnouncementCreateCommand) {
		DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement = createByDataCompanyIprGeograApproveAnnouncementCreateCommand(dataCompanyIprGeograApproveAnnouncementCreateCommand);
		dataCompanyIprGeograApproveAnnouncement.initForAdd();
		dataCompanyIprGeograApproveAnnouncement.setAddControl(dataCompanyIprGeograApproveAnnouncementCreateCommand);
		boolean save = dataCompanyIprGeograApproveAnnouncementGateway.save(dataCompanyIprGeograApproveAnnouncement);
		if (save) {
			return SingleResponse.of(DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.toDataCompanyIprGeograApproveAnnouncementVO(dataCompanyIprGeograApproveAnnouncement));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权地理标识核准公告创建指令创建企业知识产权地理标识核准公告模型
	 * @param dataCompanyIprGeograApproveAnnouncementCreateCommand
	 * @return
	 */
	private DataCompanyIprGeograApproveAnnouncement createByDataCompanyIprGeograApproveAnnouncementCreateCommand(DataCompanyIprGeograApproveAnnouncementCreateCommand dataCompanyIprGeograApproveAnnouncementCreateCommand){
		DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement = DataCompanyIprGeograApproveAnnouncement.create();
		DataCompanyIprGeograApproveAnnouncementCreateCommandToDataCompanyIprGeograApproveAnnouncementMapping.instance.fillDataCompanyIprGeograApproveAnnouncementByDataCompanyIprGeograApproveAnnouncementCreateCommand(dataCompanyIprGeograApproveAnnouncement, dataCompanyIprGeograApproveAnnouncementCreateCommand);
		return dataCompanyIprGeograApproveAnnouncement;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprGeograApproveAnnouncementCreateCommandToDataCompanyIprGeograApproveAnnouncementMapping{
		DataCompanyIprGeograApproveAnnouncementCreateCommandToDataCompanyIprGeograApproveAnnouncementMapping instance = Mappers.getMapper( DataCompanyIprGeograApproveAnnouncementCreateCommandToDataCompanyIprGeograApproveAnnouncementMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprGeograApproveAnnouncement
		 * @param dataCompanyIprGeograApproveAnnouncementCreateCommand
		 */
		void fillDataCompanyIprGeograApproveAnnouncementByDataCompanyIprGeograApproveAnnouncementCreateCommand(@MappingTarget DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement, DataCompanyIprGeograApproveAnnouncementCreateCommand dataCompanyIprGeograApproveAnnouncementCreateCommand);
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
