package com.particle.func.app.api.impl;

import com.particle.func.app.executor.FuncGroupCreateCommandExecutor;
import com.particle.func.app.executor.FuncGroupDeleteCommandExecutor;
import com.particle.func.app.executor.FuncGroupUpdateCommandExecutor;
import com.particle.func.app.executor.FuncGroupQueryCommandExecutor;
import com.particle.func.client.dto.command.FuncGroupDeleteCommand;
import com.particle.func.client.dto.command.FuncGroupUpdateCommand;
import com.particle.func.client.dto.command.FuncGroupQueryDetailCommand;
import com.particle.func.client.dto.command.FuncGroupQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.FuncGroupPageQueryCommand;
import com.particle.func.client.api.IFuncGroupApplicationService;
import com.particle.func.client.dto.command.FuncGroupCreateCommand;
import com.particle.func.client.dto.command.FuncGroupQueryListCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 功能组 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
@CatchAndLog
public class FuncGroupApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncGroupApplicationService {

	private FuncGroupCreateCommandExecutor funcGroupCreateCommandExecutor;

	private FuncGroupDeleteCommandExecutor funcGroupDeleteCommandExecutor;

	private FuncGroupUpdateCommandExecutor funcGroupUpdateCommandExecutor;

	private FuncGroupQueryCommandExecutor funcGroupQueryCommandExecutor;

	@Override
	public SingleResponse<FuncGroupVO> create(FuncGroupCreateCommand funcGroupCreateCommand) {
		return funcGroupCreateCommandExecutor.execute(funcGroupCreateCommand);
	}

	@Override
	public SingleResponse<FuncGroupVO> delete(FuncGroupDeleteCommand funcGroupDeleteCommand) {
		return funcGroupDeleteCommandExecutor.execute(funcGroupDeleteCommand);
	}

	@Override
	public SingleResponse<FuncGroupVO> update(FuncGroupUpdateCommand funcGroupUpdateCommand) {
		return funcGroupUpdateCommandExecutor.execute(funcGroupUpdateCommand);
	}

	@Override
	public SingleResponse<FuncGroupVO> queryDetail(FuncGroupQueryDetailCommand funcGroupQueryDetailCommand) {
		return funcGroupQueryCommandExecutor.execute(funcGroupQueryDetailCommand);
	}

	@Override
	public SingleResponse<FuncGroupVO> queryDetailForUpdate(FuncGroupQueryDetailForUpdateCommand funcGroupQueryDetailForUpdateCommand) {
		return funcGroupQueryCommandExecutor.execute(funcGroupQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<FuncGroupVO> pageQuery(FuncGroupPageQueryCommand funcGroupPageQueryCommand) {
		return funcGroupQueryCommandExecutor.execute(funcGroupPageQueryCommand);
	}

	@Override
	public MultiResponse<FuncGroupVO> queryList(FuncGroupQueryListCommand funcGroupQueryListCommand) {
		return funcGroupQueryCommandExecutor.execute(funcGroupQueryListCommand);
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
	@Autowired
	public void setFuncGroupQueryCommandExecutor(FuncGroupQueryCommandExecutor funcGroupQueryCommandExecutor) {
		this.funcGroupQueryCommandExecutor = funcGroupQueryCommandExecutor;
	}
}
