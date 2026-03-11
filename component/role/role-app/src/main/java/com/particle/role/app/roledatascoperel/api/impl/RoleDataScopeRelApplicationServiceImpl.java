package com.particle.role.app.roledatascoperel.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.roledatascoperel.executor.RoleDataScopeRelCommandExecutor;
import com.particle.role.app.roledatascoperel.executor.RoleDataScopeRelCreateCommandExecutor;
import com.particle.role.app.roledatascoperel.executor.RoleDataScopeRelDeleteCommandExecutor;
import com.particle.role.app.roledatascoperel.executor.RoleDataScopeRelUpdateCommandExecutor;
import com.particle.role.client.roledatascoperel.api.IRoleDataScopeRelApplicationService;
import com.particle.role.client.roledatascoperel.dto.command.DataScopeAssignRoleCommand;
import com.particle.role.client.roledatascoperel.dto.command.RoleAssignDataScopeCommand;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelCreateCommand;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelUpdateCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 角色数据范围关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Transactional
@Service
@CatchAndLog
public class RoleDataScopeRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleDataScopeRelApplicationService {

	private RoleDataScopeRelCreateCommandExecutor roleDataScopeRelCreateCommandExecutor;

	private RoleDataScopeRelDeleteCommandExecutor roleDataScopeRelDeleteCommandExecutor;

	private RoleDataScopeRelUpdateCommandExecutor roleDataScopeRelUpdateCommandExecutor;

	private RoleDataScopeRelCommandExecutor roleDataScopeRelCommandExecutor;


	@Override
	public SingleResponse<RoleDataScopeRelVO> create(RoleDataScopeRelCreateCommand roleDataScopeRelCreateCommand) {
		return roleDataScopeRelCreateCommandExecutor.execute(roleDataScopeRelCreateCommand);
	}

	@Override
	public SingleResponse<RoleDataScopeRelVO> delete(CommonIdCommand deleteCommand) {
		return roleDataScopeRelDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<RoleDataScopeRelVO> update(RoleDataScopeRelUpdateCommand roleDataScopeRelUpdateCommand) {
		return roleDataScopeRelUpdateCommandExecutor.execute(roleDataScopeRelUpdateCommand);
	}


	@Override
	public Response roleAssignDataScope(RoleAssignDataScopeCommand roleAssignDataScopeCommand){
		return roleDataScopeRelCommandExecutor.roleAssignDataScope(roleAssignDataScopeCommand);
	}

	@Override
	public Response dataScopeAssignRole(DataScopeAssignRoleCommand dataScopeAssignRoleCommand){
		return roleDataScopeRelCommandExecutor.dataScopeAssignRole(dataScopeAssignRoleCommand);
	}

	@Override
	public Response deleteByRoleId(CommonIdCommand commonIdCommand){
		return roleDataScopeRelDeleteCommandExecutor.deleteByRoleId(commonIdCommand);
	}

	@Override
	public Response deleteByDataScopeId(CommonIdCommand commonIdCommand){
		return roleDataScopeRelDeleteCommandExecutor.deleteByDataScopeId(commonIdCommand);
	}

	@Autowired
	public void setRoleDataScopeRelCreateCommandExecutor(RoleDataScopeRelCreateCommandExecutor roleDataScopeRelCreateCommandExecutor) {
		this.roleDataScopeRelCreateCommandExecutor = roleDataScopeRelCreateCommandExecutor;
	}

	@Autowired
	public void setRoleDataScopeRelDeleteCommandExecutor(RoleDataScopeRelDeleteCommandExecutor roleDataScopeRelDeleteCommandExecutor) {
		this.roleDataScopeRelDeleteCommandExecutor = roleDataScopeRelDeleteCommandExecutor;
	}
	@Autowired
	public void setRoleDataScopeRelUpdateCommandExecutor(RoleDataScopeRelUpdateCommandExecutor roleDataScopeRelUpdateCommandExecutor) {
		this.roleDataScopeRelUpdateCommandExecutor = roleDataScopeRelUpdateCommandExecutor;
	}
	@Autowired
	public void setRoleDataScopeRelCommandExecutor(RoleDataScopeRelCommandExecutor roleDataScopeRelCommandExecutor) {
		this.roleDataScopeRelCommandExecutor = roleDataScopeRelCommandExecutor;
	}
}
