package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContentId;
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
 * 企业开庭公告内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementContentGateway dataCompanyOpenCourtAnnouncementContentGateway;

	/**
	 * 执行 企业开庭公告内容 更新指令
	 * @param dataCompanyOpenCourtAnnouncementContentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> execute(@Valid DataCompanyOpenCourtAnnouncementContentUpdateCommand dataCompanyOpenCourtAnnouncementContentUpdateCommand) {
		DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent = createByDataCompanyOpenCourtAnnouncementContentUpdateCommand(dataCompanyOpenCourtAnnouncementContentUpdateCommand);
		dataCompanyOpenCourtAnnouncementContent.initForUpdate();
		dataCompanyOpenCourtAnnouncementContent.setUpdateControl(dataCompanyOpenCourtAnnouncementContentUpdateCommand);
		boolean save = dataCompanyOpenCourtAnnouncementContentGateway.save(dataCompanyOpenCourtAnnouncementContent);
		if (save) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementContentVO(dataCompanyOpenCourtAnnouncementContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业开庭公告内容更新指令创建企业开庭公告内容模型
	 * @param dataCompanyOpenCourtAnnouncementContentUpdateCommand
	 * @return
	 */
	private DataCompanyOpenCourtAnnouncementContent createByDataCompanyOpenCourtAnnouncementContentUpdateCommand(DataCompanyOpenCourtAnnouncementContentUpdateCommand dataCompanyOpenCourtAnnouncementContentUpdateCommand){
		DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent = DataCompanyOpenCourtAnnouncementContent.create();
		DataCompanyOpenCourtAnnouncementContentUpdateCommandToDataCompanyOpenCourtAnnouncementContentMapping.instance.fillDataCompanyOpenCourtAnnouncementContentByDataCompanyOpenCourtAnnouncementContentUpdateCommand(dataCompanyOpenCourtAnnouncementContent, dataCompanyOpenCourtAnnouncementContentUpdateCommand);
		return dataCompanyOpenCourtAnnouncementContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyOpenCourtAnnouncementContentUpdateCommandToDataCompanyOpenCourtAnnouncementContentMapping{
		DataCompanyOpenCourtAnnouncementContentUpdateCommandToDataCompanyOpenCourtAnnouncementContentMapping instance = Mappers.getMapper(DataCompanyOpenCourtAnnouncementContentUpdateCommandToDataCompanyOpenCourtAnnouncementContentMapping.class );

		default DataCompanyOpenCourtAnnouncementContentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyOpenCourtAnnouncementContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyOpenCourtAnnouncementContent
		 * @param dataCompanyOpenCourtAnnouncementContentUpdateCommand
		 */
		void fillDataCompanyOpenCourtAnnouncementContentByDataCompanyOpenCourtAnnouncementContentUpdateCommand(@MappingTarget DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent, DataCompanyOpenCourtAnnouncementContentUpdateCommand dataCompanyOpenCourtAnnouncementContentUpdateCommand);
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
