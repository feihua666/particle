package com.particle.config.app.system.executor;

import com.particle.config.app.system.structmapping.SystemConfigAppStructMapping;
import com.particle.config.client.system.dto.command.SystemConfigCreateCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.config.domain.system.SystemConfig;
import com.particle.config.domain.system.gateway.SystemConfigGateway;
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
 * 系统参数配置 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Component
@Validated
public class SystemConfigCreateCommandExecutor  extends AbstractBaseExecutor {

	private SystemConfigGateway systemConfigGateway;

	/**
	 * 执行系统参数配置添加指令
	 * @param systemConfigCreateCommand
	 * @return
	 */
	public SingleResponse<SystemConfigVO> execute(@Valid SystemConfigCreateCommand systemConfigCreateCommand) {
		SystemConfig systemConfig = createBySystemConfigCreateCommand(systemConfigCreateCommand);
		systemConfig.setAddControl(systemConfigCreateCommand);
		boolean save = systemConfigGateway.save(systemConfig);
		if (save) {
			return SingleResponse.of(SystemConfigAppStructMapping.instance.toSystemConfigVO(systemConfig));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据系统参数配置创建指令创建系统参数配置模型
	 * @param systemConfigCreateCommand
	 * @return
	 */
	private SystemConfig createBySystemConfigCreateCommand(SystemConfigCreateCommand systemConfigCreateCommand){
		SystemConfig systemConfig = SystemConfig.create();
		SystemConfigCreateCommandToSystemConfigMapping.instance.fillSystemConfigBySystemConfigCreateCommand(systemConfig, systemConfigCreateCommand);
		return systemConfig;
	}

	@Mapper
	interface  SystemConfigCreateCommandToSystemConfigMapping{
		SystemConfigCreateCommandToSystemConfigMapping instance = Mappers.getMapper( SystemConfigCreateCommandToSystemConfigMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param systemConfig
		 * @param systemConfigCreateCommand
		 */
		void fillSystemConfigBySystemConfigCreateCommand(@MappingTarget SystemConfig systemConfig, SystemConfigCreateCommand systemConfigCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param systemConfigGateway
	 */
	@Autowired
	public void setSystemConfigGateway(SystemConfigGateway systemConfigGateway) {
		this.systemConfigGateway = systemConfigGateway;
	}
}
