package com.particle.user.app.identifier.api.impl;

import com.particle.user.app.identifier.executor.UserIdentifierPwdCreateCommandExecutor;
import com.particle.user.app.identifier.executor.UserIdentifierPwdDeleteCommandExecutor;
import com.particle.user.app.identifier.executor.UserIdentifierPwdUpdateCommandExecutor;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdDeleteCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdUpdateCommand;
import com.particle.user.client.identifier.api.IUserIdentifierPwdApplicationService;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCreateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
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
 * 用户密码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Transactional
@Service
@CatchAndLog
public class UserIdentifierPwdApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserIdentifierPwdApplicationService {

	private UserIdentifierPwdCreateCommandExecutor userIdentifierPwdCreateCommandExecutor;

	private UserIdentifierPwdDeleteCommandExecutor userIdentifierPwdDeleteCommandExecutor;

	private UserIdentifierPwdUpdateCommandExecutor userIdentifierPwdUpdateCommandExecutor;


	@Override
	public SingleResponse<UserIdentifierPwdVO> create(UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand) {
		return userIdentifierPwdCreateCommandExecutor.execute(userIdentifierPwdCreateCommand);
	}

	@Override
	public SingleResponse<UserIdentifierPwdVO> delete(UserIdentifierPwdDeleteCommand userIdentifierPwdDeleteCommand) {
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
