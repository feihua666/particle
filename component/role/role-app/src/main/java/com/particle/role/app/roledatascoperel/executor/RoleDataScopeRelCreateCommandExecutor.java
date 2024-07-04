package com.particle.role.app.roledatascoperel.executor;

import com.particle.role.app.roledatascoperel.structmapping.RoleDataScopeRelAppStructMapping;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelCreateCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRel;
import com.particle.role.domain.roledatascoperel.gateway.RoleDataScopeRelGateway;
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
 * 角色数据范围关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Component
@Validated
public class RoleDataScopeRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private RoleDataScopeRelGateway roleDataScopeRelGateway;

	/**
	 * 执行角色数据范围关系添加指令
	 * @param roleDataScopeRelCreateCommand
	 * @return
	 */
	public SingleResponse<RoleDataScopeRelVO> execute(@Valid RoleDataScopeRelCreateCommand roleDataScopeRelCreateCommand) {
		RoleDataScopeRel roleDataScopeRel = createByRoleDataScopeRelCreateCommand(roleDataScopeRelCreateCommand);
		roleDataScopeRel.setAddControl(roleDataScopeRelCreateCommand);
		boolean save = roleDataScopeRelGateway.save(roleDataScopeRel);
		if (save) {
			return SingleResponse.of(RoleDataScopeRelAppStructMapping.instance.toRoleDataScopeRelVO(roleDataScopeRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据角色数据范围关系创建指令创建角色数据范围关系模型
	 * @param roleDataScopeRelCreateCommand
	 * @return
	 */
	private RoleDataScopeRel createByRoleDataScopeRelCreateCommand(RoleDataScopeRelCreateCommand roleDataScopeRelCreateCommand){
		RoleDataScopeRel roleDataScopeRel = RoleDataScopeRel.create();
		RoleDataScopeRelCreateCommandToRoleDataScopeRelMapping.instance.fillRoleDataScopeRelByRoleDataScopeRelCreateCommand(roleDataScopeRel, roleDataScopeRelCreateCommand);
		return roleDataScopeRel;
	}

	@Mapper
	interface  RoleDataScopeRelCreateCommandToRoleDataScopeRelMapping{
		RoleDataScopeRelCreateCommandToRoleDataScopeRelMapping instance = Mappers.getMapper( RoleDataScopeRelCreateCommandToRoleDataScopeRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param roleDataScopeRel
		 * @param roleDataScopeRelCreateCommand
		 */
		void fillRoleDataScopeRelByRoleDataScopeRelCreateCommand(@MappingTarget RoleDataScopeRel roleDataScopeRel, RoleDataScopeRelCreateCommand roleDataScopeRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param roleDataScopeRelGateway
	 */
	@Autowired
	public void setRoleDataScopeRelGateway(RoleDataScopeRelGateway roleDataScopeRelGateway) {
		this.roleDataScopeRelGateway = roleDataScopeRelGateway;
	}
}
