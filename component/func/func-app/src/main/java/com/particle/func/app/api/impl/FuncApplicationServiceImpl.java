package com.particle.func.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.executor.FuncCreateCommandExecutor;
import com.particle.func.app.executor.FuncDeleteCommandExecutor;
import com.particle.func.app.executor.FuncUpdateCommandExecutor;
import com.particle.func.client.api.IFuncApplicationService;
import com.particle.func.client.dto.command.FuncCreateCommand;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 菜单功能 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Transactional
@Service
@CatchAndLog
public class FuncApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncApplicationService {

	private FuncCreateCommandExecutor funcCreateCommandExecutor;

	private FuncDeleteCommandExecutor funcDeleteCommandExecutor;

	private FuncUpdateCommandExecutor funcUpdateCommandExecutor;


	@Transactional
	@Override
	public SingleResponse<FuncVO> create(FuncCreateCommand funcCreateCommand) {
		return funcCreateCommandExecutor.execute(funcCreateCommand);
	}

	@Transactional
	@Override
	public SingleResponse<FuncVO> delete(IdCommand funcDeleteCommand) {
		return funcDeleteCommandExecutor.execute(funcDeleteCommand);
	}

	@Transactional
	@Override
	public SingleResponse<FuncVO> update(FuncUpdateCommand funcUpdateCommand) {
		return funcUpdateCommandExecutor.execute(funcUpdateCommand);
	}

	@Autowired
	public void setFuncCreateCommandExecutor(FuncCreateCommandExecutor funcCreateCommandExecutor) {
		this.funcCreateCommandExecutor = funcCreateCommandExecutor;
	}

	@Autowired
	public void setFuncDeleteCommandExecutor(FuncDeleteCommandExecutor funcDeleteCommandExecutor) {
		this.funcDeleteCommandExecutor = funcDeleteCommandExecutor;
	}
	@Autowired
	public void setFuncUpdateCommandExecutor(FuncUpdateCommandExecutor funcUpdateCommandExecutor) {
		this.funcUpdateCommandExecutor = funcUpdateCommandExecutor;
	}

}
