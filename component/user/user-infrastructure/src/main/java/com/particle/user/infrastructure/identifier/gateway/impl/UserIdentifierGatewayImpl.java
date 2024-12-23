package com.particle.user.infrastructure.identifier.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.UserIdentifierId;
import com.particle.user.domain.identifier.gateway.UserIdentifierGateway;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.user.infrastructure.identifier.structmapping.UserIdentifierInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户登录标识 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class UserIdentifierGatewayImpl extends AbstractBaseGatewayImpl<UserIdentifierId,UserIdentifier> implements UserIdentifierGateway {

	private IUserIdentifierService iUserIdentifierService;

	@Override
	public UserIdentifier getById(UserIdentifierId userIdentifierId) {
		UserIdentifierDO byId = iUserIdentifierService.getById(userIdentifierId.getId());
		UserIdentifier userIdentifier = DomainFactory.create(UserIdentifier.class);
		userIdentifier = UserIdentifierInfrastructureStructMapping.instance. userIdentifierDOToUserIdentifier(userIdentifier,byId);
		return userIdentifier;
	}

	@Override
	public boolean doSave(UserIdentifier userIdentifier) {
		UserIdentifierDO userIdentifierDO = UserIdentifierInfrastructureStructMapping.instance.userIdentifierToUserIdentifierDO(userIdentifier);
		if (userIdentifierDO.getId() == null) {
			UserIdentifierDO add = iUserIdentifierService.add(userIdentifierDO);
			userIdentifier.setId(UserIdentifierId.of(add.getId()));
			return add != null;
		}
		UserIdentifierDO update = iUserIdentifierService.update(userIdentifierDO);
		return update != null;
	}

	@Override
	public boolean delete(UserIdentifierId userIdentifierId) {
		return iUserIdentifierService.deleteById(userIdentifierId.getId());
	}

	@Override
	public boolean delete(UserIdentifierId id, IdCommand idCommand) {
		return iUserIdentifierService.deleteById(idCommand);
	}

	@Autowired
	public void setIUserIdentifierService(IUserIdentifierService iUserIdentifierService) {
		this.iUserIdentifierService = iUserIdentifierService;
	}
}
