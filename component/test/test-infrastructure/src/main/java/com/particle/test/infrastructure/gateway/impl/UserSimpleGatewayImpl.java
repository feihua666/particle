package com.particle.test.infrastructure.gateway.impl;

import com.particle.test.domain.UserSimple;
import com.particle.test.domain.UserSimpleId;
import com.particle.test.domain.gateway.UserSimpleGateway;
import com.particle.test.infrastructure.service.IUserSimpleService;
import com.particle.test.infrastructure.dos.UserSimpleDO;
import com.particle.test.infrastructure.structmapping.UserSimpleInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 简单用户 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Component
public class UserSimpleGatewayImpl extends AbstractBaseGatewayImpl implements UserSimpleGateway {

	private IUserSimpleService iUserSimpleService;

	@Override
	public UserSimple getById(UserSimpleId userSimpleId) {
		UserSimpleDO byId = iUserSimpleService.getById(userSimpleId.getId());
		UserSimple userSimple = DomainFactory.create(UserSimple.class);
		userSimple = UserSimpleInfrastructureStructMapping.instance. userSimpleDOToUserSimple(userSimple,byId);
		return userSimple;
	}

	@Override
	public boolean save(UserSimple userSimple) {
		UserSimpleDO userSimpleDO = UserSimpleInfrastructureStructMapping.instance.userSimpleToUserSimpleDO(userSimple);
		if (userSimpleDO.getId() == null) {
			UserSimpleDO add = iUserSimpleService.add(userSimpleDO);
			userSimple.setId(UserSimpleId.of(add.getId()));
			return add != null;
		}
		UserSimpleDO update = iUserSimpleService.update(userSimpleDO);
		return update != null;
	}

	@Override
	public boolean delete(UserSimpleId userSimpleId) {
		return iUserSimpleService.deleteById(userSimpleId.getId());
	}


	@Autowired
	public void setIUserSimpleService(IUserSimpleService iUserSimpleService) {
		this.iUserSimpleService = iUserSimpleService;
	}
}
