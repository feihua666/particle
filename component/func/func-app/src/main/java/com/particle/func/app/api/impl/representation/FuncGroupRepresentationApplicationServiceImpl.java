package com.particle.func.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.executor.representation.FuncGroupQueryCommandExecutor;
import com.particle.func.client.api.representation.IFuncGroupRepresentationApplicationService;
import com.particle.func.client.dto.command.representation.FuncGroupPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryListCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public SingleResponse<FuncGroupVO> queryDetail(IdCommand funcGroupQueryDetailCommand) {
		return funcGroupQueryCommandExecutor.executeDetail(funcGroupQueryDetailCommand);
	}

	@Override
	public SingleResponse<FuncGroupVO> queryDetailForUpdate(IdCommand funcGroupQueryDetailForUpdateCommand) {
		return funcGroupQueryCommandExecutor.executeDetailForUpdate(funcGroupQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<FuncGroupVO> pageQuery(FuncGroupPageQueryCommand funcGroupPageQueryCommand) {
		return funcGroupQueryCommandExecutor.executeDetail(funcGroupPageQueryCommand);
	}

	@Override
	public MultiResponse<FuncGroupVO> queryList(FuncGroupQueryListCommand funcGroupQueryListCommand) {
		return funcGroupQueryCommandExecutor.executeDetail(funcGroupQueryListCommand);
	}

	@Autowired
	public void setFuncGroupQueryCommandExecutor(FuncGroupQueryCommandExecutor funcGroupQueryCommandExecutor) {
		this.funcGroupQueryCommandExecutor = funcGroupQueryCommandExecutor;
	}
}
