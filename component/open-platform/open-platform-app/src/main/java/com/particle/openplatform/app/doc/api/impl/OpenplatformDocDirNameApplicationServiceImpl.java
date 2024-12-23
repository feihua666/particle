package com.particle.openplatform.app.doc.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.OpenplatformDocDirNameCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocDirNameDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocDirNameUpdateCommandExecutor;
import com.particle.openplatform.client.doc.api.IOpenplatformDocDirNameApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirNameCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirNameUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口目录名称 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocDirNameApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocDirNameApplicationService {

	private OpenplatformDocDirNameCreateCommandExecutor openplatformDocDirNameCreateCommandExecutor;

	private OpenplatformDocDirNameDeleteCommandExecutor openplatformDocDirNameDeleteCommandExecutor;

	private OpenplatformDocDirNameUpdateCommandExecutor openplatformDocDirNameUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocDirNameVO> create(OpenplatformDocDirNameCreateCommand openplatformDocDirNameCreateCommand) {
		return openplatformDocDirNameCreateCommandExecutor.execute(openplatformDocDirNameCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocDirNameVO> delete(IdCommand deleteCommand) {
		return openplatformDocDirNameDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocDirNameVO> update(OpenplatformDocDirNameUpdateCommand openplatformDocDirNameUpdateCommand) {
		return openplatformDocDirNameUpdateCommandExecutor.execute(openplatformDocDirNameUpdateCommand);
	}

	@Autowired
	public void setOpenplatformDocDirNameCreateCommandExecutor(OpenplatformDocDirNameCreateCommandExecutor openplatformDocDirNameCreateCommandExecutor) {
		this.openplatformDocDirNameCreateCommandExecutor = openplatformDocDirNameCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocDirNameDeleteCommandExecutor(OpenplatformDocDirNameDeleteCommandExecutor openplatformDocDirNameDeleteCommandExecutor) {
		this.openplatformDocDirNameDeleteCommandExecutor = openplatformDocDirNameDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocDirNameUpdateCommandExecutor(OpenplatformDocDirNameUpdateCommandExecutor openplatformDocDirNameUpdateCommandExecutor) {
		this.openplatformDocDirNameUpdateCommandExecutor = openplatformDocDirNameUpdateCommandExecutor;
	}

}
