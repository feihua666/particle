package com.particle.role.app.roledatascoperel.api.impl;

import com.particle.role.app.roledatascoperel.executor.RoleDataScopeRelCreateCommandExecutor;
import com.particle.role.app.roledatascoperel.executor.RoleDataScopeRelDeleteCommandExecutor;
import com.particle.role.app.roledatascoperel.executor.RoleDataScopeRelUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelUpdateCommand;
import com.particle.role.client.roledatascoperel.api.IRoleDataScopeRelApplicationService;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelCreateCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;

import com.particle.role.app.roledatascoperel.executor.RoleDataScopeRelCommandExecutor;
import com.particle.role.client.roledatascoperel.dto.command.RoleAssignDataScopeCommand;
import com.particle.role.client.roledatascoperel.dto.command.DataScopeAssignRoleCommand;

import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
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
	public SingleResponse<RoleDataScopeRelVO> delete(IdCommand deleteCommand) {
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
	public Response deleteByRoleId(IdCommand idCommand){
		return roleDataScopeRelDeleteCommandExecutor.deleteByRoleId(idCommand);
	}

	@Override
	public Response deleteByDataScopeId(IdCommand idCommand){
		return roleDataScopeRelDeleteCommandExecutor.deleteByDataScopeId(idCommand);
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
