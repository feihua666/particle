package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContent;
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
 * 企业法院公告内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementContentGateway dataCompanyCourtAnnouncementContentGateway;

	/**
	 * 执行企业法院公告内容添加指令
	 * @param dataCompanyCourtAnnouncementContentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementContentVO> execute(@Valid DataCompanyCourtAnnouncementContentCreateCommand dataCompanyCourtAnnouncementContentCreateCommand) {
		DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent = createByDataCompanyCourtAnnouncementContentCreateCommand(dataCompanyCourtAnnouncementContentCreateCommand);
		dataCompanyCourtAnnouncementContent.initForAdd();
		dataCompanyCourtAnnouncementContent.setAddControl(dataCompanyCourtAnnouncementContentCreateCommand);
		boolean save = dataCompanyCourtAnnouncementContentGateway.save(dataCompanyCourtAnnouncementContent);
		if (save) {
			return SingleResponse.of(DataCompanyCourtAnnouncementContentAppStructMapping.instance.toDataCompanyCourtAnnouncementContentVO(dataCompanyCourtAnnouncementContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业法院公告内容创建指令创建企业法院公告内容模型
	 * @param dataCompanyCourtAnnouncementContentCreateCommand
	 * @return
	 */
	private DataCompanyCourtAnnouncementContent createByDataCompanyCourtAnnouncementContentCreateCommand(DataCompanyCourtAnnouncementContentCreateCommand dataCompanyCourtAnnouncementContentCreateCommand){
		DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent = DataCompanyCourtAnnouncementContent.create();
		DataCompanyCourtAnnouncementContentCreateCommandToDataCompanyCourtAnnouncementContentMapping.instance.fillDataCompanyCourtAnnouncementContentByDataCompanyCourtAnnouncementContentCreateCommand(dataCompanyCourtAnnouncementContent, dataCompanyCourtAnnouncementContentCreateCommand);
		return dataCompanyCourtAnnouncementContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyCourtAnnouncementContentCreateCommandToDataCompanyCourtAnnouncementContentMapping{
		DataCompanyCourtAnnouncementContentCreateCommandToDataCompanyCourtAnnouncementContentMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementContentCreateCommandToDataCompanyCourtAnnouncementContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyCourtAnnouncementContent
		 * @param dataCompanyCourtAnnouncementContentCreateCommand
		 */
		void fillDataCompanyCourtAnnouncementContentByDataCompanyCourtAnnouncementContentCreateCommand(@MappingTarget DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent, DataCompanyCourtAnnouncementContentCreateCommand dataCompanyCourtAnnouncementContentCreateCommand);
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
