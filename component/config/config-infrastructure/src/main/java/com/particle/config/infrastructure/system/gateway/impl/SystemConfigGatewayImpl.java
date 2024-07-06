package com.particle.config.infrastructure.system.gateway.impl;

import com.particle.config.domain.system.SystemConfig;
import com.particle.config.domain.system.SystemConfigId;
import com.particle.config.domain.system.gateway.SystemConfigGateway;
import com.particle.config.infrastructure.system.service.ISystemConfigService;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.infrastructure.system.structmapping.SystemConfigInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 系统参数配置 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Component
public class SystemConfigGatewayImpl extends AbstractBaseGatewayImpl<SystemConfigId,SystemConfig> implements SystemConfigGateway {

	private ISystemConfigService iSystemConfigService;

	@Override
	public SystemConfig getById(SystemConfigId systemConfigId) {
		SystemConfigDO byId = iSystemConfigService.getById(systemConfigId.getId());
		SystemConfig systemConfig = DomainFactory.create(SystemConfig.class);
		systemConfig = SystemConfigInfrastructureStructMapping.instance. systemConfigDOToSystemConfig(systemConfig,byId);
		return systemConfig;
	}

	@Override
	public boolean doSave(SystemConfig systemConfig) {
		SystemConfigDO systemConfigDO = SystemConfigInfrastructureStructMapping.instance.systemConfigToSystemConfigDO(systemConfig);
		if (systemConfigDO.getId() == null) {
			systemConfigDO.setAddControl(systemConfig.getAddControl());
			SystemConfigDO add = iSystemConfigService.add(systemConfigDO);
			systemConfig.setId(SystemConfigId.of(add.getId()));
			return add != null;
		}
		systemConfigDO.setUpdateControl(systemConfig.getUpdateControl());
		SystemConfigDO update = iSystemConfigService.update(systemConfigDO);
		return update != null;
	}

	@Override
	public boolean delete(SystemConfigId systemConfigId) {
		return iSystemConfigService.deleteById(systemConfigId.getId());
	}

	@Override
	public boolean delete(SystemConfigId id, IdCommand idCommand) {
		return iSystemConfigService.deleteById(idCommand);
	}

	@Autowired
	public void setISystemConfigService(ISystemConfigService iSystemConfigService) {
		this.iSystemConfigService = iSystemConfigService;
	}
}
