package com.particle.func.app.api.impl;

import com.particle.func.app.executor.FuncCreateCommandExecutor;
import com.particle.func.app.executor.FuncDeleteCommandExecutor;
import com.particle.func.app.executor.FuncUpdateCommandExecutor;
import com.particle.func.app.executor.FuncQueryCommandExecutor;
import com.particle.func.client.dto.command.FuncDeleteCommand;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.command.FuncQueryDetailCommand;
import com.particle.func.client.dto.command.FuncQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.FuncPageQueryCommand;
import com.particle.func.client.api.IFuncApplicationService;
import com.particle.func.client.dto.command.FuncCreateCommand;
import com.particle.func.client.dto.command.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 菜单功能 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
@CatchAndLog
public class FuncApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncApplicationService {

	private FuncCreateCommandExecutor funcCreateCommandExecutor;

	private FuncDeleteCommandExecutor funcDeleteCommandExecutor;

	private FuncUpdateCommandExecutor funcUpdateCommandExecutor;

	private FuncQueryCommandExecutor funcQueryCommandExecutor;

	@Override
	public SingleResponse<FuncVO> create(FuncCreateCommand funcCreateCommand) {
		return funcCreateCommandExecutor.execute(funcCreateCommand);
	}

	@Override
	public SingleResponse<FuncVO> delete(FuncDeleteCommand funcDeleteCommand) {
		return funcDeleteCommandExecutor.execute(funcDeleteCommand);
	}

	@Override
	public SingleResponse<FuncVO> update(FuncUpdateCommand funcUpdateCommand) {
		return funcUpdateCommandExecutor.execute(funcUpdateCommand);
	}

	@Override
	public SingleResponse<FuncVO> queryDetail(FuncQueryDetailCommand funcQueryDetailCommand) {
		return funcQueryCommandExecutor.execute(funcQueryDetailCommand);
	}

	@Override
	public SingleResponse<FuncVO> queryDetailForUpdate(FuncQueryDetailForUpdateCommand funcQueryDetailForUpdateCommand) {
		return funcQueryCommandExecutor.execute(funcQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<FuncVO> pageQuery(FuncPageQueryCommand funcPageQueryCommand) {
		return funcQueryCommandExecutor.execute(funcPageQueryCommand);
	}

	@Override
	public MultiResponse<FuncVO> queryList(FuncQueryListCommand funcQueryListCommand) {
		return funcQueryCommandExecutor.execute(funcQueryListCommand);
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
	@Autowired
	public void setFuncQueryCommandExecutor(FuncQueryCommandExecutor funcQueryCommandExecutor) {
		this.funcQueryCommandExecutor = funcQueryCommandExecutor;
	}
}
