package com.particle.func.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.executor.representation.FuncQueryCommandExecutor;
import com.particle.func.app.structmapping.FuncAppStructMapping;
import com.particle.func.client.api.representation.IFuncRepresentationApplicationService;
import com.particle.func.client.dto.command.representation.FuncPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	private IFuncService iFuncService;

	@Override
	public SingleResponse<FuncVO> queryDetail(IdCommand funcQueryDetailCommand) {
		return funcQueryCommandExecutor.executeDetail(funcQueryDetailCommand);
	}

	@Override
	public SingleResponse<FuncVO> queryDetailForUpdate(IdCommand funcQueryDetailForUpdateCommand) {
		return funcQueryCommandExecutor.executeDetailForUpdate(funcQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<FuncVO> pageQuery(FuncPageQueryCommand funcPageQueryCommand) {
		return funcQueryCommandExecutor.execute(funcPageQueryCommand);
	}

	@Override
	public MultiResponse<FuncVO> queryListByIds(List<Long> ids, Boolean isDisabled) {
		List<FuncDO> funcDOS = iFuncService.listByFuncIds(ids, isDisabled);
		List<FuncVO> funcVOs = FuncAppStructMapping.instance.funcDOsToFuncVOs(funcDOS);
		return MultiResponse.of(funcVOs);
	}

	@Override
	public MultiResponse<FuncVO> queryList(FuncQueryListCommand funcQueryListCommand) {
		return funcQueryCommandExecutor.execute(funcQueryListCommand);
	}

	@Autowired
	public void setFuncQueryCommandExecutor(FuncQueryCommandExecutor funcQueryCommandExecutor) {
		this.funcQueryCommandExecutor = funcQueryCommandExecutor;
	}

	@Autowired
	public void setiFuncService(IFuncService iFuncService) {
		this.iFuncService = iFuncService;
	}
}
