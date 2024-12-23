package com.particle.user.app.identifier.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.app.identifier.executor.UserIdentifierCreateCommandExecutor;
import com.particle.user.app.identifier.executor.UserIdentifierDeleteCommandExecutor;
import com.particle.user.app.identifier.executor.UserIdentifierUpdateCommandExecutor;
import com.particle.user.client.identifier.api.IUserIdentifierApplicationService;
import com.particle.user.client.identifier.dto.command.UserIdentifierCreateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierUpdateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 用户登录标识 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Transactional
@Service
@CatchAndLog
public class UserIdentifierApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserIdentifierApplicationService {

	private UserIdentifierCreateCommandExecutor userIdentifierCreateCommandExecutor;

	private UserIdentifierDeleteCommandExecutor userIdentifierDeleteCommandExecutor;

	private UserIdentifierUpdateCommandExecutor userIdentifierUpdateCommandExecutor;


	@Override
	public SingleResponse<UserIdentifierVO> create(UserIdentifierCreateCommand userIdentifierCreateCommand, UserIdentifierPwdCommand userIdentifierPwdCommand) {
		return userIdentifierCreateCommandExecutor.execute(userIdentifierCreateCommand, userIdentifierPwdCommand);
	}

	@Override
	public SingleResponse<UserIdentifierVO> createBind(UserIdentifierCreateCommand userIdentifierCreateCommand) {
		return userIdentifierCreateCommandExecutor.createBind(userIdentifierCreateCommand);

	}

	@Override
	public SingleResponse<UserIdentifierVO> delete(IdCommand userIdentifierDeleteCommand) {
		return userIdentifierDeleteCommandExecutor.execute(userIdentifierDeleteCommand);
	}

	@Override
	public SingleResponse<UserIdentifierVO> update(UserIdentifierUpdateCommand userIdentifierUpdateCommand) {
		return userIdentifierUpdateCommandExecutor.execute(userIdentifierUpdateCommand);
	}

	@Autowired
	public void setUserIdentifierCreateCommandExecutor(UserIdentifierCreateCommandExecutor userIdentifierCreateCommandExecutor) {
		this.userIdentifierCreateCommandExecutor = userIdentifierCreateCommandExecutor;
	}

	@Autowired
	public void setUserIdentifierDeleteCommandExecutor(UserIdentifierDeleteCommandExecutor userIdentifierDeleteCommandExecutor) {
		this.userIdentifierDeleteCommandExecutor = userIdentifierDeleteCommandExecutor;
	}
	@Autowired
	public void setUserIdentifierUpdateCommandExecutor(UserIdentifierUpdateCommandExecutor userIdentifierUpdateCommandExecutor) {
		this.userIdentifierUpdateCommandExecutor = userIdentifierUpdateCommandExecutor;
	}

}
