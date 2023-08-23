package com.particle.openplatform.app.openapi.api.impl;

import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiCreateCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiDeleteCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiUpdateCommand;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
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
 * 开放平台开放接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiApplicationService {

	private OpenplatformOpenapiCreateCommandExecutor openplatformOpenapiCreateCommandExecutor;

	private OpenplatformOpenapiDeleteCommandExecutor openplatformOpenapiDeleteCommandExecutor;

	private OpenplatformOpenapiUpdateCommandExecutor openplatformOpenapiUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformOpenapiVO> create(OpenplatformOpenapiCreateCommand openplatformOpenapiCreateCommand) {
		return openplatformOpenapiCreateCommandExecutor.execute(openplatformOpenapiCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformOpenapiVO> delete(IdCommand deleteCommand) {
		return openplatformOpenapiDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformOpenapiVO> update(OpenplatformOpenapiUpdateCommand openplatformOpenapiUpdateCommand) {
		return openplatformOpenapiUpdateCommandExecutor.execute(openplatformOpenapiUpdateCommand);
	}

	@Autowired
	public void setOpenplatformOpenapiCreateCommandExecutor(OpenplatformOpenapiCreateCommandExecutor openplatformOpenapiCreateCommandExecutor) {
		this.openplatformOpenapiCreateCommandExecutor = openplatformOpenapiCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformOpenapiDeleteCommandExecutor(OpenplatformOpenapiDeleteCommandExecutor openplatformOpenapiDeleteCommandExecutor) {
		this.openplatformOpenapiDeleteCommandExecutor = openplatformOpenapiDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformOpenapiUpdateCommandExecutor(OpenplatformOpenapiUpdateCommandExecutor openplatformOpenapiUpdateCommandExecutor) {
		this.openplatformOpenapiUpdateCommandExecutor = openplatformOpenapiUpdateCommandExecutor;
	}

}
