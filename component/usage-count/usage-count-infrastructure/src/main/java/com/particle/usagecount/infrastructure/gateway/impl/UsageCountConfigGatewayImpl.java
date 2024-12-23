package com.particle.usagecount.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.usagecount.domain.UsageCountConfig;
import com.particle.usagecount.domain.UsageCountConfigId;
import com.particle.usagecount.domain.gateway.UsageCountConfigGateway;
import com.particle.usagecount.infrastructure.dos.UsageCountConfigDO;
import com.particle.usagecount.infrastructure.service.IUsageCountConfigService;
import com.particle.usagecount.infrastructure.structmapping.UsageCountConfigInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 使用次数配置 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Component
public class UsageCountConfigGatewayImpl extends AbstractBaseGatewayImpl<UsageCountConfigId,UsageCountConfig> implements UsageCountConfigGateway {

	private IUsageCountConfigService iUsageCountConfigService;

	@Override
	public UsageCountConfig getById(UsageCountConfigId usageCountConfigId) {
		UsageCountConfigDO byId = iUsageCountConfigService.getById(usageCountConfigId.getId());
		UsageCountConfig usageCountConfig = DomainFactory.create(UsageCountConfig.class);
		usageCountConfig = UsageCountConfigInfrastructureStructMapping.instance. usageCountConfigDOToUsageCountConfig(usageCountConfig,byId);
		return usageCountConfig;
	}

	@Override
	public boolean doSave(UsageCountConfig usageCountConfig) {
		UsageCountConfigDO usageCountConfigDO = UsageCountConfigInfrastructureStructMapping.instance.usageCountConfigToUsageCountConfigDO(usageCountConfig);
		if (usageCountConfigDO.getId() == null) {
			usageCountConfigDO.setAddControl(usageCountConfig.getAddControl());
			UsageCountConfigDO add = iUsageCountConfigService.add(usageCountConfigDO);
			usageCountConfig.setId(UsageCountConfigId.of(add.getId()));
			return add != null;
		}
		usageCountConfigDO.setUpdateControl(usageCountConfig.getUpdateControl());
		UsageCountConfigDO update = iUsageCountConfigService.update(usageCountConfigDO);
		return update != null;
	}

	@Override
	public boolean delete(UsageCountConfigId usageCountConfigId) {
		return iUsageCountConfigService.deleteById(usageCountConfigId.getId());
	}

	@Override
	public boolean delete(UsageCountConfigId id, IdCommand idCommand) {
		return iUsageCountConfigService.deleteById(idCommand);
	}

	@Autowired
	public void setIUsageCountConfigService(IUsageCountConfigService iUsageCountConfigService) {
		this.iUsageCountConfigService = iUsageCountConfigService;
	}
}
