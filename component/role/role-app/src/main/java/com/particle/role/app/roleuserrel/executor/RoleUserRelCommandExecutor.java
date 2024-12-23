package com.particle.role.app.roleuserrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.role.client.roleuserrel.dto.command.RoleAssignUserCommand;
import com.particle.role.client.roleuserrel.dto.command.UserAssignRoleCommand;
import com.particle.role.domain.roleuserrel.gateway.RoleUserRelGateway;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 角色用户关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleUserRelCommandExecutor extends AbstractBaseExecutor {


	private RoleUserRelGateway roleUserRelGateway;

	private IRoleUserRelService iRoleUserRelService;


	/**
	 * 角色分配功能
	 * @param roleAssignUserCommand
	 * @return
	 */
	public Response roleAssignUser(@Valid RoleAssignUserCommand roleAssignUserCommand) {
		boolean result = iRoleUserRelService.removeAndAssignRel(roleAssignUserCommand.getRoleId(),
				roleAssignUserCommand.getCheckedUserIds(),roleAssignUserCommand.getUncheckedUserIds(),
				roleAssignUserCommand.getIsLazyLoad(), RoleUserRelDO::getRoleId,RoleUserRelDO::getUserId,
				(relDto)->new RoleUserRelDO().setRoleId(relDto.getMainId()).setUserId(relDto.getOtherId()));
		return Response.buildSuccess();
	}

	/**
	 * 功能分配角色
	 * @param userAssignRoleCommand
	 * @return
	 */
	public Response userAssignRole(@Valid UserAssignRoleCommand userAssignRoleCommand) {
		boolean result = iRoleUserRelService.removeAndAssignRel(userAssignRoleCommand.getUserId(),
				userAssignRoleCommand.getCheckedRoleIds(),userAssignRoleCommand.getUncheckedRoleIds(),
				userAssignRoleCommand.getIsLazyLoad(), RoleUserRelDO::getUserId,RoleUserRelDO::getRoleId,
				(relDto)->new RoleUserRelDO().setUserId(relDto.getMainId()).setRoleId(relDto.getOtherId()));
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
	public void setiRoleUserRelService(IRoleUserRelService iRoleUserRelService) {
		this.iRoleUserRelService = iRoleUserRelService;
	}
}
