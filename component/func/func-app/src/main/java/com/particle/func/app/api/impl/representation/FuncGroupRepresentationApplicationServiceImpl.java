package com.particle.func.app.api.impl.representation;

import com.particle.func.app.executor.representation.FuncGroupQueryCommandExecutor;
import com.particle.func.client.dto.command.FuncGroupDeleteCommand;
import com.particle.func.client.dto.command.FuncGroupUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryDetailCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncGroupPageQueryCommand;
import com.particle.func.client.api.representation.IFuncGroupRepresentationApplicationService;
import com.particle.func.client.dto.command.FuncGroupCreateCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryListCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
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
 * 功能组 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Service
@CatchAndLog
public class FuncGroupRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IFuncGroupRepresentationApplicationService {

	private FuncGroupQueryCommandExecutor funcGroupQueryCommandExecutor;

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
	public void setFuncGroupQueryCommandExecutor(FuncGroupQueryCommandExecutor funcGroupQueryCommandExecutor) {
		this.funcGroupQueryCommandExecutor = funcGroupQueryCommandExecutor;
	}
}
