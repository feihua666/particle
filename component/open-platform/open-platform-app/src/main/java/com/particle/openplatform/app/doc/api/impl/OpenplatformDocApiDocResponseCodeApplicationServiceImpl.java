package com.particle.openplatform.app.doc.api.impl;

import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocResponseCodeCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocResponseCodeDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocResponseCodeUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocResponseCodeUpdateCommand;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocResponseCodeApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocResponseCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;
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
 * 开放接口文档响应码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocApiDocResponseCodeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocResponseCodeApplicationService {

	private OpenplatformDocApiDocResponseCodeCreateCommandExecutor openplatformDocApiDocResponseCodeCreateCommandExecutor;

	private OpenplatformDocApiDocResponseCodeDeleteCommandExecutor openplatformDocApiDocResponseCodeDeleteCommandExecutor;

	private OpenplatformDocApiDocResponseCodeUpdateCommandExecutor openplatformDocApiDocResponseCodeUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> create(OpenplatformDocApiDocResponseCodeCreateCommand openplatformDocApiDocResponseCodeCreateCommand) {
		return openplatformDocApiDocResponseCodeCreateCommandExecutor.execute(openplatformDocApiDocResponseCodeCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> delete(IdCommand deleteCommand) {
		return openplatformDocApiDocResponseCodeDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> update(OpenplatformDocApiDocResponseCodeUpdateCommand openplatformDocApiDocResponseCodeUpdateCommand) {
		return openplatformDocApiDocResponseCodeUpdateCommandExecutor.execute(openplatformDocApiDocResponseCodeUpdateCommand);
	}

	@Autowired
	public void setOpenplatformDocApiDocResponseCodeCreateCommandExecutor(OpenplatformDocApiDocResponseCodeCreateCommandExecutor openplatformDocApiDocResponseCodeCreateCommandExecutor) {
		this.openplatformDocApiDocResponseCodeCreateCommandExecutor = openplatformDocApiDocResponseCodeCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocApiDocResponseCodeDeleteCommandExecutor(OpenplatformDocApiDocResponseCodeDeleteCommandExecutor openplatformDocApiDocResponseCodeDeleteCommandExecutor) {
		this.openplatformDocApiDocResponseCodeDeleteCommandExecutor = openplatformDocApiDocResponseCodeDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocApiDocResponseCodeUpdateCommandExecutor(OpenplatformDocApiDocResponseCodeUpdateCommandExecutor openplatformDocApiDocResponseCodeUpdateCommandExecutor) {
		this.openplatformDocApiDocResponseCodeUpdateCommandExecutor = openplatformDocApiDocResponseCodeUpdateCommandExecutor;
	}

}
