package com.particle.role.app.rolefuncrel.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.rolefuncrel.executor.representation.RoleFuncRelQueryCommandExecutor;
import com.particle.role.client.rolefuncrel.api.representation.IRoleFuncRelRepresentationApplicationService;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单功能关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Service
@CatchAndLog
public class RoleFuncRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleFuncRelRepresentationApplicationService {

	private RoleFuncRelQueryCommandExecutor roleFuncRelQueryCommandExecutor;

	@Override
	public SingleResponse<RoleFuncRelVO> queryDetail(IdCommand roleFuncRelQueryDetailCommand) {
		return roleFuncRelQueryCommandExecutor.executeDetail(roleFuncRelQueryDetailCommand);
	}

	@Override
	public PageResponse<RoleFuncRelVO> pageQuery(RoleFuncRelPageQueryCommand roleFuncRelPageQueryCommand) {
		return roleFuncRelQueryCommandExecutor.execute(roleFuncRelPageQueryCommand);
	}

	@Override
	public MultiResponse<Long> queryFuncIdsByRoleId(IdCommand roleIdCommand) {

		return roleFuncRelQueryCommandExecutor.queryFuncIdsByRoleId(roleIdCommand);
	}

	@Override
	public MultiResponse<Long> queryRoleIdsByFuncId(IdCommand funcIdCommand) {
		return roleFuncRelQueryCommandExecutor.queryRoleIdsByFuncId(funcIdCommand);
	}

	@Override
	public MultiResponse<RoleFuncRelVO> queryList(RoleFuncRelQueryListCommand roleFuncRelQueryListCommand) {
		return roleFuncRelQueryCommandExecutor.execute(roleFuncRelQueryListCommand);
	}

	@Autowired
	public void setRoleFuncRelQueryCommandExecutor(RoleFuncRelQueryCommandExecutor roleFuncRelQueryCommandExecutor) {
		this.roleFuncRelQueryCommandExecutor = roleFuncRelQueryCommandExecutor;
	}
}
