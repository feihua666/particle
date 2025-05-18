package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementId;
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
 * 企业开庭公告 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementGateway dataCompanyOpenCourtAnnouncementGateway;

	/**
	 * 执行 企业开庭公告 更新指令
	 * @param dataCompanyOpenCourtAnnouncementUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementVO> execute(@Valid DataCompanyOpenCourtAnnouncementUpdateCommand dataCompanyOpenCourtAnnouncementUpdateCommand) {
		DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement = createByDataCompanyOpenCourtAnnouncementUpdateCommand(dataCompanyOpenCourtAnnouncementUpdateCommand);
		dataCompanyOpenCourtAnnouncement.setUpdateControl(dataCompanyOpenCourtAnnouncementUpdateCommand);
		boolean save = dataCompanyOpenCourtAnnouncementGateway.save(dataCompanyOpenCourtAnnouncement);
		if (save) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementVO(dataCompanyOpenCourtAnnouncement));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业开庭公告更新指令创建企业开庭公告模型
	 * @param dataCompanyOpenCourtAnnouncementUpdateCommand
	 * @return
	 */
	private DataCompanyOpenCourtAnnouncement createByDataCompanyOpenCourtAnnouncementUpdateCommand(DataCompanyOpenCourtAnnouncementUpdateCommand dataCompanyOpenCourtAnnouncementUpdateCommand){
		DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement = DataCompanyOpenCourtAnnouncement.create();
		DataCompanyOpenCourtAnnouncementUpdateCommandToDataCompanyOpenCourtAnnouncementMapping.instance.fillDataCompanyOpenCourtAnnouncementByDataCompanyOpenCourtAnnouncementUpdateCommand(dataCompanyOpenCourtAnnouncement, dataCompanyOpenCourtAnnouncementUpdateCommand);
		return dataCompanyOpenCourtAnnouncement;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyOpenCourtAnnouncementUpdateCommandToDataCompanyOpenCourtAnnouncementMapping{
		DataCompanyOpenCourtAnnouncementUpdateCommandToDataCompanyOpenCourtAnnouncementMapping instance = Mappers.getMapper(DataCompanyOpenCourtAnnouncementUpdateCommandToDataCompanyOpenCourtAnnouncementMapping.class );

		default DataCompanyOpenCourtAnnouncementId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyOpenCourtAnnouncementId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyOpenCourtAnnouncement
		 * @param dataCompanyOpenCourtAnnouncementUpdateCommand
		 */
		void fillDataCompanyOpenCourtAnnouncementByDataCompanyOpenCourtAnnouncementUpdateCommand(@MappingTarget DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement, DataCompanyOpenCourtAnnouncementUpdateCommand dataCompanyOpenCourtAnnouncementUpdateCommand);
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
