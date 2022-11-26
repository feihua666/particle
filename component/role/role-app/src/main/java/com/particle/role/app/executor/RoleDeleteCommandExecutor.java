package com.particle.role.app.executor;

import com.particle.role.app.structmapping.RoleAppStructMapping;
import com.particle.role.client.dto.command.RoleDeleteCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.domain.Role;
import com.particle.role.domain.RoleId;
import com.particle.role.domain.gateway.RoleGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 角色 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleDeleteCommandExecutor  extends AbstractBaseExecutor {

	private RoleGateway roleGateway;

	/**
	 * 执行 角色 删除指令
	 * @param roleDeleteCommand
	 * @return
	 */
	public SingleResponse<RoleVO> execute(@Valid RoleDeleteCommand roleDeleteCommand) {
		RoleId roleId = RoleId.of(roleDeleteCommand.getId());
		Role byId = roleGateway.getById(roleId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = roleGateway.delete(roleId);
		if (delete) {
			return SingleResponse.of(RoleAppStructMapping.instance.toRoleVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param roleGateway
	 */
	@Autowired
	public void setRoleGateway(RoleGateway roleGateway) {
		this.roleGateway = roleGateway;
	}
}
