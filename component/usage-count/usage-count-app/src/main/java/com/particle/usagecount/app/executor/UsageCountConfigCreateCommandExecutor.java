package com.particle.usagecount.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.usagecount.app.structmapping.UsageCountConfigAppStructMapping;
import com.particle.usagecount.client.dto.command.UsageCountConfigCreateCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;
import com.particle.usagecount.domain.UsageCountConfig;
import com.particle.usagecount.domain.gateway.UsageCountConfigGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 使用次数配置 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Component
@Validated
public class UsageCountConfigCreateCommandExecutor  extends AbstractBaseExecutor {

	private UsageCountConfigGateway usageCountConfigGateway;

	/**
	 * 执行使用次数配置添加指令
	 * @param usageCountConfigCreateCommand
	 * @return
	 */
	public SingleResponse<UsageCountConfigVO> execute(@Valid UsageCountConfigCreateCommand usageCountConfigCreateCommand) {
		UsageCountConfig usageCountConfig = createByUsageCountConfigCreateCommand(usageCountConfigCreateCommand);
		usageCountConfig.setAddControl(usageCountConfigCreateCommand);
		boolean save = usageCountConfigGateway.save(usageCountConfig);
		if (save) {
			return SingleResponse.of(UsageCountConfigAppStructMapping.instance.toUsageCountConfigVO(usageCountConfig));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据使用次数配置创建指令创建使用次数配置模型
	 * @param usageCountConfigCreateCommand
	 * @return
	 */
	private UsageCountConfig createByUsageCountConfigCreateCommand(UsageCountConfigCreateCommand usageCountConfigCreateCommand){
		UsageCountConfig usageCountConfig = UsageCountConfig.create();
		UsageCountConfigCreateCommandToUsageCountConfigMapping.instance.fillUsageCountConfigByUsageCountConfigCreateCommand(usageCountConfig, usageCountConfigCreateCommand);
		return usageCountConfig;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  UsageCountConfigCreateCommandToUsageCountConfigMapping{
		UsageCountConfigCreateCommandToUsageCountConfigMapping instance = Mappers.getMapper( UsageCountConfigCreateCommandToUsageCountConfigMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param usageCountConfig
		 * @param usageCountConfigCreateCommand
		 */
		void fillUsageCountConfigByUsageCountConfigCreateCommand(@MappingTarget UsageCountConfig usageCountConfig, UsageCountConfigCreateCommand usageCountConfigCreateCommand);
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
