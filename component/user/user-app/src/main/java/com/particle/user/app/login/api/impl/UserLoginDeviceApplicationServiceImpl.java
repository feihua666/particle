package com.particle.user.app.login.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.app.login.executor.UserLoginDeviceDeleteCommandExecutor;
import com.particle.user.client.login.api.IUserLoginDeviceApplicationService;
import com.particle.user.client.login.dto.command.UserLoginDeviceDeleteCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


	private UserLoginDeviceDeleteCommandExecutor userLoginDeviceDeleteCommandExecutor;


	@Override
	public SingleResponse<UserLoginDeviceVO> delete(UserLoginDeviceDeleteCommand userLoginDeviceDeleteCommand) {
		return userLoginDeviceDeleteCommandExecutor.execute(userLoginDeviceDeleteCommand);
	}


	@Autowired
	public void setUserLoginDeviceDeleteCommandExecutor(UserLoginDeviceDeleteCommandExecutor userLoginDeviceDeleteCommandExecutor) {
		this.userLoginDeviceDeleteCommandExecutor = userLoginDeviceDeleteCommandExecutor;
	}


}
