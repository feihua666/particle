package com.particle.openplatform.app.app.api.impl;

import com.particle.openplatform.app.app.executor.OpenplatformAppCreateCommandExecutor;
import com.particle.openplatform.app.app.executor.OpenplatformAppDeleteCommandExecutor;
import com.particle.openplatform.app.app.executor.OpenplatformAppUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppUpdateCommand;
import com.particle.openplatform.client.app.api.IOpenplatformAppApplicationService;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppCreateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;
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
 * 开放平台应用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformAppApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformAppApplicationService {

	private OpenplatformAppCreateCommandExecutor openplatformAppCreateCommandExecutor;

	private OpenplatformAppDeleteCommandExecutor openplatformAppDeleteCommandExecutor;

	private OpenplatformAppUpdateCommandExecutor openplatformAppUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformAppVO> create(OpenplatformAppCreateCommand openplatformAppCreateCommand) {
		return openplatformAppCreateCommandExecutor.execute(openplatformAppCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformAppVO> delete(IdCommand deleteCommand) {
		return openplatformAppDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformAppVO> update(OpenplatformAppUpdateCommand openplatformAppUpdateCommand) {
		return openplatformAppUpdateCommandExecutor.execute(openplatformAppUpdateCommand);
	}

	@Autowired
	public void setOpenplatformAppCreateCommandExecutor(OpenplatformAppCreateCommandExecutor openplatformAppCreateCommandExecutor) {
		this.openplatformAppCreateCommandExecutor = openplatformAppCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformAppDeleteCommandExecutor(OpenplatformAppDeleteCommandExecutor openplatformAppDeleteCommandExecutor) {
		this.openplatformAppDeleteCommandExecutor = openplatformAppDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformAppUpdateCommandExecutor(OpenplatformAppUpdateCommandExecutor openplatformAppUpdateCommandExecutor) {
		this.openplatformAppUpdateCommandExecutor = openplatformAppUpdateCommandExecutor;
	}

}
