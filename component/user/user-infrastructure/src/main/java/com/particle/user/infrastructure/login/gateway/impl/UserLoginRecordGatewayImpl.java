package com.particle.user.infrastructure.login.gateway.impl;

import com.particle.user.domain.login.UserLoginRecord;
import com.particle.user.domain.login.UserLoginRecordId;
import com.particle.user.domain.login.gateway.UserLoginRecordGateway;
import com.particle.user.infrastructure.login.service.IUserLoginRecordService;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.infrastructure.login.structmapping.UserLoginRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户登录记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
public class UserLoginRecordGatewayImpl extends AbstractBaseGatewayImpl<UserLoginRecordId,UserLoginRecord> implements UserLoginRecordGateway {

	private IUserLoginRecordService iUserLoginRecordService;

	@Override
	public UserLoginRecord getById(UserLoginRecordId userLoginRecordId) {
		UserLoginRecordDO byId = iUserLoginRecordService.getById(userLoginRecordId.getId());
		UserLoginRecord userLoginRecord = DomainFactory.create(UserLoginRecord.class);
		userLoginRecord = UserLoginRecordInfrastructureStructMapping.instance. userLoginRecordDOToUserLoginRecord(userLoginRecord,byId);
		return userLoginRecord;
	}

	@Override
	public boolean doSave(UserLoginRecord userLoginRecord) {
		UserLoginRecordDO userLoginRecordDO = UserLoginRecordInfrastructureStructMapping.instance.userLoginRecordToUserLoginRecordDO(userLoginRecord);
		if (userLoginRecordDO.getId() == null) {
			UserLoginRecordDO add = iUserLoginRecordService.add(userLoginRecordDO);
			userLoginRecord.setId(UserLoginRecordId.of(add.getId()));
			return add != null;
		}
		UserLoginRecordDO update = iUserLoginRecordService.update(userLoginRecordDO);
		return update != null;
	}

	@Override
	public boolean delete(UserLoginRecordId userLoginRecordId) {
		return iUserLoginRecordService.deleteById(userLoginRecordId.getId());
	}


	@Autowired
	public void setIUserLoginRecordService(IUserLoginRecordService iUserLoginRecordService) {
		this.iUserLoginRecordService = iUserLoginRecordService;
	}
}
