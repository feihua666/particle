package com.particle.user.infrastructure.gateway.impl;

import com.particle.user.domain.User;
import com.particle.user.domain.UserId;
import com.particle.user.domain.gateway.UserGateway;
import com.particle.user.infrastructure.service.IUserService;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.structmapping.UserInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class UserGatewayImpl extends AbstractBaseGatewayImpl<UserId,User> implements UserGateway {

	private IUserService iUserService;

	@Override
	public User getById(UserId userId) {
		UserDO byId = iUserService.getById(userId.getId());
		User user = DomainFactory.create(User.class);
		user = UserInfrastructureStructMapping.instance. userDOToUser(user,byId);
		return user;
	}

	@Override
	public boolean doSave(User user) {
		UserDO userDO = UserInfrastructureStructMapping.instance.userToUserDO(user);
		if (userDO.getId() == null) {
			UserDO add = iUserService.add(userDO);
			user.setId(UserId.of(add.getId()));
			return add != null;
		}
		UserDO update = iUserService.update(userDO);
		return update != null;
	}

	@Override
	public boolean delete(UserId userId) {
		return iUserService.deleteById(userId.getId());
	}


	@Autowired
	public void setIUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}
}
