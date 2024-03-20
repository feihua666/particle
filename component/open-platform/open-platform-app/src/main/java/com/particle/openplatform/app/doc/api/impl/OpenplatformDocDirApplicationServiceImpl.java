package com.particle.openplatform.app.doc.api.impl;

import com.particle.openplatform.app.doc.executor.OpenplatformDocDirCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocDirDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocDirUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirUpdateCommand;
import com.particle.openplatform.client.doc.api.IOpenplatformDocDirApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
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
 * 开放接口目录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocDirApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocDirApplicationService {

	private OpenplatformDocDirCreateCommandExecutor openplatformDocDirCreateCommandExecutor;

	private OpenplatformDocDirDeleteCommandExecutor openplatformDocDirDeleteCommandExecutor;

	private OpenplatformDocDirUpdateCommandExecutor openplatformDocDirUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocDirVO> create(OpenplatformDocDirCreateCommand openplatformDocDirCreateCommand) {
		return openplatformDocDirCreateCommandExecutor.execute(openplatformDocDirCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocDirVO> delete(IdCommand deleteCommand) {
		return openplatformDocDirDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocDirVO> update(OpenplatformDocDirUpdateCommand openplatformDocDirUpdateCommand) {
		return openplatformDocDirUpdateCommandExecutor.execute(openplatformDocDirUpdateCommand);
	}

	@Autowired
	public void setOpenplatformDocDirCreateCommandExecutor(OpenplatformDocDirCreateCommandExecutor openplatformDocDirCreateCommandExecutor) {
		this.openplatformDocDirCreateCommandExecutor = openplatformDocDirCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocDirDeleteCommandExecutor(OpenplatformDocDirDeleteCommandExecutor openplatformDocDirDeleteCommandExecutor) {
		this.openplatformDocDirDeleteCommandExecutor = openplatformDocDirDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocDirUpdateCommandExecutor(OpenplatformDocDirUpdateCommandExecutor openplatformDocDirUpdateCommandExecutor) {
		this.openplatformDocDirUpdateCommandExecutor = openplatformDocDirUpdateCommandExecutor;
	}

}
