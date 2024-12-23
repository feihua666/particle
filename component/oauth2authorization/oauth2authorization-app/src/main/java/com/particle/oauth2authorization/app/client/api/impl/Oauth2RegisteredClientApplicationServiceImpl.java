package com.particle.oauth2authorization.app.client.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oauth2authorization.app.client.executor.Oauth2RegisteredClientCreateCommandExecutor;
import com.particle.oauth2authorization.app.client.executor.Oauth2RegisteredClientDeleteCommandExecutor;
import com.particle.oauth2authorization.app.client.executor.Oauth2RegisteredClientUpdateCommandExecutor;
import com.particle.oauth2authorization.client.client.api.IOauth2RegisteredClientApplicationService;
import com.particle.oauth2authorization.client.client.dto.command.Oauth2RegisteredClientCreateCommand;
import com.particle.oauth2authorization.client.client.dto.command.Oauth2RegisteredClientUpdateCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * oauth2客户端 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Transactional
@Service
@CatchAndLog
public class Oauth2RegisteredClientApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOauth2RegisteredClientApplicationService {

	private Oauth2RegisteredClientCreateCommandExecutor oauth2RegisteredClientCreateCommandExecutor;

	private Oauth2RegisteredClientDeleteCommandExecutor oauth2RegisteredClientDeleteCommandExecutor;

	private Oauth2RegisteredClientUpdateCommandExecutor oauth2RegisteredClientUpdateCommandExecutor;


	@Override
	public SingleResponse<Oauth2RegisteredClientVO> create(Oauth2RegisteredClientCreateCommand oauth2RegisteredClientCreateCommand) {
		return oauth2RegisteredClientCreateCommandExecutor.execute(oauth2RegisteredClientCreateCommand);
	}

	@Override
	public SingleResponse<Oauth2RegisteredClientVO> delete(IdCommand deleteCommand) {
		return oauth2RegisteredClientDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<Oauth2RegisteredClientVO> update(Oauth2RegisteredClientUpdateCommand oauth2RegisteredClientUpdateCommand) {
		return oauth2RegisteredClientUpdateCommandExecutor.execute(oauth2RegisteredClientUpdateCommand);
	}

	@Autowired
	public void setOauth2RegisteredClientCreateCommandExecutor(Oauth2RegisteredClientCreateCommandExecutor oauth2RegisteredClientCreateCommandExecutor) {
		this.oauth2RegisteredClientCreateCommandExecutor = oauth2RegisteredClientCreateCommandExecutor;
	}

	@Autowired
	public void setOauth2RegisteredClientDeleteCommandExecutor(Oauth2RegisteredClientDeleteCommandExecutor oauth2RegisteredClientDeleteCommandExecutor) {
		this.oauth2RegisteredClientDeleteCommandExecutor = oauth2RegisteredClientDeleteCommandExecutor;
	}
	@Autowired
	public void setOauth2RegisteredClientUpdateCommandExecutor(Oauth2RegisteredClientUpdateCommandExecutor oauth2RegisteredClientUpdateCommandExecutor) {
		this.oauth2RegisteredClientUpdateCommandExecutor = oauth2RegisteredClientUpdateCommandExecutor;
	}

}
