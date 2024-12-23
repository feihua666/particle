package com.particle.openplatform.app.doc.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocUpdateCommandExecutor;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口文档 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocApiDocApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocApplicationService {

	private OpenplatformDocApiDocCreateCommandExecutor openplatformDocApiDocCreateCommandExecutor;

	private OpenplatformDocApiDocDeleteCommandExecutor openplatformDocApiDocDeleteCommandExecutor;

	private OpenplatformDocApiDocUpdateCommandExecutor openplatformDocApiDocUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocApiDocVO> create(OpenplatformDocApiDocCreateCommand openplatformDocApiDocCreateCommand) {
		return openplatformDocApiDocCreateCommandExecutor.execute(openplatformDocApiDocCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocVO> delete(IdCommand deleteCommand) {
		return openplatformDocApiDocDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocVO> update(OpenplatformDocApiDocUpdateCommand openplatformDocApiDocUpdateCommand) {
		return openplatformDocApiDocUpdateCommandExecutor.execute(openplatformDocApiDocUpdateCommand);
	}

	@Autowired
	public void setOpenplatformDocApiDocCreateCommandExecutor(OpenplatformDocApiDocCreateCommandExecutor openplatformDocApiDocCreateCommandExecutor) {
		this.openplatformDocApiDocCreateCommandExecutor = openplatformDocApiDocCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocApiDocDeleteCommandExecutor(OpenplatformDocApiDocDeleteCommandExecutor openplatformDocApiDocDeleteCommandExecutor) {
		this.openplatformDocApiDocDeleteCommandExecutor = openplatformDocApiDocDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocApiDocUpdateCommandExecutor(OpenplatformDocApiDocUpdateCommandExecutor openplatformDocApiDocUpdateCommandExecutor) {
		this.openplatformDocApiDocUpdateCommandExecutor = openplatformDocApiDocUpdateCommandExecutor;
	}

}
