package com.particle.openplatform.infrastructure.app.gateway.impl;

import com.particle.openplatform.domain.app.OpenplatformAppOpenapi;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapiId;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppOpenapiGateway;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppOpenapiService;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.infrastructure.app.structmapping.OpenplatformAppOpenapiInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台应用与开放接口配置 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Component
public class OpenplatformAppOpenapiGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformAppOpenapiId,OpenplatformAppOpenapi> implements OpenplatformAppOpenapiGateway {

	private IOpenplatformAppOpenapiService iOpenplatformAppOpenapiService;

	@Override
	public OpenplatformAppOpenapi getById(OpenplatformAppOpenapiId openplatformAppOpenapiId) {
		OpenplatformAppOpenapiDO byId = iOpenplatformAppOpenapiService.getById(openplatformAppOpenapiId.getId());
		OpenplatformAppOpenapi openplatformAppOpenapi = DomainFactory.create(OpenplatformAppOpenapi.class);
		openplatformAppOpenapi = OpenplatformAppOpenapiInfrastructureStructMapping.instance. openplatformAppOpenapiDOToOpenplatformAppOpenapi(openplatformAppOpenapi,byId);
		return openplatformAppOpenapi;
	}

	@Override
	public boolean doSave(OpenplatformAppOpenapi openplatformAppOpenapi) {
		OpenplatformAppOpenapiDO openplatformAppOpenapiDO = OpenplatformAppOpenapiInfrastructureStructMapping.instance.openplatformAppOpenapiToOpenplatformAppOpenapiDO(openplatformAppOpenapi);
		if (openplatformAppOpenapiDO.getId() == null) {
			openplatformAppOpenapiDO.setAddControl(openplatformAppOpenapi.getAddControl());
			OpenplatformAppOpenapiDO add = iOpenplatformAppOpenapiService.add(openplatformAppOpenapiDO);
			openplatformAppOpenapi.setId(OpenplatformAppOpenapiId.of(add.getId()));
			return add != null;
		}
		openplatformAppOpenapiDO.setUpdateControl(openplatformAppOpenapi.getUpdateControl());
		OpenplatformAppOpenapiDO update = iOpenplatformAppOpenapiService.update(openplatformAppOpenapiDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformAppOpenapiId openplatformAppOpenapiId) {
		return iOpenplatformAppOpenapiService.deleteById(openplatformAppOpenapiId.getId());
	}


	@Autowired
	public void setIOpenplatformAppOpenapiService(IOpenplatformAppOpenapiService iOpenplatformAppOpenapiService) {
		this.iOpenplatformAppOpenapiService = iOpenplatformAppOpenapiService;
	}
}
