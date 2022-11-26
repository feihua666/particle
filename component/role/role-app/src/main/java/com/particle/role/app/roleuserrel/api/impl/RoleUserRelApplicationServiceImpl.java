package com.particle.role.app.roleuserrel.api.impl;

import com.particle.role.app.roleuserrel.executor.RoleUserRelCreateCommandExecutor;
import com.particle.role.app.roleuserrel.executor.RoleUserRelDeleteCommandExecutor;
import com.particle.role.app.roleuserrel.executor.RoleUserRelUpdateCommandExecutor;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelDeleteCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelUpdateCommand;
import com.particle.role.client.roleuserrel.api.IRoleUserRelApplicationService;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
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

	private RoleUserRelUpdateCommandExecutor roleUserRelUpdateCommandExecutor;


	@Override
	public SingleResponse<RoleUserRelVO> create(RoleUserRelCreateCommand roleUserRelCreateCommand) {
		return roleUserRelCreateCommandExecutor.execute(roleUserRelCreateCommand);
	}

	@Override
	public SingleResponse<RoleUserRelVO> delete(RoleUserRelDeleteCommand roleUserRelDeleteCommand) {
		return roleUserRelDeleteCommandExecutor.execute(roleUserRelDeleteCommand);
	}

	@Override
	public SingleResponse<RoleUserRelVO> update(RoleUserRelUpdateCommand roleUserRelUpdateCommand) {
		return roleUserRelUpdateCommandExecutor.execute(roleUserRelUpdateCommand);
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
	public void setRoleUserRelUpdateCommandExecutor(RoleUserRelUpdateCommandExecutor roleUserRelUpdateCommandExecutor) {
		this.roleUserRelUpdateCommandExecutor = roleUserRelUpdateCommandExecutor;
	}

}
