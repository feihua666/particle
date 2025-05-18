package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContentId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementContentGateway;
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
 * 企业法院公告内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementContentGateway dataCompanyCourtAnnouncementContentGateway;

	/**
	 * 执行 企业法院公告内容 更新指令
	 * @param dataCompanyCourtAnnouncementContentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementContentVO> execute(@Valid DataCompanyCourtAnnouncementContentUpdateCommand dataCompanyCourtAnnouncementContentUpdateCommand) {
		DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent = createByDataCompanyCourtAnnouncementContentUpdateCommand(dataCompanyCourtAnnouncementContentUpdateCommand);
		dataCompanyCourtAnnouncementContent.initForUpdate();
		dataCompanyCourtAnnouncementContent.setUpdateControl(dataCompanyCourtAnnouncementContentUpdateCommand);
		boolean save = dataCompanyCourtAnnouncementContentGateway.save(dataCompanyCourtAnnouncementContent);
		if (save) {
			return SingleResponse.of(DataCompanyCourtAnnouncementContentAppStructMapping.instance.toDataCompanyCourtAnnouncementContentVO(dataCompanyCourtAnnouncementContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业法院公告内容更新指令创建企业法院公告内容模型
	 * @param dataCompanyCourtAnnouncementContentUpdateCommand
	 * @return
	 */
	private DataCompanyCourtAnnouncementContent createByDataCompanyCourtAnnouncementContentUpdateCommand(DataCompanyCourtAnnouncementContentUpdateCommand dataCompanyCourtAnnouncementContentUpdateCommand){
		DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent = DataCompanyCourtAnnouncementContent.create();
		DataCompanyCourtAnnouncementContentUpdateCommandToDataCompanyCourtAnnouncementContentMapping.instance.fillDataCompanyCourtAnnouncementContentByDataCompanyCourtAnnouncementContentUpdateCommand(dataCompanyCourtAnnouncementContent, dataCompanyCourtAnnouncementContentUpdateCommand);
		return dataCompanyCourtAnnouncementContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyCourtAnnouncementContentUpdateCommandToDataCompanyCourtAnnouncementContentMapping{
		DataCompanyCourtAnnouncementContentUpdateCommandToDataCompanyCourtAnnouncementContentMapping instance = Mappers.getMapper(DataCompanyCourtAnnouncementContentUpdateCommandToDataCompanyCourtAnnouncementContentMapping.class );

		default DataCompanyCourtAnnouncementContentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyCourtAnnouncementContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCourtAnnouncementContent
		 * @param dataCompanyCourtAnnouncementContentUpdateCommand
		 */
		void fillDataCompanyCourtAnnouncementContentByDataCompanyCourtAnnouncementContentUpdateCommand(@MappingTarget DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent, DataCompanyCourtAnnouncementContentUpdateCommand dataCompanyCourtAnnouncementContentUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementContentGateway(DataCompanyCourtAnnouncementContentGateway dataCompanyCourtAnnouncementContentGateway) {
		this.dataCompanyCourtAnnouncementContentGateway = dataCompanyCourtAnnouncementContentGateway;
	}
}
