package com.particle.func.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.executor.FuncGroupCreateCommandExecutor;
import com.particle.func.app.executor.FuncGroupDeleteCommandExecutor;
import com.particle.func.app.executor.FuncGroupUpdateCommandExecutor;
import com.particle.func.client.api.IFuncGroupApplicationService;
import com.particle.func.client.dto.command.FuncGroupCreateCommand;
import com.particle.func.client.dto.command.FuncGroupUpdateCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 功能组 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Transactional
@Service
@CatchAndLog
public class FuncGroupApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncGroupApplicationService {

	private FuncGroupCreateCommandExecutor funcGroupCreateCommandExecutor;

	private FuncGroupDeleteCommandExecutor funcGroupDeleteCommandExecutor;

	private FuncGroupUpdateCommandExecutor funcGroupUpdateCommandExecutor;


	@Override
	public SingleResponse<FuncGroupVO> create(FuncGroupCreateCommand funcGroupCreateCommand) {
		return funcGroupCreateCommandExecutor.execute(funcGroupCreateCommand);
	}

	@Override
	public SingleResponse<FuncGroupVO> delete(IdCommand funcGroupDeleteCommand) {
		return funcGroupDeleteCommandExecutor.execute(funcGroupDeleteCommand);
	}

	@Override
	public SingleResponse<FuncGroupVO> update(FuncGroupUpdateCommand funcGroupUpdateCommand) {
		return funcGroupUpdateCommandExecutor.execute(funcGroupUpdateCommand);
	}

	@Autowired
	public void setFuncGroupCreateCommandExecutor(FuncGroupCreateCommandExecutor funcGroupCreateCommandExecutor) {
		this.funcGroupCreateCommandExecutor = funcGroupCreateCommandExecutor;
	}

	@Autowired
	public void setFuncGroupDeleteCommandExecutor(FuncGroupDeleteCommandExecutor funcGroupDeleteCommandExecutor) {
		this.funcGroupDeleteCommandExecutor = funcGroupDeleteCommandExecutor;
	}
	@Autowired
	public void setFuncGroupUpdateCommandExecutor(FuncGroupUpdateCommandExecutor funcGroupUpdateCommandExecutor) {
		this.funcGroupUpdateCommandExecutor = funcGroupUpdateCommandExecutor;
	}

}
