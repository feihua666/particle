package com.particle.openplatform.infrastructure.app.gateway.impl;

import com.particle.openplatform.domain.app.OpenplatformApp;
import com.particle.openplatform.domain.app.OpenplatformAppId;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppGateway;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.structmapping.OpenplatformAppInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台应用 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Component
public class OpenplatformAppGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformAppId,OpenplatformApp> implements OpenplatformAppGateway {

	private IOpenplatformAppService iOpenplatformAppService;

	@Override
	public OpenplatformApp getById(OpenplatformAppId openplatformAppId) {
		OpenplatformAppDO byId = iOpenplatformAppService.getById(openplatformAppId.getId());
		OpenplatformApp openplatformApp = DomainFactory.create(OpenplatformApp.class);
		openplatformApp = OpenplatformAppInfrastructureStructMapping.instance. openplatformAppDOToOpenplatformApp(openplatformApp,byId);
		return openplatformApp;
	}

	@Override
	public boolean doSave(OpenplatformApp openplatformApp) {
		OpenplatformAppDO openplatformAppDO = OpenplatformAppInfrastructureStructMapping.instance.openplatformAppToOpenplatformAppDO(openplatformApp);
		if (openplatformAppDO.getId() == null) {
			openplatformAppDO.setAddControl(openplatformApp.getAddControl());
			OpenplatformAppDO add = iOpenplatformAppService.add(openplatformAppDO);
			openplatformApp.setId(OpenplatformAppId.of(add.getId()));
			return add != null;
		}
		openplatformAppDO.setUpdateControl(openplatformApp.getUpdateControl());
		OpenplatformAppDO update = iOpenplatformAppService.update(openplatformAppDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformAppId openplatformAppId) {
		return iOpenplatformAppService.deleteById(openplatformAppId.getId());
	}


	@Autowired
	public void setIOpenplatformAppService(IOpenplatformAppService iOpenplatformAppService) {
		this.iOpenplatformAppService = iOpenplatformAppService;
	}
}
