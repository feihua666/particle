package com.particle.usagecount.app.executor;

import com.particle.usagecount.app.structmapping.UsageCountConfigAppStructMapping;
import com.particle.usagecount.client.dto.command.UsageCountConfigUpdateCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;
import com.particle.usagecount.domain.UsageCountConfig;
import com.particle.usagecount.domain.UsageCountConfigId;
import com.particle.usagecount.domain.gateway.UsageCountConfigGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 使用次数配置 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class UsageCountConfigUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UsageCountConfigGateway usageCountConfigGateway;

	/**
	 * 执行 使用次数配置 更新指令
	 * @param usageCountConfigUpdateCommand
	 * @return
	 */
	public SingleResponse<UsageCountConfigVO> execute(@Valid UsageCountConfigUpdateCommand usageCountConfigUpdateCommand) {
		UsageCountConfig usageCountConfig = createByUsageCountConfigUpdateCommand(usageCountConfigUpdateCommand);
		usageCountConfig.setUpdateControl(usageCountConfigUpdateCommand);
		boolean save = usageCountConfigGateway.save(usageCountConfig);
		if (save) {
			return SingleResponse.of(UsageCountConfigAppStructMapping.instance.toUsageCountConfigVO(usageCountConfig));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据使用次数配置更新指令创建使用次数配置模型
	 * @param usageCountConfigUpdateCommand
	 * @return
	 */
	private UsageCountConfig createByUsageCountConfigUpdateCommand(UsageCountConfigUpdateCommand usageCountConfigUpdateCommand){
		UsageCountConfig usageCountConfig = UsageCountConfig.create();
		UsageCountConfigUpdateCommandToUsageCountConfigMapping.instance.fillUsageCountConfigByUsageCountConfigUpdateCommand(usageCountConfig, usageCountConfigUpdateCommand);
		return usageCountConfig;
	}

	@Mapper
	interface UsageCountConfigUpdateCommandToUsageCountConfigMapping{
		UsageCountConfigUpdateCommandToUsageCountConfigMapping instance = Mappers.getMapper(UsageCountConfigUpdateCommandToUsageCountConfigMapping.class );

		default UsageCountConfigId map(Long id){
			if (id == null) {
				return null;
			}
			return UsageCountConfigId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param usageCountConfig
		 * @param usageCountConfigUpdateCommand
		 */
		void fillUsageCountConfigByUsageCountConfigUpdateCommand(@MappingTarget UsageCountConfig usageCountConfig, UsageCountConfigUpdateCommand usageCountConfigUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param usageCountConfigGateway
	 */
	@Autowired
	public void setUsageCountConfigGateway(UsageCountConfigGateway usageCountConfigGateway) {
		this.usageCountConfigGateway = usageCountConfigGateway;
	}
}
