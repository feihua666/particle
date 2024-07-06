package com.particle.user.infrastructure.login.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.user.domain.login.UserLoginDevice;
import com.particle.user.domain.login.UserLoginDeviceId;
import com.particle.user.domain.login.gateway.UserLoginDeviceGateway;
import com.particle.user.infrastructure.login.service.IUserLoginDeviceService;
import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.user.infrastructure.login.structmapping.UserLoginDeviceInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户登录设备 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
public class UserLoginDeviceGatewayImpl extends AbstractBaseGatewayImpl<UserLoginDeviceId,UserLoginDevice> implements UserLoginDeviceGateway {

	private IUserLoginDeviceService iUserLoginDeviceService;

	@Override
	public UserLoginDevice getById(UserLoginDeviceId userLoginDeviceId) {
		UserLoginDeviceDO byId = iUserLoginDeviceService.getById(userLoginDeviceId.getId());
		UserLoginDevice userLoginDevice = DomainFactory.create(UserLoginDevice.class);
		userLoginDevice = UserLoginDeviceInfrastructureStructMapping.instance. userLoginDeviceDOToUserLoginDevice(userLoginDevice,byId);
		return userLoginDevice;
	}

	@Override
	public boolean doSave(UserLoginDevice userLoginDevice) {
		UserLoginDeviceDO userLoginDeviceDO = UserLoginDeviceInfrastructureStructMapping.instance.userLoginDeviceToUserLoginDeviceDO(userLoginDevice);
		if (userLoginDeviceDO.getId() == null) {
			UserLoginDeviceDO add = iUserLoginDeviceService.add(userLoginDeviceDO);
			userLoginDevice.setId(UserLoginDeviceId.of(add.getId()));
			return add != null;
		}
		UserLoginDeviceDO update = iUserLoginDeviceService.update(userLoginDeviceDO);
		return update != null;
	}

	@Override
	public boolean delete(UserLoginDeviceId userLoginDeviceId) {
		return iUserLoginDeviceService.deleteById(userLoginDeviceId.getId());
	}

	@Override
	public boolean delete(UserLoginDeviceId id, IdCommand idCommand) {
		return iUserLoginDeviceService.deleteById(idCommand);
	}

	@Autowired
	public void setIUserLoginDeviceService(IUserLoginDeviceService iUserLoginDeviceService) {
		this.iUserLoginDeviceService = iUserLoginDeviceService;
	}
}
