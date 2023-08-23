package com.particle.openplatform.app.provider.api.impl;

import com.particle.openplatform.app.provider.executor.OpenplatformProviderCreateCommandExecutor;
import com.particle.openplatform.app.provider.executor.OpenplatformProviderDeleteCommandExecutor;
import com.particle.openplatform.app.provider.executor.OpenplatformProviderUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderUpdateCommand;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApplicationService;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderCreateCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
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
 * 开放平台开放接口供应商 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformProviderApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderApplicationService {

	private OpenplatformProviderCreateCommandExecutor openplatformProviderCreateCommandExecutor;

	private OpenplatformProviderDeleteCommandExecutor openplatformProviderDeleteCommandExecutor;

	private OpenplatformProviderUpdateCommandExecutor openplatformProviderUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformProviderVO> create(OpenplatformProviderCreateCommand openplatformProviderCreateCommand) {
		return openplatformProviderCreateCommandExecutor.execute(openplatformProviderCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformProviderVO> delete(IdCommand deleteCommand) {
		return openplatformProviderDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformProviderVO> update(OpenplatformProviderUpdateCommand openplatformProviderUpdateCommand) {
		return openplatformProviderUpdateCommandExecutor.execute(openplatformProviderUpdateCommand);
	}

	@Autowired
	public void setOpenplatformProviderCreateCommandExecutor(OpenplatformProviderCreateCommandExecutor openplatformProviderCreateCommandExecutor) {
		this.openplatformProviderCreateCommandExecutor = openplatformProviderCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformProviderDeleteCommandExecutor(OpenplatformProviderDeleteCommandExecutor openplatformProviderDeleteCommandExecutor) {
		this.openplatformProviderDeleteCommandExecutor = openplatformProviderDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformProviderUpdateCommandExecutor(OpenplatformProviderUpdateCommandExecutor openplatformProviderUpdateCommandExecutor) {
		this.openplatformProviderUpdateCommandExecutor = openplatformProviderUpdateCommandExecutor;
	}

}
