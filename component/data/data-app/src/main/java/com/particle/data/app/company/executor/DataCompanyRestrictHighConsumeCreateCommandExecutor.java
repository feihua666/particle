package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsume;
import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumeGateway;
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
 * 企业限制高消费 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumeGateway dataCompanyRestrictHighConsumeGateway;

	/**
	 * 执行企业限制高消费添加指令
	 * @param dataCompanyRestrictHighConsumeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumeVO> execute(@Valid DataCompanyRestrictHighConsumeCreateCommand dataCompanyRestrictHighConsumeCreateCommand) {
		DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume = createByDataCompanyRestrictHighConsumeCreateCommand(dataCompanyRestrictHighConsumeCreateCommand);
		dataCompanyRestrictHighConsume.initForAdd();
		dataCompanyRestrictHighConsume.setAddControl(dataCompanyRestrictHighConsumeCreateCommand);
		boolean save = dataCompanyRestrictHighConsumeGateway.save(dataCompanyRestrictHighConsume);
		if (save) {
			return SingleResponse.of(DataCompanyRestrictHighConsumeAppStructMapping.instance.toDataCompanyRestrictHighConsumeVO(dataCompanyRestrictHighConsume));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业限制高消费创建指令创建企业限制高消费模型
	 * @param dataCompanyRestrictHighConsumeCreateCommand
	 * @return
	 */
	private DataCompanyRestrictHighConsume createByDataCompanyRestrictHighConsumeCreateCommand(DataCompanyRestrictHighConsumeCreateCommand dataCompanyRestrictHighConsumeCreateCommand){
		DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume = DataCompanyRestrictHighConsume.create();
		DataCompanyRestrictHighConsumeCreateCommandToDataCompanyRestrictHighConsumeMapping.instance.fillDataCompanyRestrictHighConsumeByDataCompanyRestrictHighConsumeCreateCommand(dataCompanyRestrictHighConsume, dataCompanyRestrictHighConsumeCreateCommand);
		return dataCompanyRestrictHighConsume;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyRestrictHighConsumeCreateCommandToDataCompanyRestrictHighConsumeMapping{
		DataCompanyRestrictHighConsumeCreateCommandToDataCompanyRestrictHighConsumeMapping instance = Mappers.getMapper( DataCompanyRestrictHighConsumeCreateCommandToDataCompanyRestrictHighConsumeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyRestrictHighConsume
		 * @param dataCompanyRestrictHighConsumeCreateCommand
		 */
		void fillDataCompanyRestrictHighConsumeByDataCompanyRestrictHighConsumeCreateCommand(@MappingTarget DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume, DataCompanyRestrictHighConsumeCreateCommand dataCompanyRestrictHighConsumeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyRestrictHighConsumeGateway
	 */
	@Autowired
	public void setDataCompanyRestrictHighConsumeGateway(DataCompanyRestrictHighConsumeGateway dataCompanyRestrictHighConsumeGateway) {
		this.dataCompanyRestrictHighConsumeGateway = dataCompanyRestrictHighConsumeGateway;
	}
}
