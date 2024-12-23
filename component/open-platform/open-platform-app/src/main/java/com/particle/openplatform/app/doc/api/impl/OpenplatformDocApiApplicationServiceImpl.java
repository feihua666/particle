package com.particle.openplatform.app.doc.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiUpdateCommandExecutor;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口文档接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocApiApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiApplicationService {

	private OpenplatformDocApiCreateCommandExecutor openplatformDocApiCreateCommandExecutor;

	private OpenplatformDocApiDeleteCommandExecutor openplatformDocApiDeleteCommandExecutor;

	private OpenplatformDocApiUpdateCommandExecutor openplatformDocApiUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocApiVO> create(OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand) {
		return openplatformDocApiCreateCommandExecutor.execute(openplatformDocApiCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiVO> delete(IdCommand deleteCommand) {
		return openplatformDocApiDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiVO> update(OpenplatformDocApiUpdateCommand openplatformDocApiUpdateCommand) {
		return openplatformDocApiUpdateCommandExecutor.execute(openplatformDocApiUpdateCommand);
	}

	@Autowired
	public void setOpenplatformDocApiCreateCommandExecutor(OpenplatformDocApiCreateCommandExecutor openplatformDocApiCreateCommandExecutor) {
		this.openplatformDocApiCreateCommandExecutor = openplatformDocApiCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocApiDeleteCommandExecutor(OpenplatformDocApiDeleteCommandExecutor openplatformDocApiDeleteCommandExecutor) {
		this.openplatformDocApiDeleteCommandExecutor = openplatformDocApiDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocApiUpdateCommandExecutor(OpenplatformDocApiUpdateCommandExecutor openplatformDocApiUpdateCommandExecutor) {
		this.openplatformDocApiUpdateCommandExecutor = openplatformDocApiUpdateCommandExecutor;
	}

}
