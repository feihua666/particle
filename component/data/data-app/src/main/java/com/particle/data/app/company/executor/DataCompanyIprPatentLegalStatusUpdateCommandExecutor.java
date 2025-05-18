package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentLegalStatusAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatus;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatusId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentLegalStatusGateway;
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
 * 企业知识产权专利法律状态 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentLegalStatusUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentLegalStatusGateway dataCompanyIprPatentLegalStatusGateway;

	/**
	 * 执行 企业知识产权专利法律状态 更新指令
	 * @param dataCompanyIprPatentLegalStatusUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLegalStatusVO> execute(@Valid DataCompanyIprPatentLegalStatusUpdateCommand dataCompanyIprPatentLegalStatusUpdateCommand) {
		DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus = createByDataCompanyIprPatentLegalStatusUpdateCommand(dataCompanyIprPatentLegalStatusUpdateCommand);
		dataCompanyIprPatentLegalStatus.initForUpdate();
		dataCompanyIprPatentLegalStatus.setUpdateControl(dataCompanyIprPatentLegalStatusUpdateCommand);
		boolean save = dataCompanyIprPatentLegalStatusGateway.save(dataCompanyIprPatentLegalStatus);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentLegalStatusAppStructMapping.instance.toDataCompanyIprPatentLegalStatusVO(dataCompanyIprPatentLegalStatus));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利法律状态更新指令创建企业知识产权专利法律状态模型
	 * @param dataCompanyIprPatentLegalStatusUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentLegalStatus createByDataCompanyIprPatentLegalStatusUpdateCommand(DataCompanyIprPatentLegalStatusUpdateCommand dataCompanyIprPatentLegalStatusUpdateCommand){
		DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus = DataCompanyIprPatentLegalStatus.create();
		DataCompanyIprPatentLegalStatusUpdateCommandToDataCompanyIprPatentLegalStatusMapping.instance.fillDataCompanyIprPatentLegalStatusByDataCompanyIprPatentLegalStatusUpdateCommand(dataCompanyIprPatentLegalStatus, dataCompanyIprPatentLegalStatusUpdateCommand);
		return dataCompanyIprPatentLegalStatus;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentLegalStatusUpdateCommandToDataCompanyIprPatentLegalStatusMapping{
		DataCompanyIprPatentLegalStatusUpdateCommandToDataCompanyIprPatentLegalStatusMapping instance = Mappers.getMapper(DataCompanyIprPatentLegalStatusUpdateCommandToDataCompanyIprPatentLegalStatusMapping.class );

		default DataCompanyIprPatentLegalStatusId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentLegalStatusId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentLegalStatus
		 * @param dataCompanyIprPatentLegalStatusUpdateCommand
		 */
		void fillDataCompanyIprPatentLegalStatusByDataCompanyIprPatentLegalStatusUpdateCommand(@MappingTarget DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus, DataCompanyIprPatentLegalStatusUpdateCommand dataCompanyIprPatentLegalStatusUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentLegalStatusGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentLegalStatusGateway(DataCompanyIprPatentLegalStatusGateway dataCompanyIprPatentLegalStatusGateway) {
		this.dataCompanyIprPatentLegalStatusGateway = dataCompanyIprPatentLegalStatusGateway;
	}
}
