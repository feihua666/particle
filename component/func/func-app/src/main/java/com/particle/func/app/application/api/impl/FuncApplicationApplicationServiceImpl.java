package com.particle.func.app.application.api.impl;

import com.particle.func.app.application.executor.FuncApplicationCreateCommandExecutor;
import com.particle.func.app.application.executor.FuncApplicationDeleteCommandExecutor;
import com.particle.func.app.application.executor.FuncApplicationUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.application.dto.command.FuncApplicationUpdateCommand;
import com.particle.func.client.application.api.IFuncApplicationApplicationService;
import com.particle.func.client.application.dto.command.FuncApplicationCreateCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
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
 * 功能应用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Transactional
@Service
@CatchAndLog
public class FuncApplicationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncApplicationApplicationService {

	private FuncApplicationCreateCommandExecutor funcApplicationCreateCommandExecutor;

	private FuncApplicationDeleteCommandExecutor funcApplicationDeleteCommandExecutor;

	private FuncApplicationUpdateCommandExecutor funcApplicationUpdateCommandExecutor;


	@Override
	public SingleResponse<FuncApplicationVO> create(FuncApplicationCreateCommand funcApplicationCreateCommand) {
		return funcApplicationCreateCommandExecutor.execute(funcApplicationCreateCommand);
	}

	@Override
	public SingleResponse<FuncApplicationVO> delete(IdCommand deleteCommand) {
		return funcApplicationDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<FuncApplicationVO> update(FuncApplicationUpdateCommand funcApplicationUpdateCommand) {
		return funcApplicationUpdateCommandExecutor.execute(funcApplicationUpdateCommand);
	}

	@Autowired
	public void setFuncApplicationCreateCommandExecutor(FuncApplicationCreateCommandExecutor funcApplicationCreateCommandExecutor) {
		this.funcApplicationCreateCommandExecutor = funcApplicationCreateCommandExecutor;
	}

	@Autowired
	public void setFuncApplicationDeleteCommandExecutor(FuncApplicationDeleteCommandExecutor funcApplicationDeleteCommandExecutor) {
		this.funcApplicationDeleteCommandExecutor = funcApplicationDeleteCommandExecutor;
	}
	@Autowired
	public void setFuncApplicationUpdateCommandExecutor(FuncApplicationUpdateCommandExecutor funcApplicationUpdateCommandExecutor) {
		this.funcApplicationUpdateCommandExecutor = funcApplicationUpdateCommandExecutor;
	}

}
