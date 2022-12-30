package com.particle.role.app.roleuserrel.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.roleuserrel.executor.RoleUserRelCommandExecutor;
import com.particle.role.app.roleuserrel.executor.RoleUserRelCreateCommandExecutor;
import com.particle.role.app.roleuserrel.executor.RoleUserRelDeleteCommandExecutor;
import com.particle.role.client.roleuserrel.api.IRoleUserRelApplicationService;
import com.particle.role.client.roleuserrel.dto.command.RoleAssignUserCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.command.UserAssignRoleCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色用户关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Transactional
@Service
@CatchAndLog
public class RoleUserRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleUserRelApplicationService {

	private RoleUserRelCreateCommandExecutor roleUserRelCreateCommandExecutor;

	private RoleUserRelDeleteCommandExecutor roleUserRelDeleteCommandExecutor;

	private RoleUserRelCommandExecutor roleUserRelCommandExecutor;

	@Override
	public SingleResponse<RoleUserRelVO> create(RoleUserRelCreateCommand roleUserRelCreateCommand) {
		return roleUserRelCreateCommandExecutor.execute(roleUserRelCreateCommand);
	}

	@Override
	public SingleResponse<RoleUserRelVO> delete(IdCommand roleUserRelDeleteCommand) {
		return roleUserRelDeleteCommandExecutor.execute(roleUserRelDeleteCommand);
	}


	@Override
	public Response roleAssignUser(RoleAssignUserCommand cf) {
		return roleUserRelCommandExecutor.roleAssignUser(cf);
	}

	@Override
	public Response userAssignRole(UserAssignRoleCommand cf) {
		return roleUserRelCommandExecutor.userAssignRole(cf);
	}

	@Override
	public Response deleteByRoleId(IdCommand roleIdCommand) {
		return roleUserRelDeleteCommandExecutor.deleteByRoleId(roleIdCommand);
	}

	@Override
	public Response deleteByUserId(IdCommand userIdCommand) {
		return roleUserRelDeleteCommandExecutor.deleteByUserId(userIdCommand);
	}


	@Autowired
	public void setRoleUserRelCreateCommandExecutor(RoleUserRelCreateCommandExecutor roleUserRelCreateCommandExecutor) {
		this.roleUserRelCreateCommandExecutor = roleUserRelCreateCommandExecutor;
	}

	@Autowired
	public void setRoleUserRelDeleteCommandExecutor(RoleUserRelDeleteCommandExecutor roleUserRelDeleteCommandExecutor) {
		this.roleUserRelDeleteCommandExecutor = roleUserRelDeleteCommandExecutor;
	}

	@Autowired
	public void setRoleUserRelCommandExecutor(RoleUserRelCommandExecutor roleUserRelCommandExecutor) {
		this.roleUserRelCommandExecutor = roleUserRelCommandExecutor;
	}
}
