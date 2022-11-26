package com.particle.user.app.login.api.impl.representation;

import com.particle.user.app.login.executor.representation.UserLoginDeviceQueryCommandExecutor;
import com.particle.user.client.login.dto.command.UserLoginDeviceDeleteCommand;
import com.particle.user.client.login.dto.command.UserLoginDeviceUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailForUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDevicePageQueryCommand;
import com.particle.user.client.login.api.representation.IUserLoginDeviceRepresentationApplicationService;
import com.particle.user.client.login.dto.command.UserLoginDeviceCreateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryListCommand;
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
@Service
@CatchAndLog
public class UserLoginDeviceRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserLoginDeviceRepresentationApplicationService {

	private UserLoginDeviceQueryCommandExecutor userLoginDeviceQueryCommandExecutor;

	@Override
	public SingleResponse<UserLoginDeviceVO> queryDetail(UserLoginDeviceQueryDetailCommand userLoginDeviceQueryDetailCommand) {
		return userLoginDeviceQueryCommandExecutor.execute(userLoginDeviceQueryDetailCommand);
	}

	@Override
	public SingleResponse<UserLoginDeviceVO> queryDetailForUpdate(UserLoginDeviceQueryDetailForUpdateCommand userLoginDeviceQueryDetailForUpdateCommand) {
		return userLoginDeviceQueryCommandExecutor.execute(userLoginDeviceQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<UserLoginDeviceVO> pageQuery(UserLoginDevicePageQueryCommand userLoginDevicePageQueryCommand) {
		return userLoginDeviceQueryCommandExecutor.execute(userLoginDevicePageQueryCommand);
	}

	@Override
	public MultiResponse<UserLoginDeviceVO> queryList(UserLoginDeviceQueryListCommand userLoginDeviceQueryListCommand) {
		return userLoginDeviceQueryCommandExecutor.execute(userLoginDeviceQueryListCommand);
	}

	@Autowired
	public void setUserLoginDeviceQueryCommandExecutor(UserLoginDeviceQueryCommandExecutor userLoginDeviceQueryCommandExecutor) {
		this.userLoginDeviceQueryCommandExecutor = userLoginDeviceQueryCommandExecutor;
	}
}
