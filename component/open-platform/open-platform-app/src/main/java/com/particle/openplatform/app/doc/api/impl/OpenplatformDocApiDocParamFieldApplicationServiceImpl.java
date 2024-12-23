package com.particle.openplatform.app.doc.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocParamFieldCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocParamFieldDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocParamFieldUpdateCommandExecutor;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocParamFieldApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldParseAndCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口文档参数字段 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocApiDocParamFieldApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocParamFieldApplicationService {

	private OpenplatformDocApiDocParamFieldCreateCommandExecutor openplatformDocApiDocParamFieldCreateCommandExecutor;

	private OpenplatformDocApiDocParamFieldDeleteCommandExecutor openplatformDocApiDocParamFieldDeleteCommandExecutor;

	private OpenplatformDocApiDocParamFieldUpdateCommandExecutor openplatformDocApiDocParamFieldUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> create(OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand) {
		return openplatformDocApiDocParamFieldCreateCommandExecutor.execute(openplatformDocApiDocParamFieldCreateCommand);
	}


	@Override
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> delete(IdCommand deleteCommand) {
		return openplatformDocApiDocParamFieldDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> update(OpenplatformDocApiDocParamFieldUpdateCommand openplatformDocApiDocParamFieldUpdateCommand) {
		return openplatformDocApiDocParamFieldUpdateCommandExecutor.execute(openplatformDocApiDocParamFieldUpdateCommand);
	}

	@Override
	public Response parseAndCreate(OpenplatformDocApiDocParamFieldParseAndCreateCommand command) {
		return openplatformDocApiDocParamFieldCreateCommandExecutor.parseAndCreate(command);
	}
	@Autowired
	public void setOpenplatformDocApiDocParamFieldCreateCommandExecutor(OpenplatformDocApiDocParamFieldCreateCommandExecutor openplatformDocApiDocParamFieldCreateCommandExecutor) {
		this.openplatformDocApiDocParamFieldCreateCommandExecutor = openplatformDocApiDocParamFieldCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocApiDocParamFieldDeleteCommandExecutor(OpenplatformDocApiDocParamFieldDeleteCommandExecutor openplatformDocApiDocParamFieldDeleteCommandExecutor) {
		this.openplatformDocApiDocParamFieldDeleteCommandExecutor = openplatformDocApiDocParamFieldDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocApiDocParamFieldUpdateCommandExecutor(OpenplatformDocApiDocParamFieldUpdateCommandExecutor openplatformDocApiDocParamFieldUpdateCommandExecutor) {
		this.openplatformDocApiDocParamFieldUpdateCommandExecutor = openplatformDocApiDocParamFieldUpdateCommandExecutor;
	}

}
