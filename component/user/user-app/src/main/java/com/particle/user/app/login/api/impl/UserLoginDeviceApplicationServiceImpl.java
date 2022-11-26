package com.particle.user.app.login.api.impl;

import com.particle.user.app.login.executor.UserLoginDeviceCreateCommandExecutor;
import com.particle.user.app.login.executor.UserLoginDeviceDeleteCommandExecutor;
import com.particle.user.app.login.executor.UserLoginDeviceUpdateCommandExecutor;
import com.particle.user.client.login.dto.command.UserLoginDeviceDeleteCommand;
import com.particle.user.client.login.dto.command.UserLoginDeviceUpdateCommand;
import com.particle.user.client.login.api.IUserLoginDeviceApplicationService;
import com.particle.user.client.login.dto.command.UserLoginDeviceCreateCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 用户登录设备 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Transactional
@Service
@CatchAndLog
public class UserLoginDeviceApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserLoginDeviceApplicationService {

	private UserLoginDeviceCreateCommandExecutor userLoginDeviceCreateCommandExecutor;

	private UserLoginDeviceDeleteCommandExecutor userLoginDeviceDeleteCommandExecutor;

	private UserLoginDeviceUpdateCommandExecutor userLoginDeviceUpdateCommandExecutor;


	@Override
	public SingleResponse<UserLoginDeviceVO> create(UserLoginDeviceCreateCommand userLoginDeviceCreateCommand) {
		return userLoginDeviceCreateCommandExecutor.execute(userLoginDeviceCreateCommand);
	}

	@Override
	public SingleResponse<UserLoginDeviceVO> delete(UserLoginDeviceDeleteCommand userLoginDeviceDeleteCommand) {
		return userLoginDeviceDeleteCommandExecutor.execute(userLoginDeviceDeleteCommand);
	}

	@Override
	public SingleResponse<UserLoginDeviceVO> update(UserLoginDeviceUpdateCommand userLoginDeviceUpdateCommand) {
		return userLoginDeviceUpdateCommandExecutor.execute(userLoginDeviceUpdateCommand);
	}

	@Autowired
	public void setUserLoginDeviceCreateCommandExecutor(UserLoginDeviceCreateCommandExecutor userLoginDeviceCreateCommandExecutor) {
		this.userLoginDeviceCreateCommandExecutor = userLoginDeviceCreateCommandExecutor;
	}

	@Autowired
	public void setUserLoginDeviceDeleteCommandExecutor(UserLoginDeviceDeleteCommandExecutor userLoginDeviceDeleteCommandExecutor) {
		this.userLoginDeviceDeleteCommandExecutor = userLoginDeviceDeleteCommandExecutor;
	}
	@Autowired
	public void setUserLoginDeviceUpdateCommandExecutor(UserLoginDeviceUpdateCommandExecutor userLoginDeviceUpdateCommandExecutor) {
		this.userLoginDeviceUpdateCommandExecutor = userLoginDeviceUpdateCommandExecutor;
	}

}
