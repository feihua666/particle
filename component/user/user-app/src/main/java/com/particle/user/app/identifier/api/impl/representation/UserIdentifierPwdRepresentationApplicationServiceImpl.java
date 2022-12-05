package com.particle.user.app.identifier.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.app.identifier.executor.representation.UserIdentifierPwdQueryCommandExecutor;
import com.particle.user.client.identifier.api.representation.IUserIdentifierPwdRepresentationApplicationService;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryListCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 用户密码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Service
@CatchAndLog
public class UserIdentifierPwdRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserIdentifierPwdRepresentationApplicationService {

	private UserIdentifierPwdQueryCommandExecutor userIdentifierPwdQueryCommandExecutor;

	@Override
	public SingleResponse<UserIdentifierPwdVO> queryDetail(IdCommand userIdentifierPwdQueryDetailCommand) {
		return userIdentifierPwdQueryCommandExecutor.executeDetail(userIdentifierPwdQueryDetailCommand);
	}

	@Override
	public SingleResponse<UserIdentifierPwdVO> queryDetailForUpdate(IdCommand userIdentifierPwdQueryDetailForUpdateCommand) {
		return userIdentifierPwdQueryCommandExecutor.executeDetailForUpdate(userIdentifierPwdQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<UserIdentifierPwdVO> pageQuery(UserIdentifierPwdPageQueryCommand userIdentifierPwdPageQueryCommand) {
		return userIdentifierPwdQueryCommandExecutor.execute(userIdentifierPwdPageQueryCommand);
	}

	@Override
	public MultiResponse<UserIdentifierPwdVO> queryList(UserIdentifierPwdQueryListCommand userIdentifierPwdQueryListCommand) {
		return userIdentifierPwdQueryCommandExecutor.execute(userIdentifierPwdQueryListCommand);
	}

	@Autowired
	public void setUserIdentifierPwdQueryCommandExecutor(UserIdentifierPwdQueryCommandExecutor userIdentifierPwdQueryCommandExecutor) {
		this.userIdentifierPwdQueryCommandExecutor = userIdentifierPwdQueryCommandExecutor;
	}
}
