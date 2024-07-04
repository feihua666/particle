package com.particle.role.app.roledatascoperel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.role.app.roledatascoperel.structmapping.RoleDataScopeRelAppStructMapping;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRel;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRelId;
import com.particle.role.domain.roledatascoperel.gateway.RoleDataScopeRelGateway;
import com.particle.role.infrastructure.roledatascoperel.service.IRoleDataScopeRelService;
import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 角色数据范围关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Component
@Validated
public class RoleDataScopeRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private RoleDataScopeRelGateway roleDataScopeRelGateway;
	private IRoleDataScopeRelService iRoleDataScopeRelService;

	/**
	 * 执行 角色数据范围关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<RoleDataScopeRelVO> execute(@Valid IdCommand deleteCommand) {
		RoleDataScopeRelId roleDataScopeRelId = RoleDataScopeRelId.of(deleteCommand.getId());
		RoleDataScopeRel byId = roleDataScopeRelGateway.getById(roleDataScopeRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = roleDataScopeRelGateway.delete(roleDataScopeRelId);
		if (delete) {
			return SingleResponse.of(RoleDataScopeRelAppStructMapping.instance.toRoleDataScopeRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 根据 roleId 删除
	 * @param roleIdCommand
	 * @return
	 */
	public Response deleteByRoleId(@Valid IdCommand roleIdCommand) {
		boolean result = iRoleDataScopeRelService.deleteByColumn(roleIdCommand.getId(), RoleDataScopeRelDO::getRoleId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 dataScopeId 删除
	 * @param dataScopeIdCommand
	 * @return
	 */
	public Response deleteByDataScopeId(@Valid IdCommand dataScopeIdCommand) {
		boolean result = iRoleDataScopeRelService.deleteByColumn(dataScopeIdCommand.getId(), RoleDataScopeRelDO::getDataScopeId);
		return Response.buildSuccess();
	}

	/**
	 * 注入使用set方法
	 * @param roleDataScopeRelGateway
	 */
	@Autowired
	public void setRoleDataScopeRelGateway(RoleDataScopeRelGateway roleDataScopeRelGateway) {
		this.roleDataScopeRelGateway = roleDataScopeRelGateway;
	}
	@Autowired
	public void setIRoleDataScopeRelService(IRoleDataScopeRelService iRoleDataScopeRelService) {
		this.iRoleDataScopeRelService = iRoleDataScopeRelService;
	}
}
