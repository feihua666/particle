package com.particle.openplatform.app.doc.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDirRelCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDirRelDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDirRelUpdateCommandExecutor;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDirRelApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDirRelCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDirRelUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口文档接口与目录关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocApiDirRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDirRelApplicationService {

	private OpenplatformDocApiDirRelCreateCommandExecutor openplatformDocApiDirRelCreateCommandExecutor;

	private OpenplatformDocApiDirRelDeleteCommandExecutor openplatformDocApiDirRelDeleteCommandExecutor;

	private OpenplatformDocApiDirRelUpdateCommandExecutor openplatformDocApiDirRelUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocApiDirRelVO> create(OpenplatformDocApiDirRelCreateCommand openplatformDocApiDirRelCreateCommand) {
		return openplatformDocApiDirRelCreateCommandExecutor.execute(openplatformDocApiDirRelCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDirRelVO> delete(IdCommand deleteCommand) {
		return openplatformDocApiDirRelDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDirRelVO> update(OpenplatformDocApiDirRelUpdateCommand openplatformDocApiDirRelUpdateCommand) {
		return openplatformDocApiDirRelUpdateCommandExecutor.execute(openplatformDocApiDirRelUpdateCommand);
	}

	@Autowired
	public void setOpenplatformDocApiDirRelCreateCommandExecutor(OpenplatformDocApiDirRelCreateCommandExecutor openplatformDocApiDirRelCreateCommandExecutor) {
		this.openplatformDocApiDirRelCreateCommandExecutor = openplatformDocApiDirRelCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocApiDirRelDeleteCommandExecutor(OpenplatformDocApiDirRelDeleteCommandExecutor openplatformDocApiDirRelDeleteCommandExecutor) {
		this.openplatformDocApiDirRelDeleteCommandExecutor = openplatformDocApiDirRelDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocApiDirRelUpdateCommandExecutor(OpenplatformDocApiDirRelUpdateCommandExecutor openplatformDocApiDirRelUpdateCommandExecutor) {
		this.openplatformDocApiDirRelUpdateCommandExecutor = openplatformDocApiDirRelUpdateCommandExecutor;
	}

}
