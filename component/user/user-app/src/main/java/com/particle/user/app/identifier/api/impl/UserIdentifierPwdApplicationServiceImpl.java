package com.particle.user.app.identifier.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.app.identifier.executor.UserIdentifierPwdCreateCommandExecutor;
import com.particle.user.app.identifier.executor.UserIdentifierPwdDeleteCommandExecutor;
import com.particle.user.app.identifier.executor.UserIdentifierPwdUpdateCommandExecutor;
import com.particle.user.client.identifier.api.IUserIdentifierPwdApplicationService;
import com.particle.user.client.identifier.dto.command.*;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 用户密码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Validated
@Transactional
@Service
@CatchAndLog
public class UserIdentifierPwdApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserIdentifierPwdApplicationService {

	private UserIdentifierPwdCreateCommandExecutor userIdentifierPwdCreateCommandExecutor;

	private UserIdentifierPwdDeleteCommandExecutor userIdentifierPwdDeleteCommandExecutor;

	private UserIdentifierPwdUpdateCommandExecutor userIdentifierPwdUpdateCommandExecutor;

	@Override
	public SingleResponse<UserIdentifierPwdVO> create(UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand, UserIdentifierPasswordCommand userIdentifierPasswordCommand) {
		return userIdentifierPwdCreateCommandExecutor.execute(userIdentifierPwdCreateCommand,userIdentifierPasswordCommand);
	}

	@Override
	public Response identifierResetPassword(UserIdentifierResetPasswordCommand userIdentifierResetPasswordCommand) {
		return userIdentifierPwdUpdateCommandExecutor.identifierResetPassword(userIdentifierResetPasswordCommand);
	}

	@Override
	public Response userResetPassword(UserResetPasswordCommand userResetPasswordCommand) {
		return userIdentifierPwdUpdateCommandExecutor.userResetPassword(userResetPasswordCommand);
	}

	@Override
	public SingleResponse<UserIdentifierPwdVO> delete(IdCommand userIdentifierPwdDeleteCommand) {
		return userIdentifierPwdDeleteCommandExecutor.execute(userIdentifierPwdDeleteCommand);
	}

	@Override
	public SingleResponse<UserIdentifierPwdVO> update(UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand) {
		return userIdentifierPwdUpdateCommandExecutor.execute(userIdentifierPwdUpdateCommand);
	}

	@Autowired
	public void setUserIdentifierPwdCreateCommandExecutor(UserIdentifierPwdCreateCommandExecutor userIdentifierPwdCreateCommandExecutor) {
		this.userIdentifierPwdCreateCommandExecutor = userIdentifierPwdCreateCommandExecutor;
	}

	@Autowired
	public void setUserIdentifierPwdDeleteCommandExecutor(UserIdentifierPwdDeleteCommandExecutor userIdentifierPwdDeleteCommandExecutor) {
		this.userIdentifierPwdDeleteCommandExecutor = userIdentifierPwdDeleteCommandExecutor;
	}
	@Autowired
	public void setUserIdentifierPwdUpdateCommandExecutor(UserIdentifierPwdUpdateCommandExecutor userIdentifierPwdUpdateCommandExecutor) {
		this.userIdentifierPwdUpdateCommandExecutor = userIdentifierPwdUpdateCommandExecutor;
	}

}
