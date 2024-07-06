package com.particle.openplatform.infrastructure.provider.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.provider.OpenplatformProvider;
import com.particle.openplatform.domain.provider.OpenplatformProviderId;
import com.particle.openplatform.domain.provider.gateway.OpenplatformProviderGateway;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderService;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.openplatform.infrastructure.provider.structmapping.OpenplatformProviderInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台开放接口供应商 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Component
public class OpenplatformProviderGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformProviderId,OpenplatformProvider> implements OpenplatformProviderGateway {

	private IOpenplatformProviderService iOpenplatformProviderService;

	@Override
	public OpenplatformProvider getById(OpenplatformProviderId openplatformProviderId) {
		OpenplatformProviderDO byId = iOpenplatformProviderService.getById(openplatformProviderId.getId());
		OpenplatformProvider openplatformProvider = DomainFactory.create(OpenplatformProvider.class);
		openplatformProvider = OpenplatformProviderInfrastructureStructMapping.instance. openplatformProviderDOToOpenplatformProvider(openplatformProvider,byId);
		return openplatformProvider;
	}

	@Override
	public boolean doSave(OpenplatformProvider openplatformProvider) {
		OpenplatformProviderDO openplatformProviderDO = OpenplatformProviderInfrastructureStructMapping.instance.openplatformProviderToOpenplatformProviderDO(openplatformProvider);
		if (openplatformProviderDO.getId() == null) {
			openplatformProviderDO.setAddControl(openplatformProvider.getAddControl());
			OpenplatformProviderDO add = iOpenplatformProviderService.add(openplatformProviderDO);
			openplatformProvider.setId(OpenplatformProviderId.of(add.getId()));
			return add != null;
		}
		openplatformProviderDO.setUpdateControl(openplatformProvider.getUpdateControl());
		OpenplatformProviderDO update = iOpenplatformProviderService.update(openplatformProviderDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformProviderId openplatformProviderId) {
		return iOpenplatformProviderService.deleteById(openplatformProviderId.getId());
	}

	@Override
	public boolean delete(OpenplatformProviderId id, IdCommand idCommand) {
		return iOpenplatformProviderService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformProviderService(IOpenplatformProviderService iOpenplatformProviderService) {
		this.iOpenplatformProviderService = iOpenplatformProviderService;
	}
}
