package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsume;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeId;
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
 * 企业限制高消费 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumeGateway dataCompanyRestrictHighConsumeGateway;

	/**
	 * 执行 企业限制高消费 更新指令
	 * @param dataCompanyRestrictHighConsumeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumeVO> execute(@Valid DataCompanyRestrictHighConsumeUpdateCommand dataCompanyRestrictHighConsumeUpdateCommand) {
		DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume = createByDataCompanyRestrictHighConsumeUpdateCommand(dataCompanyRestrictHighConsumeUpdateCommand);
		dataCompanyRestrictHighConsume.initForUpdate();
		dataCompanyRestrictHighConsume.setUpdateControl(dataCompanyRestrictHighConsumeUpdateCommand);
		boolean save = dataCompanyRestrictHighConsumeGateway.save(dataCompanyRestrictHighConsume);
		if (save) {
			return SingleResponse.of(DataCompanyRestrictHighConsumeAppStructMapping.instance.toDataCompanyRestrictHighConsumeVO(dataCompanyRestrictHighConsume));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业限制高消费更新指令创建企业限制高消费模型
	 * @param dataCompanyRestrictHighConsumeUpdateCommand
	 * @return
	 */
	private DataCompanyRestrictHighConsume createByDataCompanyRestrictHighConsumeUpdateCommand(DataCompanyRestrictHighConsumeUpdateCommand dataCompanyRestrictHighConsumeUpdateCommand){
		DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume = DataCompanyRestrictHighConsume.create();
		DataCompanyRestrictHighConsumeUpdateCommandToDataCompanyRestrictHighConsumeMapping.instance.fillDataCompanyRestrictHighConsumeByDataCompanyRestrictHighConsumeUpdateCommand(dataCompanyRestrictHighConsume, dataCompanyRestrictHighConsumeUpdateCommand);
		return dataCompanyRestrictHighConsume;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyRestrictHighConsumeUpdateCommandToDataCompanyRestrictHighConsumeMapping{
		DataCompanyRestrictHighConsumeUpdateCommandToDataCompanyRestrictHighConsumeMapping instance = Mappers.getMapper(DataCompanyRestrictHighConsumeUpdateCommandToDataCompanyRestrictHighConsumeMapping.class );

		default DataCompanyRestrictHighConsumeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyRestrictHighConsumeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyRestrictHighConsume
		 * @param dataCompanyRestrictHighConsumeUpdateCommand
		 */
		void fillDataCompanyRestrictHighConsumeByDataCompanyRestrictHighConsumeUpdateCommand(@MappingTarget DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume, DataCompanyRestrictHighConsumeUpdateCommand dataCompanyRestrictHighConsumeUpdateCommand);
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
