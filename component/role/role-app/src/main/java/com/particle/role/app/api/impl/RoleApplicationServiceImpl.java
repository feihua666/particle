package com.particle.role.app.api.impl;

import com.particle.role.app.executor.RoleCreateCommandExecutor;
import com.particle.role.app.executor.RoleDeleteCommandExecutor;
import com.particle.role.app.executor.RoleUpdateCommandExecutor;
import com.particle.role.client.dto.command.RoleDeleteCommand;
import com.particle.role.client.dto.command.RoleUpdateCommand;
import com.particle.role.client.api.IRoleApplicationService;
import com.particle.role.client.dto.command.RoleCreateCommand;
import com.particle.role.client.dto.data.RoleVO;
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
 * 角色 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Transactional
@Service
@CatchAndLog
public class RoleApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleApplicationService {

	private RoleCreateCommandExecutor roleCreateCommandExecutor;

	private RoleDeleteCommandExecutor roleDeleteCommandExecutor;

	private RoleUpdateCommandExecutor roleUpdateCommandExecutor;


	@Override
	public SingleResponse<RoleVO> create(RoleCreateCommand roleCreateCommand) {
		return roleCreateCommandExecutor.execute(roleCreateCommand);
	}

	@Override
	public SingleResponse<RoleVO> delete(RoleDeleteCommand roleDeleteCommand) {
		return roleDeleteCommandExecutor.execute(roleDeleteCommand);
	}

	@Override
	public SingleResponse<RoleVO> update(RoleUpdateCommand roleUpdateCommand) {
		return roleUpdateCommandExecutor.execute(roleUpdateCommand);
	}

	@Autowired
	public void setRoleCreateCommandExecutor(RoleCreateCommandExecutor roleCreateCommandExecutor) {
		this.roleCreateCommandExecutor = roleCreateCommandExecutor;
	}

	@Autowired
	public void setRoleDeleteCommandExecutor(RoleDeleteCommandExecutor roleDeleteCommandExecutor) {
		this.roleDeleteCommandExecutor = roleDeleteCommandExecutor;
	}
	@Autowired
	public void setRoleUpdateCommandExecutor(RoleUpdateCommandExecutor roleUpdateCommandExecutor) {
		this.roleUpdateCommandExecutor = roleUpdateCommandExecutor;
	}

}
