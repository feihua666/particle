package com.particle.user.app.login.api.impl;

import com.particle.user.app.login.executor.UserLoginRecordCreateCommandExecutor;
import com.particle.user.app.login.executor.UserLoginRecordDeleteCommandExecutor;
import com.particle.user.app.login.executor.UserLoginRecordUpdateCommandExecutor;
import com.particle.user.client.login.dto.command.UserLoginRecordDeleteCommand;
import com.particle.user.client.login.dto.command.UserLoginRecordUpdateCommand;
import com.particle.user.client.login.api.IUserLoginRecordApplicationService;
import com.particle.user.client.login.dto.command.UserLoginRecordCreateCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
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
 * 用户登录记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Transactional
@Service
@CatchAndLog
public class UserLoginRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserLoginRecordApplicationService {

	private UserLoginRecordCreateCommandExecutor userLoginRecordCreateCommandExecutor;

	private UserLoginRecordDeleteCommandExecutor userLoginRecordDeleteCommandExecutor;

	private UserLoginRecordUpdateCommandExecutor userLoginRecordUpdateCommandExecutor;


	@Override
	public SingleResponse<UserLoginRecordVO> create(UserLoginRecordCreateCommand userLoginRecordCreateCommand) {
		return userLoginRecordCreateCommandExecutor.execute(userLoginRecordCreateCommand);
	}

	@Override
	public SingleResponse<UserLoginRecordVO> delete(UserLoginRecordDeleteCommand userLoginRecordDeleteCommand) {
		return userLoginRecordDeleteCommandExecutor.execute(userLoginRecordDeleteCommand);
	}

	@Override
	public SingleResponse<UserLoginRecordVO> update(UserLoginRecordUpdateCommand userLoginRecordUpdateCommand) {
		return userLoginRecordUpdateCommandExecutor.execute(userLoginRecordUpdateCommand);
	}

	@Autowired
	public void setUserLoginRecordCreateCommandExecutor(UserLoginRecordCreateCommandExecutor userLoginRecordCreateCommandExecutor) {
		this.userLoginRecordCreateCommandExecutor = userLoginRecordCreateCommandExecutor;
	}

	@Autowired
	public void setUserLoginRecordDeleteCommandExecutor(UserLoginRecordDeleteCommandExecutor userLoginRecordDeleteCommandExecutor) {
		this.userLoginRecordDeleteCommandExecutor = userLoginRecordDeleteCommandExecutor;
	}
	@Autowired
	public void setUserLoginRecordUpdateCommandExecutor(UserLoginRecordUpdateCommandExecutor userLoginRecordUpdateCommandExecutor) {
		this.userLoginRecordUpdateCommandExecutor = userLoginRecordUpdateCommandExecutor;
	}

}
