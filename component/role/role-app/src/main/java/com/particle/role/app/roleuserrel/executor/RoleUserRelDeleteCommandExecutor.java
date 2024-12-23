package com.particle.role.app.roleuserrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.role.app.roleuserrel.structmapping.RoleUserRelAppStructMapping;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.role.domain.roleuserrel.RoleUserRel;
import com.particle.role.domain.roleuserrel.RoleUserRelId;
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
public class RoleUserRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private RoleUserRelGateway roleUserRelGateway;

	private IRoleUserRelService iRoleUserRelService;

	/**
	 * 执行 角色用户关系 删除指令
	 * @param roleUserRelDeleteCommand
	 * @return
	 */
	public SingleResponse<RoleUserRelVO> execute(@Valid IdCommand roleUserRelDeleteCommand) {
		RoleUserRelId roleUserRelId = RoleUserRelId.of(roleUserRelDeleteCommand.getId());
		RoleUserRel byId = roleUserRelGateway.getById(roleUserRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = roleUserRelGateway.delete(roleUserRelId,roleUserRelDeleteCommand);
		if (delete) {
			return SingleResponse.of(RoleUserRelAppStructMapping.instance.toRoleUserRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}
	/**
	 * 根据 roleId 删除
	 * @param roleIdCommand
	 * @return
	 */
	public Response deleteByRoleId(@Valid IdCommand roleIdCommand) {
		boolean result = iRoleUserRelService.deleteByColumn(roleIdCommand.getId(), RoleUserRelDO::getRoleId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 userId 删除
	 * @param userIdCommand
	 * @return
	 */
	public Response deleteByUserId(@Valid IdCommand userIdCommand) {
		boolean result = iRoleUserRelService.deleteByColumn(userIdCommand.getId(), RoleUserRelDO::getUserId);
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
