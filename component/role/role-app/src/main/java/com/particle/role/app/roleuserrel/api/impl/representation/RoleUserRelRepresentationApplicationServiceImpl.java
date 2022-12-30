package com.particle.role.app.roleuserrel.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.roleuserrel.executor.representation.RoleUserRelQueryCommandExecutor;
import com.particle.role.client.roleuserrel.api.representation.IRoleUserRelRepresentationApplicationService;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelPageQueryCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色用户关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Service
@CatchAndLog
public class RoleUserRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleUserRelRepresentationApplicationService {

	private RoleUserRelQueryCommandExecutor roleUserRelQueryCommandExecutor;

	@Override
	public SingleResponse<RoleUserRelVO> queryDetail(IdCommand roleUserRelQueryDetailCommand) {
		return roleUserRelQueryCommandExecutor.executeDetail(roleUserRelQueryDetailCommand);
	}

	@Override
	public PageResponse<RoleUserRelVO> pageQuery(RoleUserRelPageQueryCommand roleUserRelPageQueryCommand) {
		return roleUserRelQueryCommandExecutor.execute(roleUserRelPageQueryCommand);
	}

	@Override
	public MultiResponse<Long> queryUserIdsByRoleId(IdCommand roleIdCommand) {

		return roleUserRelQueryCommandExecutor.queryUserIdsByRoleId(roleIdCommand);
	}

	@Override
	public MultiResponse<Long> queryRoleIdsByUserId(IdCommand userIdCommand) {
		return roleUserRelQueryCommandExecutor.queryRoleIdsByUserId(userIdCommand);
	}

	@Override
	public MultiResponse<RoleUserRelVO> queryList(RoleUserRelQueryListCommand roleUserRelQueryListCommand) {
		return roleUserRelQueryCommandExecutor.execute(roleUserRelQueryListCommand);
	}

	@Autowired
	public void setRoleUserRelQueryCommandExecutor(RoleUserRelQueryCommandExecutor roleUserRelQueryCommandExecutor) {
		this.roleUserRelQueryCommandExecutor = roleUserRelQueryCommandExecutor;
	}
}
