package com.particle.role.app.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.role.app.rolefuncrel.executor.RoleFuncRelCommandExecutor;
import com.particle.role.app.structmapping.RoleAppStructMapping;
import com.particle.role.client.dto.command.RoleCreateCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.client.rolefuncrel.dto.command.RoleAssignFuncCommand;
import com.particle.role.domain.Role;
import com.particle.role.domain.gateway.RoleGateway;
import com.particle.global.dto.response.SingleResponse;
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
public class RoleCreateCommandExecutor  extends AbstractBaseExecutor {

	private RoleGateway roleGateway;
	/**
	 * 添加角色时允许分配功能
	 */
	private RoleFuncRelCommandExecutor roleFuncRelCommandExecutor;

	/**
	 * 执行角色添加指令
	 * @param roleCreateCommand
	 * @return
	 */
	public SingleResponse<RoleVO> execute(@Valid RoleCreateCommand roleCreateCommand) {
		Role role = createByRoleCreateCommand(roleCreateCommand);
		role.setAddControl(roleCreateCommand);
		boolean save = roleGateway.save(role);
		if (save) {
			// 如果存在 funcIds，则分配功能
			if (CollectionUtil.isNotEmpty(roleCreateCommand.getFuncIds())) {
				RoleAssignFuncCommand roleAssignFuncCommand = new RoleAssignFuncCommand();
				roleAssignFuncCommand.setRoleId(role.getId().getId());
				roleAssignFuncCommand.setCheckedFuncIds(roleCreateCommand.getFuncIds());
				roleFuncRelCommandExecutor.roleAssignFunc(roleAssignFuncCommand);
			}
			return SingleResponse.of(RoleAppStructMapping.instance.toRoleVO(role));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据角色创建指令创建角色模型
	 * @param roleCreateCommand
	 * @return
	 */
	private Role createByRoleCreateCommand(RoleCreateCommand roleCreateCommand){
		Role role = Role.create();
		RoleCreateCommandToRoleMapping.instance.fillRoleByRoleCreateCommand(role, roleCreateCommand);
		return role;
	}

	@Mapper
	interface  RoleCreateCommandToRoleMapping{
		RoleCreateCommandToRoleMapping instance = Mappers.getMapper( RoleCreateCommandToRoleMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param role
		 * @param roleCreateCommand
		 */
		void fillRoleByRoleCreateCommand(@MappingTarget Role role, RoleCreateCommand roleCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param roleGateway
	 */
	@Autowired
	public void setRoleGateway(RoleGateway roleGateway) {
		this.roleGateway = roleGateway;
	}

	@Autowired
	public void setRoleFuncRelCommandExecutor(RoleFuncRelCommandExecutor roleFuncRelCommandExecutor) {
		this.roleFuncRelCommandExecutor = roleFuncRelCommandExecutor;
	}
}
