package com.particle.oauth2authorization.infrastructure.client.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClient;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClientId;
import com.particle.oauth2authorization.domain.client.gateway.Oauth2RegisteredClientGateway;
import com.particle.oauth2authorization.infrastructure.client.service.IOauth2RegisteredClientService;
import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import com.particle.oauth2authorization.infrastructure.client.structmapping.Oauth2RegisteredClientInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * oauth2客户端 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Component
public class Oauth2RegisteredClientGatewayImpl extends AbstractBaseGatewayImpl<Oauth2RegisteredClientId,Oauth2RegisteredClient> implements Oauth2RegisteredClientGateway {

	private IOauth2RegisteredClientService iOauth2RegisteredClientService;

	@Override
	public Oauth2RegisteredClient getById(Oauth2RegisteredClientId oauth2RegisteredClientId) {
		Oauth2RegisteredClientDO byId = iOauth2RegisteredClientService.getById(oauth2RegisteredClientId.getId());
		Oauth2RegisteredClient oauth2RegisteredClient = DomainFactory.create(Oauth2RegisteredClient.class);
		oauth2RegisteredClient = Oauth2RegisteredClientInfrastructureStructMapping.instance. oauth2RegisteredClientDOToOauth2RegisteredClient(oauth2RegisteredClient,byId);
		return oauth2RegisteredClient;
	}

	@Override
	public boolean doSave(Oauth2RegisteredClient oauth2RegisteredClient) {
		Oauth2RegisteredClientDO oauth2RegisteredClientDO = Oauth2RegisteredClientInfrastructureStructMapping.instance.oauth2RegisteredClientToOauth2RegisteredClientDO(oauth2RegisteredClient);
		if (oauth2RegisteredClientDO.getId() == null) {
			oauth2RegisteredClientDO.setAddControl(oauth2RegisteredClient.getAddControl());
			Oauth2RegisteredClientDO add = iOauth2RegisteredClientService.add(oauth2RegisteredClientDO);
			oauth2RegisteredClient.setId(Oauth2RegisteredClientId.of(add.getId()));
			return add != null;
		}
		oauth2RegisteredClientDO.setUpdateControl(oauth2RegisteredClient.getUpdateControl());
		Oauth2RegisteredClientDO update = iOauth2RegisteredClientService.update(oauth2RegisteredClientDO);
		return update != null;
	}

	@Override
	public boolean delete(Oauth2RegisteredClientId oauth2RegisteredClientId) {
		return iOauth2RegisteredClientService.deleteById(oauth2RegisteredClientId.getId());
	}

	@Override
	public boolean delete(Oauth2RegisteredClientId id, IdCommand idCommand) {
		return iOauth2RegisteredClientService.deleteById(idCommand);
	}

	@Autowired
	public void setIOauth2RegisteredClientService(IOauth2RegisteredClientService iOauth2RegisteredClientService) {
		this.iOauth2RegisteredClientService = iOauth2RegisteredClientService;
	}
}
