package com.particle.user.app.identifier.api.impl.representation;

import com.particle.user.app.identifier.executor.representation.UserIdentifierQueryCommandExecutor;
import com.particle.user.client.identifier.dto.command.UserIdentifierDeleteCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierUpdateCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryDetailCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryDetailForUpdateCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPageQueryCommand;
import com.particle.user.client.identifier.api.representation.IUserIdentifierRepresentationApplicationService;
import com.particle.user.client.identifier.dto.command.UserIdentifierCreateCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryListCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
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
 * 用户登录标识 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Service
@CatchAndLog
public class UserIdentifierRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserIdentifierRepresentationApplicationService {

	private UserIdentifierQueryCommandExecutor userIdentifierQueryCommandExecutor;

	@Override
	public SingleResponse<UserIdentifierVO> queryDetail(UserIdentifierQueryDetailCommand userIdentifierQueryDetailCommand) {
		return userIdentifierQueryCommandExecutor.execute(userIdentifierQueryDetailCommand);
	}

	@Override
	public SingleResponse<UserIdentifierVO> queryDetailForUpdate(UserIdentifierQueryDetailForUpdateCommand userIdentifierQueryDetailForUpdateCommand) {
		return userIdentifierQueryCommandExecutor.execute(userIdentifierQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<UserIdentifierVO> pageQuery(UserIdentifierPageQueryCommand userIdentifierPageQueryCommand) {
		return userIdentifierQueryCommandExecutor.execute(userIdentifierPageQueryCommand);
	}

	@Override
	public MultiResponse<UserIdentifierVO> queryList(UserIdentifierQueryListCommand userIdentifierQueryListCommand) {
		return userIdentifierQueryCommandExecutor.execute(userIdentifierQueryListCommand);
	}

	@Autowired
	public void setUserIdentifierQueryCommandExecutor(UserIdentifierQueryCommandExecutor userIdentifierQueryCommandExecutor) {
		this.userIdentifierQueryCommandExecutor = userIdentifierQueryCommandExecutor;
	}
}
