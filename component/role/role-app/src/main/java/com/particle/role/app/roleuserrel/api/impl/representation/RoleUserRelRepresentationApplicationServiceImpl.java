package com.particle.role.app.roleuserrel.api.impl.representation;

import com.particle.role.app.roleuserrel.executor.representation.RoleUserRelQueryCommandExecutor;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelDeleteCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelUpdateCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryDetailCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryDetailForUpdateCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelPageQueryCommand;
import com.particle.role.client.roleuserrel.api.representation.IRoleUserRelRepresentationApplicationService;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
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
@Service
@CatchAndLog
public class RoleUserRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleUserRelRepresentationApplicationService {

	private RoleUserRelQueryCommandExecutor roleUserRelQueryCommandExecutor;

	@Override
	public SingleResponse<RoleUserRelVO> queryDetail(RoleUserRelQueryDetailCommand roleUserRelQueryDetailCommand) {
		return roleUserRelQueryCommandExecutor.execute(roleUserRelQueryDetailCommand);
	}

	@Override
	public SingleResponse<RoleUserRelVO> queryDetailForUpdate(RoleUserRelQueryDetailForUpdateCommand roleUserRelQueryDetailForUpdateCommand) {
		return roleUserRelQueryCommandExecutor.execute(roleUserRelQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<RoleUserRelVO> pageQuery(RoleUserRelPageQueryCommand roleUserRelPageQueryCommand) {
		return roleUserRelQueryCommandExecutor.execute(roleUserRelPageQueryCommand);
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
