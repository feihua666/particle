package com.particle.lowcode.app.generator.api.impl;

import com.particle.global.dto.response.Response;
import com.particle.lowcode.app.generator.executor.LowcodeModelCreateCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeModelDeleteCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeModelItemCreateByModelIdCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeModelUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateByModelIdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelUpdateCommand;
import com.particle.lowcode.client.generator.api.ILowcodeModelApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 低代码模型 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Transactional
@Service
@CatchAndLog
public class LowcodeModelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeModelApplicationService {

	private LowcodeModelCreateCommandExecutor lowcodeModelCreateCommandExecutor;

	private LowcodeModelDeleteCommandExecutor lowcodeModelDeleteCommandExecutor;

	private LowcodeModelUpdateCommandExecutor lowcodeModelUpdateCommandExecutor;

	private LowcodeModelItemCreateByModelIdCommandExecutor lowcodeModelItemCreateByModelIdCommandExecutor;

	@Override
	public SingleResponse<LowcodeModelVO> create(LowcodeModelCreateCommand lowcodeModelCreateCommand) {
		return lowcodeModelCreateCommandExecutor.execute(lowcodeModelCreateCommand);
	}

	@Override
	public Response loadByModelAndDatasource(LowcodeModelItemCreateByModelIdCommand idCommand) {
		return lowcodeModelItemCreateByModelIdCommandExecutor.execute(idCommand);
	}

	@Override
	public SingleResponse<LowcodeModelVO> delete(IdCommand deleteCommand) {
		return lowcodeModelDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<LowcodeModelVO> update(LowcodeModelUpdateCommand lowcodeModelUpdateCommand) {
		return lowcodeModelUpdateCommandExecutor.execute(lowcodeModelUpdateCommand);
	}

	@Autowired
	public void setLowcodeModelCreateCommandExecutor(LowcodeModelCreateCommandExecutor lowcodeModelCreateCommandExecutor) {
		this.lowcodeModelCreateCommandExecutor = lowcodeModelCreateCommandExecutor;
	}

	@Autowired
	public void setLowcodeModelDeleteCommandExecutor(LowcodeModelDeleteCommandExecutor lowcodeModelDeleteCommandExecutor) {
		this.lowcodeModelDeleteCommandExecutor = lowcodeModelDeleteCommandExecutor;
	}
	@Autowired
	public void setLowcodeModelUpdateCommandExecutor(LowcodeModelUpdateCommandExecutor lowcodeModelUpdateCommandExecutor) {
		this.lowcodeModelUpdateCommandExecutor = lowcodeModelUpdateCommandExecutor;
	}

	@Autowired
	public void setLowcodeModelItemCreateByModelIdCommandExecutor(LowcodeModelItemCreateByModelIdCommandExecutor lowcodeModelItemCreateByModelIdCommandExecutor) {
		this.lowcodeModelItemCreateByModelIdCommandExecutor = lowcodeModelItemCreateByModelIdCommandExecutor;
	}
}
