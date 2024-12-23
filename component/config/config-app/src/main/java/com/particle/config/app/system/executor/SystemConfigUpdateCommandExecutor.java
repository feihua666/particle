package com.particle.config.app.system.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.config.app.system.structmapping.SystemConfigAppStructMapping;
import com.particle.config.client.system.dto.command.SystemConfigUpdateCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.config.domain.system.SystemConfig;
import com.particle.config.domain.system.SystemConfigId;
import com.particle.config.domain.system.gateway.SystemConfigGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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
 * 系统参数配置 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SystemConfigUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SystemConfigGateway systemConfigGateway;

	/**
	 * 执行 系统参数配置 更新指令
	 * @param systemConfigUpdateCommand
	 * @return
	 */
	public SingleResponse<SystemConfigVO> execute(@Valid SystemConfigUpdateCommand systemConfigUpdateCommand) {
		SystemConfig systemConfig = createBySystemConfigUpdateCommand(systemConfigUpdateCommand);
		systemConfig.setUpdateControl(systemConfigUpdateCommand);
		boolean save = systemConfigGateway.save(systemConfig);
		if (save) {
			return SingleResponse.of(SystemConfigAppStructMapping.instance.toSystemConfigVO(systemConfig));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据系统参数配置更新指令创建系统参数配置模型
	 * @param systemConfigUpdateCommand
	 * @return
	 */
	private SystemConfig createBySystemConfigUpdateCommand(SystemConfigUpdateCommand systemConfigUpdateCommand){
		SystemConfig systemConfig = SystemConfig.create();
		SystemConfigUpdateCommandToSystemConfigMapping.instance.fillSystemConfigBySystemConfigUpdateCommand(systemConfig, systemConfigUpdateCommand);
		return systemConfig;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface SystemConfigUpdateCommandToSystemConfigMapping{
		SystemConfigUpdateCommandToSystemConfigMapping instance = Mappers.getMapper(SystemConfigUpdateCommandToSystemConfigMapping.class );

		default SystemConfigId map(Long id){
			if (id == null) {
				return null;
			}
			return SystemConfigId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param systemConfig
		 * @param systemConfigUpdateCommand
		 */
		void fillSystemConfigBySystemConfigUpdateCommand(@MappingTarget SystemConfig systemConfig, SystemConfigUpdateCommand systemConfigUpdateCommand);
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
