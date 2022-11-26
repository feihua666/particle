package com.particle.user.app.api.impl.representation;

import com.particle.user.app.executor.representation.UserQueryCommandExecutor;
import com.particle.user.client.dto.command.UserDeleteCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.representation.UserQueryDetailCommand;
import com.particle.user.client.dto.command.representation.UserQueryDetailForUpdateCommand;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.api.representation.IUserRepresentationApplicationService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.client.dto.data.UserVO;
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
 * 用户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Service
@CatchAndLog
public class UserRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserRepresentationApplicationService {

	private UserQueryCommandExecutor userQueryCommandExecutor;

	@Override
	public SingleResponse<UserVO> queryDetail(UserQueryDetailCommand userQueryDetailCommand) {
		return userQueryCommandExecutor.execute(userQueryDetailCommand);
	}

	@Override
	public SingleResponse<UserVO> queryDetailForUpdate(UserQueryDetailForUpdateCommand userQueryDetailForUpdateCommand) {
		return userQueryCommandExecutor.execute(userQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<UserVO> pageQuery(UserPageQueryCommand userPageQueryCommand) {
		return userQueryCommandExecutor.execute(userPageQueryCommand);
	}

	@Override
	public MultiResponse<UserVO> queryList(UserQueryListCommand userQueryListCommand) {
		return userQueryCommandExecutor.execute(userQueryListCommand);
	}

	@Autowired
	public void setUserQueryCommandExecutor(UserQueryCommandExecutor userQueryCommandExecutor) {
		this.userQueryCommandExecutor = userQueryCommandExecutor;
	}
}
