package com.particle.openplatform.app.doc.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocExampleCodeCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocExampleCodeDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocExampleCodeUpdateCommandExecutor;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocExampleCodeApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocExampleCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocExampleCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口文档示例代码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocApiDocExampleCodeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocExampleCodeApplicationService {

	private OpenplatformDocApiDocExampleCodeCreateCommandExecutor openplatformDocApiDocExampleCodeCreateCommandExecutor;

	private OpenplatformDocApiDocExampleCodeDeleteCommandExecutor openplatformDocApiDocExampleCodeDeleteCommandExecutor;

	private OpenplatformDocApiDocExampleCodeUpdateCommandExecutor openplatformDocApiDocExampleCodeUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> create(OpenplatformDocApiDocExampleCodeCreateCommand openplatformDocApiDocExampleCodeCreateCommand) {
		return openplatformDocApiDocExampleCodeCreateCommandExecutor.execute(openplatformDocApiDocExampleCodeCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> delete(IdCommand deleteCommand) {
		return openplatformDocApiDocExampleCodeDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> update(OpenplatformDocApiDocExampleCodeUpdateCommand openplatformDocApiDocExampleCodeUpdateCommand) {
		return openplatformDocApiDocExampleCodeUpdateCommandExecutor.execute(openplatformDocApiDocExampleCodeUpdateCommand);
	}

	@Autowired
	public void setOpenplatformDocApiDocExampleCodeCreateCommandExecutor(OpenplatformDocApiDocExampleCodeCreateCommandExecutor openplatformDocApiDocExampleCodeCreateCommandExecutor) {
		this.openplatformDocApiDocExampleCodeCreateCommandExecutor = openplatformDocApiDocExampleCodeCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocApiDocExampleCodeDeleteCommandExecutor(OpenplatformDocApiDocExampleCodeDeleteCommandExecutor openplatformDocApiDocExampleCodeDeleteCommandExecutor) {
		this.openplatformDocApiDocExampleCodeDeleteCommandExecutor = openplatformDocApiDocExampleCodeDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocApiDocExampleCodeUpdateCommandExecutor(OpenplatformDocApiDocExampleCodeUpdateCommandExecutor openplatformDocApiDocExampleCodeUpdateCommandExecutor) {
		this.openplatformDocApiDocExampleCodeUpdateCommandExecutor = openplatformDocApiDocExampleCodeUpdateCommandExecutor;
	}

}
