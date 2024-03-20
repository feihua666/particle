package com.particle.openplatform.app.doc.api.impl;

import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocTemplateCreateCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocTemplateDeleteCommandExecutor;
import com.particle.openplatform.app.doc.executor.OpenplatformDocApiDocTemplateUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateUpdateCommand;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
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
 * 开放接口文档模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformDocApiDocTemplateApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocTemplateApplicationService {

	private OpenplatformDocApiDocTemplateCreateCommandExecutor openplatformDocApiDocTemplateCreateCommandExecutor;

	private OpenplatformDocApiDocTemplateDeleteCommandExecutor openplatformDocApiDocTemplateDeleteCommandExecutor;

	private OpenplatformDocApiDocTemplateUpdateCommandExecutor openplatformDocApiDocTemplateUpdateCommandExecutor;


	@Override
	public SingleResponse<OpenplatformDocApiDocTemplateVO> create(OpenplatformDocApiDocTemplateCreateCommand openplatformDocApiDocTemplateCreateCommand) {
		return openplatformDocApiDocTemplateCreateCommandExecutor.execute(openplatformDocApiDocTemplateCreateCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocTemplateVO> delete(IdCommand deleteCommand) {
		return openplatformDocApiDocTemplateDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<OpenplatformDocApiDocTemplateVO> update(OpenplatformDocApiDocTemplateUpdateCommand openplatformDocApiDocTemplateUpdateCommand) {
		return openplatformDocApiDocTemplateUpdateCommandExecutor.execute(openplatformDocApiDocTemplateUpdateCommand);
	}

	@Autowired
	public void setOpenplatformDocApiDocTemplateCreateCommandExecutor(OpenplatformDocApiDocTemplateCreateCommandExecutor openplatformDocApiDocTemplateCreateCommandExecutor) {
		this.openplatformDocApiDocTemplateCreateCommandExecutor = openplatformDocApiDocTemplateCreateCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDocApiDocTemplateDeleteCommandExecutor(OpenplatformDocApiDocTemplateDeleteCommandExecutor openplatformDocApiDocTemplateDeleteCommandExecutor) {
		this.openplatformDocApiDocTemplateDeleteCommandExecutor = openplatformDocApiDocTemplateDeleteCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDocApiDocTemplateUpdateCommandExecutor(OpenplatformDocApiDocTemplateUpdateCommandExecutor openplatformDocApiDocTemplateUpdateCommandExecutor) {
		this.openplatformDocApiDocTemplateUpdateCommandExecutor = openplatformDocApiDocTemplateUpdateCommandExecutor;
	}

}
