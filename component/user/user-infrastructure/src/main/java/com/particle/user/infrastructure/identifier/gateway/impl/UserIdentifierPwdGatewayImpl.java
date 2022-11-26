package com.particle.user.infrastructure.identifier.gateway.impl;

import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.UserIdentifierPwdId;
import com.particle.user.domain.identifier.gateway.UserIdentifierPwdGateway;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.infrastructure.identifier.structmapping.UserIdentifierPwdInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户密码 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class UserIdentifierPwdGatewayImpl extends AbstractBaseGatewayImpl<UserIdentifierPwdId,UserIdentifierPwd> implements UserIdentifierPwdGateway {

	private IUserIdentifierPwdService iUserIdentifierPwdService;

	@Override
	public UserIdentifierPwd getById(UserIdentifierPwdId userIdentifierPwdId) {
		UserIdentifierPwdDO byId = iUserIdentifierPwdService.getById(userIdentifierPwdId.getId());
		UserIdentifierPwd userIdentifierPwd = DomainFactory.create(UserIdentifierPwd.class);
		userIdentifierPwd = UserIdentifierPwdInfrastructureStructMapping.instance. userIdentifierPwdDOToUserIdentifierPwd(userIdentifierPwd,byId);
		return userIdentifierPwd;
	}

	@Override
	public boolean doSave(UserIdentifierPwd userIdentifierPwd) {
		UserIdentifierPwdDO userIdentifierPwdDO = UserIdentifierPwdInfrastructureStructMapping.instance.userIdentifierPwdToUserIdentifierPwdDO(userIdentifierPwd);
		if (userIdentifierPwdDO.getId() == null) {
			UserIdentifierPwdDO add = iUserIdentifierPwdService.add(userIdentifierPwdDO);
			userIdentifierPwd.setId(UserIdentifierPwdId.of(add.getId()));
			return add != null;
		}
		UserIdentifierPwdDO update = iUserIdentifierPwdService.update(userIdentifierPwdDO);
		return update != null;
	}

	@Override
	public boolean delete(UserIdentifierPwdId userIdentifierPwdId) {
		return iUserIdentifierPwdService.deleteById(userIdentifierPwdId.getId());
	}


	@Autowired
	public void setIUserIdentifierPwdService(IUserIdentifierPwdService iUserIdentifierPwdService) {
		this.iUserIdentifierPwdService = iUserIdentifierPwdService;
	}
}
