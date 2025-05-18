package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContent;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementContentGateway;
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
 * 企业开庭公告内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementContentGateway dataCompanyOpenCourtAnnouncementContentGateway;

	/**
	 * 执行企业开庭公告内容添加指令
	 * @param dataCompanyOpenCourtAnnouncementContentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> execute(@Valid DataCompanyOpenCourtAnnouncementContentCreateCommand dataCompanyOpenCourtAnnouncementContentCreateCommand) {
		DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent = createByDataCompanyOpenCourtAnnouncementContentCreateCommand(dataCompanyOpenCourtAnnouncementContentCreateCommand);
		dataCompanyOpenCourtAnnouncementContent.initForAdd();
		dataCompanyOpenCourtAnnouncementContent.setAddControl(dataCompanyOpenCourtAnnouncementContentCreateCommand);
		boolean save = dataCompanyOpenCourtAnnouncementContentGateway.save(dataCompanyOpenCourtAnnouncementContent);
		if (save) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementContentVO(dataCompanyOpenCourtAnnouncementContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业开庭公告内容创建指令创建企业开庭公告内容模型
	 * @param dataCompanyOpenCourtAnnouncementContentCreateCommand
	 * @return
	 */
	private DataCompanyOpenCourtAnnouncementContent createByDataCompanyOpenCourtAnnouncementContentCreateCommand(DataCompanyOpenCourtAnnouncementContentCreateCommand dataCompanyOpenCourtAnnouncementContentCreateCommand){
		DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent = DataCompanyOpenCourtAnnouncementContent.create();
		DataCompanyOpenCourtAnnouncementContentCreateCommandToDataCompanyOpenCourtAnnouncementContentMapping.instance.fillDataCompanyOpenCourtAnnouncementContentByDataCompanyOpenCourtAnnouncementContentCreateCommand(dataCompanyOpenCourtAnnouncementContent, dataCompanyOpenCourtAnnouncementContentCreateCommand);
		return dataCompanyOpenCourtAnnouncementContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyOpenCourtAnnouncementContentCreateCommandToDataCompanyOpenCourtAnnouncementContentMapping{
		DataCompanyOpenCourtAnnouncementContentCreateCommandToDataCompanyOpenCourtAnnouncementContentMapping instance = Mappers.getMapper( DataCompanyOpenCourtAnnouncementContentCreateCommandToDataCompanyOpenCourtAnnouncementContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyOpenCourtAnnouncementContent
		 * @param dataCompanyOpenCourtAnnouncementContentCreateCommand
		 */
		void fillDataCompanyOpenCourtAnnouncementContentByDataCompanyOpenCourtAnnouncementContentCreateCommand(@MappingTarget DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent, DataCompanyOpenCourtAnnouncementContentCreateCommand dataCompanyOpenCourtAnnouncementContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementContentGateway(DataCompanyOpenCourtAnnouncementContentGateway dataCompanyOpenCourtAnnouncementContentGateway) {
		this.dataCompanyOpenCourtAnnouncementContentGateway = dataCompanyOpenCourtAnnouncementContentGateway;
	}
}
