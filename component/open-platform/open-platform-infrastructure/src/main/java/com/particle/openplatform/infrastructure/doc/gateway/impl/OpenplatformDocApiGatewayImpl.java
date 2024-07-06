package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocApi;
import com.particle.openplatform.domain.doc.OpenplatformDocApiId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档接口 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Component
public class OpenplatformDocApiGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiId,OpenplatformDocApi> implements OpenplatformDocApiGateway {

	private IOpenplatformDocApiService iOpenplatformDocApiService;

	@Override
	public OpenplatformDocApi getById(OpenplatformDocApiId openplatformDocApiId) {
		OpenplatformDocApiDO byId = iOpenplatformDocApiService.getById(openplatformDocApiId.getId());
		OpenplatformDocApi openplatformDocApi = DomainFactory.create(OpenplatformDocApi.class);
		openplatformDocApi = OpenplatformDocApiInfrastructureStructMapping.instance. openplatformDocApiDOToOpenplatformDocApi(openplatformDocApi,byId);
		return openplatformDocApi;
	}

	@Override
	public boolean doSave(OpenplatformDocApi openplatformDocApi) {
		OpenplatformDocApiDO openplatformDocApiDO = OpenplatformDocApiInfrastructureStructMapping.instance.openplatformDocApiToOpenplatformDocApiDO(openplatformDocApi);
		if (openplatformDocApiDO.getId() == null) {
			openplatformDocApiDO.setAddControl(openplatformDocApi.getAddControl());
			OpenplatformDocApiDO add = iOpenplatformDocApiService.add(openplatformDocApiDO);
			openplatformDocApi.setId(OpenplatformDocApiId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDO.setUpdateControl(openplatformDocApi.getUpdateControl());
		OpenplatformDocApiDO update = iOpenplatformDocApiService.update(openplatformDocApiDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiId openplatformDocApiId) {
		return iOpenplatformDocApiService.deleteById(openplatformDocApiId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocApiId id, IdCommand idCommand) {
		return iOpenplatformDocApiService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocApiService(IOpenplatformDocApiService iOpenplatformDocApiService) {
		this.iOpenplatformDocApiService = iOpenplatformDocApiService;
	}
}
