package com.particle.test.app.api.impl;

import com.particle.test.app.executor.UserSimpleCreateCommandExecutor;
import com.particle.test.app.executor.UserSimpleDeleteCommandExecutor;
import com.particle.test.app.executor.UserSimpleUpdateCommandExecutor;
import com.particle.test.app.executor.UserSimpleQueryCommandExecutor;
import com.particle.test.client.dto.command.UserSimpleDeleteCommand;
import com.particle.test.client.dto.command.UserSimpleUpdateCommand;
import com.particle.test.client.dto.command.UserSimpleQueryDetailCommand;
import com.particle.test.client.dto.command.UserSimpleQueryDetailForUpdateCommand;
import com.particle.test.client.dto.command.UserSimplePageQueryCommand;
import com.particle.test.client.api.IUserSimpleApplicationService;
import com.particle.test.client.dto.command.UserSimpleCreateCommand;
import com.particle.test.client.dto.command.UserSimpleQueryListCommand;
import com.particle.test.client.dto.data.UserSimpleVO;
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
 * 简单用户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Service
@CatchAndLog
public class UserSimpleApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserSimpleApplicationService {

	private UserSimpleCreateCommandExecutor userSimpleCreateCommandExecutor;

	private UserSimpleDeleteCommandExecutor userSimpleDeleteCommandExecutor;

	private UserSimpleUpdateCommandExecutor userSimpleUpdateCommandExecutor;

	private UserSimpleQueryCommandExecutor userSimpleQueryCommandExecutor;

	@Transactional
	@Override
	public SingleResponse<UserSimpleVO> create(UserSimpleCreateCommand userSimpleCreateCommand) {
		return userSimpleCreateCommandExecutor.execute(userSimpleCreateCommand);
	}
	@Transactional
	@Override
	public SingleResponse<UserSimpleVO> delete(UserSimpleDeleteCommand userSimpleDeleteCommand) {
		return userSimpleDeleteCommandExecutor.execute(userSimpleDeleteCommand);
	}
	@Transactional
	@Override
	public SingleResponse<UserSimpleVO> update(UserSimpleUpdateCommand userSimpleUpdateCommand) {
		return userSimpleUpdateCommandExecutor.execute(userSimpleUpdateCommand);
	}

	@Override
	public SingleResponse<UserSimpleVO> queryDetail(UserSimpleQueryDetailCommand userSimpleQueryDetailCommand) {
		return userSimpleQueryCommandExecutor.execute(userSimpleQueryDetailCommand);
	}

	@Override
	public SingleResponse<UserSimpleVO> queryDetailForUpdate(UserSimpleQueryDetailForUpdateCommand userSimpleQueryDetailForUpdateCommand) {
		return userSimpleQueryCommandExecutor.execute(userSimpleQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<UserSimpleVO> pageQuery(UserSimplePageQueryCommand userSimplePageQueryCommand) {
		return userSimpleQueryCommandExecutor.execute(userSimplePageQueryCommand);
	}

	@Override
	public MultiResponse<UserSimpleVO> queryList(UserSimpleQueryListCommand userSimpleQueryListCommand) {
		return userSimpleQueryCommandExecutor.execute(userSimpleQueryListCommand);
	}

	@Autowired
	public void setUserSimpleCreateCommandExecutor(UserSimpleCreateCommandExecutor userSimpleCreateCommandExecutor) {
		this.userSimpleCreateCommandExecutor = userSimpleCreateCommandExecutor;
	}

	@Autowired
	public void setUserSimpleDeleteCommandExecutor(UserSimpleDeleteCommandExecutor userSimpleDeleteCommandExecutor) {
		this.userSimpleDeleteCommandExecutor = userSimpleDeleteCommandExecutor;
	}
	@Autowired
	public void setUserSimpleUpdateCommandExecutor(UserSimpleUpdateCommandExecutor userSimpleUpdateCommandExecutor) {
		this.userSimpleUpdateCommandExecutor = userSimpleUpdateCommandExecutor;
	}
	@Autowired
	public void setUserSimpleQueryCommandExecutor(UserSimpleQueryCommandExecutor userSimpleQueryCommandExecutor) {
		this.userSimpleQueryCommandExecutor = userSimpleQueryCommandExecutor;
	}
}
