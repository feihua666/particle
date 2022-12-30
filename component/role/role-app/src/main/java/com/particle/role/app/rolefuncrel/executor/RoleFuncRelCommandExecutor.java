package com.particle.role.app.rolefuncrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.role.client.rolefuncrel.dto.command.FuncAssignRoleCommand;
import com.particle.role.client.rolefuncrel.dto.command.RoleAssignFuncCommand;
import com.particle.role.domain.roleuserrel.gateway.RoleUserRelGateway;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 角色功能菜单关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleFuncRelCommandExecutor extends AbstractBaseExecutor {

	private RoleUserRelGateway roleUserRelGateway;

	private IRoleFuncRelService iRoleFuncRelService;


	/**
	 * 角色分配功能
	 * @param roleAssignFuncCommand
	 * @return
	 */
	public Response roleAssignFunc(@Valid RoleAssignFuncCommand roleAssignFuncCommand) {
		boolean result = iRoleFuncRelService.removeAndAssignRel(roleAssignFuncCommand.getRoleId(),
				roleAssignFuncCommand.getCheckedFuncIds(),roleAssignFuncCommand.getUncheckedFuncIds(),
				roleAssignFuncCommand.getIsLazyLoad(), RoleFuncRelDO::getRoleId,RoleFuncRelDO::getFuncId,
				(relDto)->new RoleFuncRelDO().setRoleId(relDto.getMainId()).setFuncId(relDto.getOtherId()));
		return Response.buildSuccess();
	}

	/**
	 * 功能分配角色
	 * @param funcAssignRoleCommand
	 * @return
	 */
	public Response funcAssignRole(@Valid FuncAssignRoleCommand funcAssignRoleCommand) {
		boolean result = iRoleFuncRelService.removeAndAssignRel(funcAssignRoleCommand.getFuncId(),
				funcAssignRoleCommand.getCheckedRoleIds(),funcAssignRoleCommand.getUncheckedRoleIds(),
				funcAssignRoleCommand.getIsLazyLoad(), RoleFuncRelDO::getFuncId,RoleFuncRelDO::getRoleId,
				(relDto)->new RoleFuncRelDO().setFuncId(relDto.getMainId()).setRoleId(relDto.getOtherId()));
		return Response.buildSuccess();
	}

	/**
	 * 注入使用set方法
	 * @param roleUserRelGateway
	 */
	@Autowired
	public void setRoleUserRelGateway(RoleUserRelGateway roleUserRelGateway) {
		this.roleUserRelGateway = roleUserRelGateway;
	}
	@Autowired
	public void setiRoleFuncRelService(IRoleFuncRelService iRoleFuncRelService) {
		this.iRoleFuncRelService = iRoleFuncRelService;
	}
}
