package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentLegalStatusAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatus;
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
 * 企业知识产权专利法律状态 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Component
@Validated
public class DataCompanyIprPatentLegalStatusCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentLegalStatusGateway dataCompanyIprPatentLegalStatusGateway;

	/**
	 * 执行企业知识产权专利法律状态添加指令
	 * @param dataCompanyIprPatentLegalStatusCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLegalStatusVO> execute(@Valid DataCompanyIprPatentLegalStatusCreateCommand dataCompanyIprPatentLegalStatusCreateCommand) {
		DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus = createByDataCompanyIprPatentLegalStatusCreateCommand(dataCompanyIprPatentLegalStatusCreateCommand);
		dataCompanyIprPatentLegalStatus.initForAdd();
		dataCompanyIprPatentLegalStatus.setAddControl(dataCompanyIprPatentLegalStatusCreateCommand);
		boolean save = dataCompanyIprPatentLegalStatusGateway.save(dataCompanyIprPatentLegalStatus);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentLegalStatusAppStructMapping.instance.toDataCompanyIprPatentLegalStatusVO(dataCompanyIprPatentLegalStatus));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利法律状态创建指令创建企业知识产权专利法律状态模型
	 * @param dataCompanyIprPatentLegalStatusCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentLegalStatus createByDataCompanyIprPatentLegalStatusCreateCommand(DataCompanyIprPatentLegalStatusCreateCommand dataCompanyIprPatentLegalStatusCreateCommand){
		DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus = DataCompanyIprPatentLegalStatus.create();
		DataCompanyIprPatentLegalStatusCreateCommandToDataCompanyIprPatentLegalStatusMapping.instance.fillDataCompanyIprPatentLegalStatusByDataCompanyIprPatentLegalStatusCreateCommand(dataCompanyIprPatentLegalStatus, dataCompanyIprPatentLegalStatusCreateCommand);
		return dataCompanyIprPatentLegalStatus;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentLegalStatusCreateCommandToDataCompanyIprPatentLegalStatusMapping{
		DataCompanyIprPatentLegalStatusCreateCommandToDataCompanyIprPatentLegalStatusMapping instance = Mappers.getMapper( DataCompanyIprPatentLegalStatusCreateCommandToDataCompanyIprPatentLegalStatusMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentLegalStatus
		 * @param dataCompanyIprPatentLegalStatusCreateCommand
		 */
		void fillDataCompanyIprPatentLegalStatusByDataCompanyIprPatentLegalStatusCreateCommand(@MappingTarget DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus, DataCompanyIprPatentLegalStatusCreateCommand dataCompanyIprPatentLegalStatusCreateCommand);
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
