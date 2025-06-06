package com.particle.openplatform.app.doc.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocTemplateParamFieldCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocTemplateParamFieldDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocTemplateParamFieldUpdateCommandExecutor;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateParamFieldApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldConditionDeleteCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldParseAndCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放接口文档模板参数字段 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocApiDocTemplateParamFieldApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocTemplateParamFieldApplicationService {

	private OpenplatformDocApiDocTemplateParamFieldCreateCommandExecutor openplatformDocApiDocTemplateParamFieldCreateCommandExecutor;

	private OpenplatformDocApiDocTemplateParamFieldDeleteCommandExecutor openplatformDocApiDocTemplateParamFieldDeleteCommandExecutor;

	private OpenplatformDocApiDocTemplateParamFieldUpdateCommandExecutor openplatformDocApiDocTemplateParamFieldUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> create(OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand) {
		return openplatformDocApiDocTemplateParamFieldCreateCommandExecutor.execute(openplatformDocApiDocTemplateParamFieldCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> delete(IdCommand deleteCommand) {
		return openplatformDocApiDocTemplateParamFieldDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> update(OpenplatformDocApiDocTemplateParamFieldUpdateCommand openplatformDocApiDocTemplateParamFieldUpdateCommand) {
		return openplatformDocApiDocTemplateParamFieldUpdateCommandExecutor.execute(openplatformDocApiDocTemplateParamFieldUpdateCommand);
	}

	@Override
	public Response parseAndCreate(OpenplatformDocApiDocTemplateParamFieldParseAndCreateCommand command) {
		return openplatformDocApiDocTemplateParamFieldCreateCommandExecutor.parseAndCreate(command);
	}

	@Override
	public Response conditionDelete(OpenplatformDocApiDocTemplateParamFieldConditionDeleteCommand command) {
		return openplatformDocApiDocTemplateParamFieldDeleteCommandExecutor.conditionDelete(command);
	}

	@Autowired
	public void setOpenplatformDocApiDocTemplateParamFieldCreateCommandExecutor(OpenplatformDocApiDocTemplateParamFieldCreateCommandExecutor openplatformDocApiDocTemplateParamFieldCreateCommandExecutor) {
		this.openplatformDocApiDocTemplateParamFieldCreateCommandExecutor = openplatformDocApiDocTemplateParamFieldCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocApiDocTemplateParamFieldDeleteCommandExecutor(OpenplatformDocApiDocTemplateParamFieldDeleteCommandExecutor openplatformDocApiDocTemplateParamFieldDeleteCommandExecutor) {
		this.openplatformDocApiDocTemplateParamFieldDeleteCommandExecutor = openplatformDocApiDocTemplateParamFieldDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocApiDocTemplateParamFieldUpdateCommandExecutor(OpenplatformDocApiDocTemplateParamFieldUpdateCommandExecutor openplatformDocApiDocTemplateParamFieldUpdateCommandExecutor) {
		this.openplatformDocApiDocTemplateParamFieldUpdateCommandExecutor = openplatformDocApiDocTemplateParamFieldUpdateCommandExecutor;
	}

}
