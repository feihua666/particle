package com.particle.openplatform.app.app.api.impl;

import com.particle.openplatform.app.app.executor.OpenplatformAppOpenapiCreateCommandExecutor;
import com.particle.openplatform.app.app.executor.OpenplatformAppOpenapiDeleteCommandExecutor;
import com.particle.openplatform.app.app.executor.OpenplatformAppOpenapiUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppOpenapiUpdateCommand;
import com.particle.openplatform.client.app.api.IOpenplatformAppOpenapiApplicationService;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppOpenapiCreateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;
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
 * 开放平台应用与开放接口配置 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformAppOpenapiApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformAppOpenapiApplicationService {

	private OpenplatformAppOpenapiCreateCommandExecutor openplatformAppOpenapiCreateCommandExecutor;

	private OpenplatformAppOpenapiDeleteCommandExecutor openplatformAppOpenapiDeleteCommandExecutor;

	private OpenplatformAppOpenapiUpdateCommandExecutor openplatformAppOpenapiUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformAppOpenapiVO> create(OpenplatformAppOpenapiCreateCommand openplatformAppOpenapiCreateCommand) {
		return openplatformAppOpenapiCreateCommandExecutor.execute(openplatformAppOpenapiCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformAppOpenapiVO> delete(IdCommand deleteCommand) {
		return openplatformAppOpenapiDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformAppOpenapiVO> update(OpenplatformAppOpenapiUpdateCommand openplatformAppOpenapiUpdateCommand) {
		return openplatformAppOpenapiUpdateCommandExecutor.execute(openplatformAppOpenapiUpdateCommand);
	}

	@Autowired
	public void setOpenplatformAppOpenapiCreateCommandExecutor(OpenplatformAppOpenapiCreateCommandExecutor openplatformAppOpenapiCreateCommandExecutor) {
		this.openplatformAppOpenapiCreateCommandExecutor = openplatformAppOpenapiCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformAppOpenapiDeleteCommandExecutor(OpenplatformAppOpenapiDeleteCommandExecutor openplatformAppOpenapiDeleteCommandExecutor) {
		this.openplatformAppOpenapiDeleteCommandExecutor = openplatformAppOpenapiDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformAppOpenapiUpdateCommandExecutor(OpenplatformAppOpenapiUpdateCommandExecutor openplatformAppOpenapiUpdateCommandExecutor) {
		this.openplatformAppOpenapiUpdateCommandExecutor = openplatformAppOpenapiUpdateCommandExecutor;
	}

}
