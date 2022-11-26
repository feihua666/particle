package com.particle.role.app.api.impl.representation;

import com.particle.role.app.executor.representation.RoleQueryCommandExecutor;
import com.particle.role.client.dto.command.RoleDeleteCommand;
import com.particle.role.client.dto.command.RoleUpdateCommand;
import com.particle.role.client.dto.command.representation.RoleQueryDetailCommand;
import com.particle.role.client.dto.command.representation.RoleQueryDetailForUpdateCommand;
import com.particle.role.client.dto.command.representation.RolePageQueryCommand;
import com.particle.role.client.api.representation.IRoleRepresentationApplicationService;
import com.particle.role.client.dto.command.RoleCreateCommand;
import com.particle.role.client.dto.command.representation.RoleQueryListCommand;
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
@Service
@CatchAndLog
public class RoleRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleRepresentationApplicationService {

	private RoleQueryCommandExecutor roleQueryCommandExecutor;

	@Override
	public SingleResponse<RoleVO> queryDetail(RoleQueryDetailCommand roleQueryDetailCommand) {
		return roleQueryCommandExecutor.execute(roleQueryDetailCommand);
	}

	@Override
	public SingleResponse<RoleVO> queryDetailForUpdate(RoleQueryDetailForUpdateCommand roleQueryDetailForUpdateCommand) {
		return roleQueryCommandExecutor.execute(roleQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<RoleVO> pageQuery(RolePageQueryCommand rolePageQueryCommand) {
		return roleQueryCommandExecutor.execute(rolePageQueryCommand);
	}

	@Override
	public MultiResponse<RoleVO> queryList(RoleQueryListCommand roleQueryListCommand) {
		return roleQueryCommandExecutor.execute(roleQueryListCommand);
	}

	@Autowired
	public void setRoleQueryCommandExecutor(RoleQueryCommandExecutor roleQueryCommandExecutor) {
		this.roleQueryCommandExecutor = roleQueryCommandExecutor;
	}
}
