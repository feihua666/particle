package com.particle.user.app.login.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.app.login.executor.UserLoginRecordDeleteCommandExecutor;
import com.particle.user.client.login.api.IUserLoginRecordApplicationService;
import com.particle.user.client.login.dto.command.UserLoginRecordDeleteCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


	private UserLoginRecordDeleteCommandExecutor userLoginRecordDeleteCommandExecutor;


	@Override
	public SingleResponse<UserLoginRecordVO> delete(UserLoginRecordDeleteCommand userLoginRecordDeleteCommand) {
		return userLoginRecordDeleteCommandExecutor.execute(userLoginRecordDeleteCommand);
	}


	@Autowired
	public void setUserLoginRecordDeleteCommandExecutor(UserLoginRecordDeleteCommandExecutor userLoginRecordDeleteCommandExecutor) {
		this.userLoginRecordDeleteCommandExecutor = userLoginRecordDeleteCommandExecutor;
	}

}
