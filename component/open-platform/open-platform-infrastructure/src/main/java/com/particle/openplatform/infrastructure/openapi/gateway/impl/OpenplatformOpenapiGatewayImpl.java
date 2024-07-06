package com.particle.openplatform.infrastructure.openapi.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapi;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.structmapping.OpenplatformOpenapiInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台开放接口 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Component
public class OpenplatformOpenapiGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiId,OpenplatformOpenapi> implements OpenplatformOpenapiGateway {

	private IOpenplatformOpenapiService iOpenplatformOpenapiService;

	@Override
	public OpenplatformOpenapi getById(OpenplatformOpenapiId openplatformOpenapiId) {
		OpenplatformOpenapiDO byId = iOpenplatformOpenapiService.getById(openplatformOpenapiId.getId());
		OpenplatformOpenapi openplatformOpenapi = DomainFactory.create(OpenplatformOpenapi.class);
		openplatformOpenapi = OpenplatformOpenapiInfrastructureStructMapping.instance. openplatformOpenapiDOToOpenplatformOpenapi(openplatformOpenapi,byId);
		return openplatformOpenapi;
	}

	@Override
	public boolean doSave(OpenplatformOpenapi openplatformOpenapi) {
		OpenplatformOpenapiDO openplatformOpenapiDO = OpenplatformOpenapiInfrastructureStructMapping.instance.openplatformOpenapiToOpenplatformOpenapiDO(openplatformOpenapi);
		if (openplatformOpenapiDO.getId() == null) {
			openplatformOpenapiDO.setAddControl(openplatformOpenapi.getAddControl());
			OpenplatformOpenapiDO add = iOpenplatformOpenapiService.add(openplatformOpenapiDO);
			openplatformOpenapi.setId(OpenplatformOpenapiId.of(add.getId()));
			return add != null;
		}
		openplatformOpenapiDO.setUpdateControl(openplatformOpenapi.getUpdateControl());
		OpenplatformOpenapiDO update = iOpenplatformOpenapiService.update(openplatformOpenapiDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformOpenapiId openplatformOpenapiId) {
		return iOpenplatformOpenapiService.deleteById(openplatformOpenapiId.getId());
	}

	@Override
	public boolean delete(OpenplatformOpenapiId id, IdCommand idCommand) {
		return iOpenplatformOpenapiService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformOpenapiService(IOpenplatformOpenapiService iOpenplatformOpenapiService) {
		this.iOpenplatformOpenapiService = iOpenplatformOpenapiService;
	}
}
