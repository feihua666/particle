package com.particle.func.app.funcapplicationfuncrel.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.funcapplicationfuncrel.executor.FuncApplicationFuncRelCommandExecutor;
import com.particle.func.app.funcapplicationfuncrel.executor.FuncApplicationFuncRelCreateCommandExecutor;
import com.particle.func.app.funcapplicationfuncrel.executor.FuncApplicationFuncRelDeleteCommandExecutor;
import com.particle.func.client.funcapplicationfuncrel.api.IFuncApplicationFuncRelApplicationService;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncApplicationAssignFuncCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncApplicationFuncRelCreateCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncAssignFuncApplicationCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 功能应用功能关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Transactional
@Service
@CatchAndLog
public class FuncApplicationFuncRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncApplicationFuncRelApplicationService {

	private FuncApplicationFuncRelCreateCommandExecutor funcApplicationFuncRelCreateCommandExecutor;

	private FuncApplicationFuncRelDeleteCommandExecutor funcApplicationFuncRelDeleteCommandExecutor;

	private FuncApplicationFuncRelCommandExecutor funcApplicationFuncRelCommandExecutor;


	@Override
	public SingleResponse<FuncApplicationFuncRelVO> create(FuncApplicationFuncRelCreateCommand funcApplicationFuncRelCreateCommand) {
		return funcApplicationFuncRelCreateCommandExecutor.execute(funcApplicationFuncRelCreateCommand);
	}

	@Override
	public SingleResponse<FuncApplicationFuncRelVO> delete(IdCommand deleteCommand) {
		return funcApplicationFuncRelDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public Response funcAssignFuncApplication(FuncAssignFuncApplicationCommand cf) {
		return funcApplicationFuncRelCommandExecutor.funcAssignFuncApplication(cf);
	}

	@Override
	public Response funcApplicationAssignFunc(FuncApplicationAssignFuncCommand cf) {
		return funcApplicationFuncRelCommandExecutor.funcApplicationAssignFunc(cf);
	}

	@Override
	public Response deleteByFuncId(IdCommand funcIdCommand) {
		return funcApplicationFuncRelDeleteCommandExecutor.deleteByFuncId(funcIdCommand);
	}

	@Override
	public Response deleteByFuncApplicationId(IdCommand funcApplicationIdCommand) {
		return funcApplicationFuncRelDeleteCommandExecutor.deleteByFuncApplicationId(funcApplicationIdCommand);
	}

	@Autowired
	public void setFuncApplicationFuncRelCreateCommandExecutor(FuncApplicationFuncRelCreateCommandExecutor funcApplicationFuncRelCreateCommandExecutor) {
		this.funcApplicationFuncRelCreateCommandExecutor = funcApplicationFuncRelCreateCommandExecutor;
	}

	@Autowired
	public void setFuncApplicationFuncRelDeleteCommandExecutor(FuncApplicationFuncRelDeleteCommandExecutor funcApplicationFuncRelDeleteCommandExecutor) {
		this.funcApplicationFuncRelDeleteCommandExecutor = funcApplicationFuncRelDeleteCommandExecutor;
	}

	@Autowired
	public void setFuncApplicationFuncRelCommandExecutor(FuncApplicationFuncRelCommandExecutor funcApplicationFuncRelCommandExecutor) {
		this.funcApplicationFuncRelCommandExecutor = funcApplicationFuncRelCommandExecutor;
	}
}
