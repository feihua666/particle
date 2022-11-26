package com.particle.user.infrastructure.login.service.impl;

import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.user.infrastructure.login.mapper.UserLoginDeviceMapper;
import com.particle.user.infrastructure.login.service.IUserLoginDeviceService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户登录设备 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
public class UserLoginDeviceServiceImpl extends IBaseServiceImpl<UserLoginDeviceMapper, UserLoginDeviceDO> implements IUserLoginDeviceService {
	private IBaseQueryCommandMapStruct<UserLoginDeviceDO> queryCommandMapStruct;

	@Override
	protected UserLoginDeviceDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserLoginDeviceDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
