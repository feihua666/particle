package com.particle.user.app.login.api.impl.representation;

import com.particle.user.app.login.executor.representation.UserLoginRecordQueryCommandExecutor;
import com.particle.user.client.login.dto.command.UserLoginRecordDeleteCommand;
import com.particle.user.client.login.dto.command.UserLoginRecordUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryDetailCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryDetailForUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordPageQueryCommand;
import com.particle.user.client.login.api.representation.IUserLoginRecordRepresentationApplicationService;
import com.particle.user.client.login.dto.command.UserLoginRecordCreateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryListCommand;
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
@Service
@CatchAndLog
public class UserLoginRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserLoginRecordRepresentationApplicationService {

	private UserLoginRecordQueryCommandExecutor userLoginRecordQueryCommandExecutor;

	@Override
	public SingleResponse<UserLoginRecordVO> queryDetail(UserLoginRecordQueryDetailCommand userLoginRecordQueryDetailCommand) {
		return userLoginRecordQueryCommandExecutor.execute(userLoginRecordQueryDetailCommand);
	}

	@Override
	public SingleResponse<UserLoginRecordVO> queryDetailForUpdate(UserLoginRecordQueryDetailForUpdateCommand userLoginRecordQueryDetailForUpdateCommand) {
		return userLoginRecordQueryCommandExecutor.execute(userLoginRecordQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<UserLoginRecordVO> pageQuery(UserLoginRecordPageQueryCommand userLoginRecordPageQueryCommand) {
		return userLoginRecordQueryCommandExecutor.execute(userLoginRecordPageQueryCommand);
	}

	@Override
	public MultiResponse<UserLoginRecordVO> queryList(UserLoginRecordQueryListCommand userLoginRecordQueryListCommand) {
		return userLoginRecordQueryCommandExecutor.execute(userLoginRecordQueryListCommand);
	}

	@Autowired
	public void setUserLoginRecordQueryCommandExecutor(UserLoginRecordQueryCommandExecutor userLoginRecordQueryCommandExecutor) {
		this.userLoginRecordQueryCommandExecutor = userLoginRecordQueryCommandExecutor;
	}
}
