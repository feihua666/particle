package com.particle.role.app.executor;

import com.particle.role.app.structmapping.RoleAppStructMapping;
import com.particle.role.client.dto.command.RoleUpdateCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.domain.Role;
import com.particle.role.domain.RoleId;
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
 * 角色 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class RoleUpdateCommandExecutor  extends AbstractBaseExecutor {

	private RoleGateway roleGateway;

	/**
	 * 执行 角色 更新指令
	 * @param roleUpdateCommand
	 * @return
	 */
	public SingleResponse<RoleVO> execute(@Valid RoleUpdateCommand roleUpdateCommand) {
		Role role = createByRoleUpdateCommand(roleUpdateCommand);
		role.setUpdateControl(roleUpdateCommand);
		boolean save = roleGateway.save(role);
		if (save) {
			return SingleResponse.of(RoleAppStructMapping.instance.toRoleVO(role));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据角色更新指令创建角色模型
	 * @param roleUpdateCommand
	 * @return
	 */
	private Role createByRoleUpdateCommand(RoleUpdateCommand roleUpdateCommand){
		Role role = Role.create();
		RoleUpdateCommandToRoleMapping.instance.fillRoleByRoleUpdateCommand(role, roleUpdateCommand);
		return role;
	}

	@Mapper
	interface RoleUpdateCommandToRoleMapping{
		RoleUpdateCommandToRoleMapping instance = Mappers.getMapper(RoleUpdateCommandToRoleMapping.class );

		default RoleId map(Long id){
			if (id == null) {
				return null;
			}
			return RoleId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param role
		 * @param roleUpdateCommand
		 */
		void fillRoleByRoleUpdateCommand(@MappingTarget Role role, RoleUpdateCommand roleUpdateCommand);
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
