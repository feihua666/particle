package com.particle.role.app.rolefuncrel.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.rolefuncrel.executor.RoleFuncRelCommandExecutor;
import com.particle.role.app.rolefuncrel.executor.RoleFuncRelCreateCommandExecutor;
import com.particle.role.app.rolefuncrel.executor.RoleFuncRelDeleteCommandExecutor;
import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelCreateCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.client.rolefuncrel.dto.command.FuncAssignRoleCommand;
import com.particle.role.client.rolefuncrel.dto.command.RoleAssignFuncCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 角色菜单功能关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Transactional
@Service
@CatchAndLog
public class RoleFuncRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleFuncRelApplicationService {

	private RoleFuncRelCreateCommandExecutor roleFuncRelCreateCommandExecutor;

	private RoleFuncRelDeleteCommandExecutor roleFuncRelDeleteCommandExecutor;

	private RoleFuncRelCommandExecutor roleFuncRelCommandExecutor;

	@Override
	public SingleResponse<RoleFuncRelVO> create(RoleFuncRelCreateCommand roleFuncRelCreateCommand) {
		return roleFuncRelCreateCommandExecutor.execute(roleFuncRelCreateCommand);
	}

	@Override
	public SingleResponse<RoleFuncRelVO> delete(IdCommand roleFuncRelDeleteCommand) {
		return roleFuncRelDeleteCommandExecutor.execute(roleFuncRelDeleteCommand);
	}

	@Override
	public Response roleAssignFunc(RoleAssignFuncCommand cf) {
		return roleFuncRelCommandExecutor.roleAssignFunc(cf);
	}

	@Override
	public Response funcAssignRole(FuncAssignRoleCommand cf) {
		return roleFuncRelCommandExecutor.funcAssignRole(cf);
	}

	@Override
	public Response deleteByRoleId(IdCommand roleIdCommand) {
		return roleFuncRelDeleteCommandExecutor.deleteByRoleId(roleIdCommand);
	}

	@Override
	public Response deleteByFuncId(IdCommand funcIdCommand) {
		return roleFuncRelDeleteCommandExecutor.deleteByFuncId(funcIdCommand);
	}

	@Autowired
	public void setRoleFuncRelCreateCommandExecutor(RoleFuncRelCreateCommandExecutor roleFuncRelCreateCommandExecutor) {
		this.roleFuncRelCreateCommandExecutor = roleFuncRelCreateCommandExecutor;
	}

	@Autowired
	public void setRoleFuncRelDeleteCommandExecutor(RoleFuncRelDeleteCommandExecutor roleFuncRelDeleteCommandExecutor) {
		this.roleFuncRelDeleteCommandExecutor = roleFuncRelDeleteCommandExecutor;
	}

	@Autowired
	public void setRoleFuncRelCommandExecutor(RoleFuncRelCommandExecutor roleFuncRelCommandExecutor) {
		this.roleFuncRelCommandExecutor = roleFuncRelCommandExecutor;
	}
}
