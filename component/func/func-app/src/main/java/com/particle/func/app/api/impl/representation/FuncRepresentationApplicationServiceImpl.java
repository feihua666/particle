package com.particle.func.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.func.app.executor.representation.FuncQueryCommandExecutor;
import com.particle.func.client.api.representation.IFuncRepresentationApplicationService;
import com.particle.func.client.dto.command.representation.FuncPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncQueryDetailCommand;
import com.particle.func.client.dto.command.representation.FuncQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class FuncRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncRepresentationApplicationService {

	private FuncQueryCommandExecutor funcQueryCommandExecutor;


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
	public void setFuncQueryCommandExecutor(FuncQueryCommandExecutor funcQueryCommandExecutor) {
		this.funcQueryCommandExecutor = funcQueryCommandExecutor;
	}
}
