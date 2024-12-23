package com.particle.openplatform.app.openapi.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiFeeCreateCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiFeeDeleteCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiFeeUpdateCommandExecutor;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiFeeApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiFeeCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiFeeUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台开放接口费用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiFeeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiFeeApplicationService {

	private OpenplatformOpenapiFeeCreateCommandExecutor openplatformOpenapiFeeCreateCommandExecutor;

	private OpenplatformOpenapiFeeDeleteCommandExecutor openplatformOpenapiFeeDeleteCommandExecutor;

	private OpenplatformOpenapiFeeUpdateCommandExecutor openplatformOpenapiFeeUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformOpenapiFeeVO> create(OpenplatformOpenapiFeeCreateCommand openplatformOpenapiFeeCreateCommand) {
		return openplatformOpenapiFeeCreateCommandExecutor.execute(openplatformOpenapiFeeCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformOpenapiFeeVO> delete(IdCommand deleteCommand) {
		return openplatformOpenapiFeeDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformOpenapiFeeVO> update(OpenplatformOpenapiFeeUpdateCommand openplatformOpenapiFeeUpdateCommand) {
		return openplatformOpenapiFeeUpdateCommandExecutor.execute(openplatformOpenapiFeeUpdateCommand);
	}

	@Autowired
	public void setOpenplatformOpenapiFeeCreateCommandExecutor(OpenplatformOpenapiFeeCreateCommandExecutor openplatformOpenapiFeeCreateCommandExecutor) {
		this.openplatformOpenapiFeeCreateCommandExecutor = openplatformOpenapiFeeCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformOpenapiFeeDeleteCommandExecutor(OpenplatformOpenapiFeeDeleteCommandExecutor openplatformOpenapiFeeDeleteCommandExecutor) {
		this.openplatformOpenapiFeeDeleteCommandExecutor = openplatformOpenapiFeeDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformOpenapiFeeUpdateCommandExecutor(OpenplatformOpenapiFeeUpdateCommandExecutor openplatformOpenapiFeeUpdateCommandExecutor) {
		this.openplatformOpenapiFeeUpdateCommandExecutor = openplatformOpenapiFeeUpdateCommandExecutor;
	}

}
