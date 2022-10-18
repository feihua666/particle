package com.particle.user.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.app.executor.UserCreateCommandExecutor;
import com.particle.user.app.executor.UserDeleteCommandExecutor;
import com.particle.user.app.executor.UserUpdateCommandExecutor;
import com.particle.user.client.api.IUserApplicationService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserDeleteCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.data.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 后台管理用户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Transactional
@Service
@CatchAndLog
public class UserApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserApplicationService {

	private UserCreateCommandExecutor userCreateCommandExecutor;

	private UserDeleteCommandExecutor userDeleteCommandExecutor;

	private UserUpdateCommandExecutor userUpdateCommandExecutor;


	@Override
	public SingleResponse<UserVO> create(UserCreateCommand userCreateCommand) {
		return userCreateCommandExecutor.execute(userCreateCommand);
	}

	@Override
	public SingleResponse<UserVO> delete(UserDeleteCommand userDeleteCommand) {
		return userDeleteCommandExecutor.execute(userDeleteCommand);
	}

	@Override
	public SingleResponse<UserVO> update(UserUpdateCommand userUpdateCommand) {
		return userUpdateCommandExecutor.execute(userUpdateCommand);
	}

	@Autowired
	public void setUserCreateCommandExecutor(UserCreateCommandExecutor userCreateCommandExecutor) {
		this.userCreateCommandExecutor = userCreateCommandExecutor;
	}

	@Autowired
	public void setUserDeleteCommandExecutor(UserDeleteCommandExecutor userDeleteCommandExecutor) {
		this.userDeleteCommandExecutor = userDeleteCommandExecutor;
	}
	@Autowired
	public void setUserUpdateCommandExecutor(UserUpdateCommandExecutor userUpdateCommandExecutor) {
		this.userUpdateCommandExecutor = userUpdateCommandExecutor;
	}
}
